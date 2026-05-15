import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun main() {
    val cal = Calendar.getInstance()
    cal.set(2024, Calendar.JANUARY, 2, 0, 0, 0)
    
    val sdf = SimpleDateFormat("MMM dd", Locale.US)
    
    for (i in 0 until 5) {
        val startStr = sdf.format(cal.time)
        val startMs = cal.timeInMillis
        
        cal.add(Calendar.MONTH, 1)
        val endStr = sdf.format(cal.time)
        val endMs = cal.timeInMillis
        
        println("Cycle $i: $startStr to $endStr")
        
        // Setup next start date
        cal.add(Calendar.DAY_OF_MONTH, 1)
    }
}
