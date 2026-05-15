package com.example.tiffin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [CustomerEntity::class, PaymentEntity::class, TiffinAbsenceEntity::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun paymentDao(): PaymentDao
    abstract fun tiffinAbsenceDao(): TiffinAbsenceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE customers ADD COLUMN photoUri TEXT DEFAULT NULL")
                database.execSQL("ALTER TABLE customers ADD COLUMN baseMonthlyFee INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE customers ADD COLUMN tiffinPrice INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE payments ADD COLUMN absentDays INTEGER NOT NULL DEFAULT 0")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `tiffin_absences` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `customerId` INTEGER NOT NULL,
                        `month` INTEGER NOT NULL,
                        `year` INTEGER NOT NULL,
                        `dateEpochDay` INTEGER NOT NULL,
                        `slot` TEXT NOT NULL
                    )
                    """.trimIndent()
                )
                database.execSQL(
                    """
                    CREATE UNIQUE INDEX IF NOT EXISTS
                    `index_tiffin_absences_customerId_dateEpochDay_slot`
                    ON `tiffin_absences` (`customerId`, `dateEpochDay`, `slot`)
                    """.trimIndent()
                )
            }
        }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE payments ADD COLUMN startDateMs INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE payments ADD COLUMN endDateMs INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE payments ADD COLUMN baseMonthlyFee INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE payments ADD COLUMN tiffinPrice INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tiffin.db"
                )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                .build().also { INSTANCE = it }
            }
        }
    }
}
