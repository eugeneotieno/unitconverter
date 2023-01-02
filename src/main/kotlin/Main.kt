const val OUTPUT = """
145 centimeters is 1.45 meters
2 miles is 3.2187 kilometers
5.5 inches is 139.7 millimeters
12 degrees Celsius is 53.6 degrees Fahrenheit
3 pounds is 1.360776 kilograms
"""

const val ONE_KM_TO_M = 1000

fun main() {
    println("Enter a number and a measure: ")
    val (distance, measure) = readln().split(" ")
    if (measure.lowercase() !in arrayOf("km", "kilometers", "kilometer")) {
        println("Wrong input")
    } else {
        val num = distance.toInt()
        val result: Int
        if (num == 1) {
            result = num * ONE_KM_TO_M
            println("$num kilometer is $result meters")
        } else {
            result = num * ONE_KM_TO_M
            println("$num kilometers is $result meters")
        }
    }
}