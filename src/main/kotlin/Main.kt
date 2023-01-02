const val OUTPUT = """
145 centimeters is 1.45 meters
2 miles is 3.2187 kilometers
5.5 inches is 139.7 millimeters
12 degrees Celsius is 53.6 degrees Fahrenheit
3 pounds is 1.360776 kilograms
"""

const val ONE_METER_TO_METER = 1
const val ONE_KILOMETER_TO_METER = 1000
const val ONE_CENTIMETER_TO_METER = 0.01
const val ONE_MILLIMETER_TO_METER = 0.001
const val ONE_MILE_TO_METER = 1609.35
const val ONE_YARD_TO_METER = 0.9144
const val ONE_FOOT_TO_METER = 0.3048
const val ONE_INCH_TO_METER = 0.0254

fun main() {
    println("Enter a number and a measure of length: ")
    val (distance, measure) = readln().split(" ")
    val result: Double
    val num = distance.toDouble()
    if (measure.lowercase() in arrayOf("m", "meter", "meters")) {
        result = num * ONE_METER_TO_METER
        var inputMeasure = "meters"
        if (num == 1.0) inputMeasure = "meter"
        if (result == 1.0) {
            println("$num $inputMeasure is $result meter")
        } else {
            println("$num $inputMeasure is $result meters")
        }
    } else if (measure.lowercase() in arrayOf("km", "kilometers", "kilometer")) {
        result = num * ONE_KILOMETER_TO_METER
        var inputMeasure = "kilometers"
        if (num == 1.0) inputMeasure = "kilometer"
        if (result == 1.0) {
            println("$num $inputMeasure is $result meter")
        } else {
            println("$num $inputMeasure is $result meters")
        }
    } else if (measure.lowercase() in arrayOf("cm", "centimeter", "centimeters")) {
        result = num * ONE_CENTIMETER_TO_METER
        var inputMeasure = "centimeters"
        if (num == 1.0) inputMeasure = "centimeter"
        if (result == 1.0) {
            println("$num $inputMeasure is $result meter")
        } else {
            println("$num $inputMeasure is $result meters")
        }
    } else if (measure.lowercase() in arrayOf("mm", "millimeter", "millimeters")) {
        result = num * ONE_MILLIMETER_TO_METER
        var inputMeasure = "millimeters"
        if (num == 1.0) inputMeasure = "millimeter"
        if (result == 1.0) {
            println("$num $inputMeasure is $result meter")
        } else {
            println("$num $inputMeasure is $result meters")
        }
    } else if (measure.lowercase() in arrayOf("mi", "mile", "miles")) {
        result = num * ONE_MILE_TO_METER
        var inputMeasure = "miles"
        if (num == 1.0) inputMeasure = "mile"
        if (result == 1.0) {
            println("$num $inputMeasure is $result meter")
        } else {
            println("$num $inputMeasure is $result meters")
        }
    } else if (measure.lowercase() in arrayOf("yd", "yard", "yards")) {
        result = num * ONE_YARD_TO_METER
        var inputMeasure = "yards"
        if (num == 1.0) inputMeasure = "yard"
        if (result == 1.0) {
            println("$num $inputMeasure is $result meter")
        } else {
            println("$num $inputMeasure is $result meters")
        }
    } else if (measure.lowercase() in arrayOf("ft", "foot", "feet")) {
        result = num * ONE_FOOT_TO_METER
        var inputMeasure = "feet"
        if (num == 1.0) inputMeasure = "foot"
        if (result == 1.0) {
            println("$num $inputMeasure is $result meter")
        } else {
            println("$num $inputMeasure is $result meters")
        }
    } else if (measure.lowercase() in arrayOf("in", "inch", "inches")) {
        result = num * ONE_INCH_TO_METER
        var inputMeasure = "inches"
        if (num == 1.0) inputMeasure = "inch"
        if (result == 1.0) {
            println("$num $inputMeasure is $result meter")
        } else {
            println("$num $inputMeasure is $result meters")
        }
    } else {
        println("Wrong input. Unknown unit $measure")
    }
}