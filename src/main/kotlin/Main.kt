const val WEIGHT = "weight"
const val LENGTH = "length"
const val TEMPERATURE = "temperature"

enum class MetricSystem(
    val category: String,
    val plural: String,
    val singular: String,
    val abbreviation: String,
    val other: String,
    val stdUnit: Double
) {
    G(WEIGHT, "grams", "gram", "g", "", 1.0),
    KG(WEIGHT, "kilograms", "kilogram", "kg", "", 1000.0),
    MG(WEIGHT, "milligrams", "milligram", "mg", "", 0.001),
    LB(WEIGHT, "pounds", "pound", "lb", "", 453.592),
    OZ(WEIGHT, "ounces", "ounce", "oz", "", 28.3495),
    M(LENGTH, "meters", "meter", "m", "", 1.0),
    KM(LENGTH, "kilometers", "kilometer", "km", "", 1000.0),
    CM(LENGTH, "centimeters", "centimeter", "cm", "", 0.01),
    MM(LENGTH, "millimeters", "millimeter", "mm", "", 0.001),
    MI(LENGTH, "miles", "mile", "mi", "", 1609.35),
    YD(LENGTH, "yards", "yard", "yd", "", 0.9144),
    FT(LENGTH, "feet", "foot", "ft", "", 0.3048),
    IN(LENGTH, "inches", "inch", "in", "", 0.0254),
    C(TEMPERATURE, "degrees Celsius", "degree Celsius", "c", "dc,celsius", 0.0),
    F(TEMPERATURE, "degrees Fahrenheit", "degree Fahrenheit", "f", "fahrenheit,df", 0.0),
    K(TEMPERATURE, "kelvins", "kelvin", "k", "", 0.0),
    NULL("", "", "", "", "", 0.0)
}

fun getMetricPlural(metricName: String): String {
    for (metric in MetricSystem.values()) {
        val names = mutableListOf(metric.plural.lowercase(), metric.singular.lowercase(), metric.abbreviation)
        if (metric.other.contains(",")) {
            names += metric.other.split(",")
        } else {
            if (metric.other.isNotBlank()) {
                names.add(metric.other)
            }
        }
        if (metricName.lowercase() in names) return metric.plural
    }
    return ""
}

fun getMetricCategory(metricName: String): String {
    for (metric in MetricSystem.values()) {
        val names = mutableListOf(metric.plural.lowercase(), metric.singular.lowercase(), metric.abbreviation)
        if (metric.other.contains(",")) {
            names += metric.other.split(",")
        } else {
            if (metric.other.isNotBlank()) {
                names.add(metric.other)
            }
        }
        if (metricName.lowercase() in names) return metric.category
    }
    return ""
}

fun getMetric(metricName: String): MetricSystem {
    for (metric in MetricSystem.values()) {
        val names = mutableListOf(metric.plural.lowercase(), metric.singular.lowercase(), metric.abbreviation)
        if (metric.other.contains(",")) {
            names += metric.other.split(",")
        } else {
            if (metric.other.isNotBlank()) {
                names.add(metric.other)
            }
        }
        if (metricName.lowercase() in names) return metric
    }
    return MetricSystem.NULL
}

fun main() {
    var exit = false
    do {
        print("Enter what you want to convert (or exit): ")
        val action = readln()

        if (action.isBlank()) {
            println("Parse error\n")
        } else {
            if (action == "exit") {
                exit = true
            } else {
                try {
                    val qty = action.split(" ")[0]
                    val quantity = qty.toDouble()
                    val str = action.replace(qty, "").trim()
                    val query = str.split(" ")

                    val convertFrom: String = if (query[0].lowercase().contains("degree")) {
                        query[0] + " " + query[1]
                    } else {
                        query[0]
                    }

                    val convertTo: String = if (query[query.size -2].lowercase().contains("degree")) {
                        query[query.size -2] + " " + query[query.size -1]
                    } else {
                        query[query.size -1]
                    }

                    evaluate(quantity, convertFrom, convertTo)
                } catch (e: NumberFormatException) {
                    println("Parse error\n")
                }
            }
        }

    } while (!exit)
}

