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

const val ONE_GRAM_TO_GRAM = 1
const val ONE_KILOGRAM_TO_GRAM = 1000
const val ONE_MILLIGRAM_TO_GRAM = 0.001
const val ONE_POUND_TO_GRAM = 453.592
const val ONE_OUNCE_TO_GRAM = 28.3495

fun main() {
    var exit = false
    do {
        print("Enter what you want to convert (or exit): ")
        val action = readln()

        if (action == "exit") {
            exit = true
        } else {
            val distance = action.split(" ")[0]
            val measure = action.split(" ")[1]
            val metric = action.split(" ")[3]

            val weightMetrics = arrayOf(
                "g", "gram", "grams",
                "kg", "kilogram", "kilograms",
                "mg", "milligram", "milligrams",
                "lb", "pound", "pounds",
                "oz", "ounce", "ounces"
            )

            val lengthMetrics = arrayOf(
                "m", "meter", "meters",
                "km", "kilometers", "kilometer",
                "cm", "centimeter", "centimeters",
                "mm", "millimeter", "millimeters",
                "mi", "mile", "miles",
                "yd", "yard", "yards",
                "ft", "foot", "feet",
                "in", "inch", "inches"
            )

            if (measure.lowercase() in lengthMetrics && metric.lowercase() in lengthMetrics) {
                lengthIntermediate(distance.toDouble(), measure, metric)
            } else if (measure.lowercase() in weightMetrics && metric.lowercase() in weightMetrics) {
                weightIntermediate(distance.toDouble(), measure, metric)
            } else {
                if (measure.lowercase() !in weightMetrics && measure.lowercase() !in lengthMetrics) {
                    if (metric.lowercase() !in weightMetrics && metric.lowercase() !in lengthMetrics) {
                        println("Conversion from ??? to ??? is impossible")
                    } else {
                        println("Conversion from ??? to ${getPlural(metric)} is impossible")
                    }
                } else {
                    println("Conversion from ${getPlural(measure)} to ${getPlural(metric)} is impossible")
                }

                println()
            }
        }
    } while (!exit)
}

fun getPlural(metric: String): String {
    if (metric.lowercase() in arrayOf("g", "gram", "grams")) {
        return "grams"
    } else if (metric.lowercase() in arrayOf("kg", "kilogram", "kilograms")) {
        return "kilograms"
    } else if (metric.lowercase() in arrayOf("mg", "milligram", "milligrams")) {
        return "milligrams"
    } else if (metric.lowercase() in arrayOf("lb", "pound", "pounds")) {
        return "pounds"
    } else if (metric.lowercase() in arrayOf("oz", "ounce", "ounces")) {
        return "ounces"
    } else if (metric.lowercase() in arrayOf("m", "meter", "meters")) {
        return "meters"
    } else if (metric.lowercase() in arrayOf("km", "kilometers", "kilometer")) {
        return "kilometers"
    } else if (metric.lowercase() in arrayOf("cm", "centimeter", "centimeters")) {
        return "centimeters"
    } else if (metric.lowercase() in arrayOf("mm", "millimeter", "millimeters")) {
        return "millimeters"
    } else if (metric.lowercase() in arrayOf("mi", "mile", "miles")) {
        return "miles"
    } else if (metric.lowercase() in arrayOf("yd", "yard", "yards")) {
        return "yards"
    } else if (metric.lowercase() in arrayOf("ft", "foot", "feet")) {
        return "feet"
    } else if (metric.lowercase() in arrayOf("in", "inch", "inches")) {
        return "inches"
    } else {
        return "???"
    }
}

fun lengthIntermediate(distance: Double, measure: String, metric: String) {
    val result: String
    if (measure.lowercase() in arrayOf("m", "meter", "meters")) {
        result = lengths(distance * ONE_METER_TO_METER, metric)
        var inputMeasure = "meters"
        if (distance == 1.0) inputMeasure = "meter"
        println("$distance $inputMeasure $result")
    } else if (measure.lowercase() in arrayOf("km", "kilometers", "kilometer")) {
        result = lengths(distance * ONE_KILOMETER_TO_METER, metric)
        var inputMeasure = "kilometers"
        if (distance == 1.0) inputMeasure = "kilometer"
        println("$distance $inputMeasure $result")
    } else if (measure.lowercase() in arrayOf("cm", "centimeter", "centimeters")) {
        result = lengths(distance * ONE_CENTIMETER_TO_METER, metric)
        var inputMeasure = "centimeters"
        if (distance == 1.0) inputMeasure = "centimeter"
        println("$distance $inputMeasure $result")
    } else if (measure.lowercase() in arrayOf("mm", "millimeter", "millimeters")) {
        result = lengths(distance * ONE_MILLIMETER_TO_METER, metric)
        var inputMeasure = "millimeters"
        if (distance == 1.0) inputMeasure = "millimeter"
        println("$distance $inputMeasure $result")
    } else if (measure.lowercase() in arrayOf("mi", "mile", "miles")) {
        result = lengths(distance * ONE_MILE_TO_METER, metric)
        var inputMeasure = "miles"
        if (distance == 1.0) inputMeasure = "mile"
        println("$distance $inputMeasure $result")
    } else if (measure.lowercase() in arrayOf("yd", "yard", "yards")) {
        result = lengths(distance * ONE_YARD_TO_METER, metric)
        var inputMeasure = "yards"
        if (distance == 1.0) inputMeasure = "yard"
        println("$distance $inputMeasure $result")
    } else if (measure.lowercase() in arrayOf("ft", "foot", "feet")) {
        result = lengths(distance * ONE_FOOT_TO_METER, metric)
        var inputMeasure = "feet"
        if (distance == 1.0) inputMeasure = "foot"
        println("$distance $inputMeasure $result")
    } else if (measure.lowercase() in arrayOf("in", "inch", "inches")) {
        result = lengths(distance * ONE_INCH_TO_METER, metric)
        var inputMeasure = "inch"
        if (distance == 1.0) inputMeasure = "inches"
        println("$distance $inputMeasure $result")
    }

    println()
}

fun lengths(num: Double, measure: String): String {
    val result: Double
    if (measure.lowercase() in arrayOf("m", "meter", "meters")) {
        result = num / ONE_METER_TO_METER
        return if (result == 1.0) {
            "is $result meter"
        } else {
            "is $result meters"
        }
    } else if (measure.lowercase() in arrayOf("km", "kilometers", "kilometer")) {
        result = num / ONE_KILOMETER_TO_METER
        return if (result == 1.0) {
            "is $result kilometer"
        } else {
            "is $result kilometers"
        }
    } else if (measure.lowercase() in arrayOf("cm", "centimeter", "centimeters")) {
        result = num / ONE_CENTIMETER_TO_METER
        return if (result == 1.0) {
            "is $result centimeter"
        } else {
            "is $result centimeters"
        }
    } else if (measure.lowercase() in arrayOf("mm", "millimeter", "millimeters")) {
        result = num / ONE_MILLIMETER_TO_METER
        return if (result == 1.0) {
            "is $result millimeter"
        } else {
            "is $result millimeters"
        }
    } else if (measure.lowercase() in arrayOf("mi", "mile", "miles")) {
        result = num / ONE_MILE_TO_METER
        return if (result == 1.0) {
            "is $result mile"
        } else {
            "is $result miles"
        }
    } else if (measure.lowercase() in arrayOf("yd", "yard", "yards")) {
        result = num / ONE_YARD_TO_METER
        return if (result == 1.0) {
            "is $result yard"
        } else {
            "is $result yards"
        }
    } else if (measure.lowercase() in arrayOf("ft", "foot", "feet")) {
        result = num / ONE_FOOT_TO_METER
        return if (result == 1.0) {
            "is $result foot"
        } else {
            "is $result feet"
        }
    } else if (measure.lowercase() in arrayOf("in", "inch", "inches")) {
        result = num / ONE_INCH_TO_METER
        return if (result == 1.0) {
            "is $result inch"
        } else {
            "is $result inches"
        }
    } else {
        return "to $measure is impossible"
    }
}

fun weightIntermediate(weight: Double, measure: String, metric: String) {
    val result: String
    if (measure.lowercase() in arrayOf("g", "gram", "grams")) {
        result = weights(weight * ONE_GRAM_TO_GRAM, metric)
        var inputMeasure = "grams"
        if (weight == 1.0) inputMeasure = "gram"
        println("$weight $inputMeasure $result")
    } else if (measure.lowercase() in arrayOf("kg", "kilogram", "kilograms")) {
        result = weights(weight * ONE_KILOGRAM_TO_GRAM, metric)
        var inputMeasure = "kilograms"
        if (weight == 1.0) inputMeasure = "kilogram"
        println("$weight $inputMeasure $result")
    } else if (measure.lowercase() in arrayOf("mg", "milligram", "milligrams")) {
        result = weights(weight * ONE_MILLIGRAM_TO_GRAM, metric)
        var inputMeasure = "milligrams"
        if (weight == 1.0) inputMeasure = "milligram"
        println("$weight $inputMeasure $result")
    } else if (measure.lowercase() in arrayOf("lb", "pound", "pounds")) {
        result = weights(weight * ONE_POUND_TO_GRAM, metric)
        var inputMeasure = "pounds"
        if (weight == 1.0) inputMeasure = "pound"
        println("$weight $inputMeasure $result")
    } else if (measure.lowercase() in arrayOf("oz", "ounce", "ounces")) {
        result = weights(weight * ONE_OUNCE_TO_GRAM, metric)
        var inputMeasure = "ounces"
        if (weight == 1.0) inputMeasure = "ounce"
        println("$weight $inputMeasure $result")
    }

    println()
}

fun weights(num: Double, measure: String): String {
    val result: Double
    if (measure.lowercase() in arrayOf("g", "gram", "grams")) {
        result = num / ONE_GRAM_TO_GRAM
        return if (result == 1.0) {
            "is $result gram"
        } else {
            "is $result grams"
        }
    } else if (measure.lowercase() in arrayOf("kg", "kilogram", "kilograms")) {
        result = num / ONE_KILOGRAM_TO_GRAM
        return if (result == 1.0) {
            "is $result kilogram"
        } else {
            "is $result kilograms"
        }
    } else if (measure.lowercase() in arrayOf("mg", "milligram", "milligrams")) {
        result = num / ONE_MILLIGRAM_TO_GRAM
        return if (result == 1.0) {
            "is $result milligram"
        } else {
            "is $result milligrams"
        }
    } else if (measure.lowercase() in arrayOf("lb", "pound", "pounds")) {
        result = num / ONE_POUND_TO_GRAM
        return if (result == 1.0) {
            "is $result pound"
        } else {
            "is $result pounds"
        }
    } else if (measure.lowercase() in arrayOf("oz", "ounce", "ounces")) {
        result = num / ONE_OUNCE_TO_GRAM
        return if (result == 1.0) {
            "is $result ounce"
        } else {
            "is $result ounces"
        }
    } else {
        return "to $measure is impossible"
    }
}