fun evaluate(quantity: Double, convertFrom: String, convertTo: String) {
    if (getMetricCategory(convertFrom) == getMetricCategory(convertTo) &&
        getMetricCategory(convertFrom) in arrayOf(WEIGHT, LENGTH)) {

        if (quantity < 0) {
            if (getMetricCategory(convertFrom) == WEIGHT) {
                println("Weight shouldn't be negative.\n")
            } else {
                println("Length shouldn't be negative.\n")
            }
        } else {
            var inputMeasure = getMetric(convertFrom).plural
            if (quantity == 1.0) inputMeasure = getMetric(convertFrom).singular
            val finalValue = getFinalConversion(quantity * getMetric(convertFrom).stdUnit, convertTo)
            println("$quantity $inputMeasure $finalValue")

            println()
        }
    } else if (getMetricCategory(convertFrom) == getMetricCategory(convertTo) &&
        getMetricCategory(convertFrom) == TEMPERATURE) {

        convertTemperature(quantity, convertFrom, convertTo)
        println()
    } else {
        if (getMetric(convertFrom) == MetricSystem.NULL) {
            if (getMetric(convertTo) == MetricSystem.NULL) {
                println("Conversion from ??? to ??? is impossible")
            } else {
                println("Conversion from ??? to ${getMetricPlural(convertTo)} is impossible")
            }
        } else if (getMetric(convertTo) == MetricSystem.NULL) {
            if (getMetric(convertFrom) == MetricSystem.NULL) {
                println("Conversion from ??? to ??? is impossible")
            } else {
                println("Conversion from ${getMetricPlural(convertFrom)} to ??? is impossible")
            }
        } else {
            println("Conversion from ${getMetricPlural(convertFrom)} to ${getMetricPlural(convertTo)} is impossible")
        }

        println()
    }
}

fun getFinalConversion(num: Double, convertTo: String): String {
    val rs = num / getMetric(convertTo).stdUnit
    return if (rs == 1.0) {
        "is $rs ${getMetric(convertTo).singular}"
    } else {
        "is $rs ${getMetric(convertTo).plural}"
    }
}

fun convertTemperature(num: Double, convertFrom: String, convertTo: String) {

    val result: Double
    var outputMeasure: String
    var inputMeasure = getMetric(convertFrom).plural
    if (num == 1.0) inputMeasure = getMetric(convertFrom).singular

    if (getMetric(convertFrom) == MetricSystem.C) {
        if (getMetric(convertTo) == MetricSystem.F) {
            result = (num * 9/5) + 32
            outputMeasure = getMetric(convertTo).plural
            if (result == 1.0) outputMeasure = getMetric(convertTo).singular
        } else if (getMetric(convertTo) == MetricSystem.K) {
            result = num + 273.15
            outputMeasure = getMetric(convertTo).plural
            if (result == 1.0) outputMeasure = getMetric(convertTo).singular
        } else {
            result = num
            outputMeasure = inputMeasure
        }
    } else if (getMetric(convertFrom) == MetricSystem.F) {
        if (getMetric(convertTo) == MetricSystem.C) {
            result = (num - 32) * 5/9
            outputMeasure = getMetric(convertTo).plural
            if (result == 1.0) outputMeasure = getMetric(convertTo).singular
        } else if (getMetric(convertTo) == MetricSystem.K) {
            result = (num - 32) * 5/9 + 273.15
            outputMeasure = getMetric(convertTo).plural
            if (result == 1.0) outputMeasure = getMetric(convertTo).singular
        } else {
            result = num
            outputMeasure = inputMeasure
        }
    } else {
        if (getMetric(convertTo) == MetricSystem.C) {
            result = num - 273.15
            outputMeasure = getMetric(convertTo).plural
            if (result == 1.0) outputMeasure = getMetric(convertTo).singular
        } else if (getMetric(convertTo) == MetricSystem.F) {
            result = (num - 273.15) * 9/5 + 32
            outputMeasure = getMetric(convertTo).plural
            if (result == 1.0) outputMeasure = getMetric(convertTo).singular
        } else {
            result = num
            outputMeasure = inputMeasure
        }
    }

    println("$num $inputMeasure is $result $outputMeasure")
}
