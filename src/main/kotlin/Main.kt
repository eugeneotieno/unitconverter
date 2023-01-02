const val WEIGHT = "weight"
const val LENGTH = "length"

enum class MetricSystem(
    val category: String,
    val plural: String,
    val singular: String,
    val metricName: String,
    val stdUnit: Double
) {
    G(WEIGHT, "grams", "gram", "g, gram, grams", 1.0),
    KG(WEIGHT, "kilograms", "kilogram", "kg, kilogram", 1000.0),
    MG(WEIGHT, "milligrams", "milligram", "mg, milligram, milligrams", 0.001),
    LB(WEIGHT, "pounds", "pound", "lb, pound, pounds", 453.592),
    OZ(WEIGHT, "ounces", "ounce", "oz, ounce, ounces", 28.3495),
    M(LENGTH, "meters", "meter", "m, meter, meters", 1.0),
    KM(LENGTH, "kilometers", "kilometer", "km, kilometer, kilometers", 1000.0),
    CM(LENGTH, "centimeters", "centimeter", "cm, centimeter, centimeters", 0.01),
    MM(LENGTH, "millimeters", "millimeter", "mm, millimeter, millimeters", 0.001),
    MI(LENGTH, "miles", "mile", "mi, mile, miles", 1609.35),
    YD(LENGTH, "yards", "yard", "yd, yard, yards", 0.9144),
    FT(LENGTH, "feet", "foot", "ft, foot, feet", 0.3048),
    IN(LENGTH, "inches", "inch", "in, inch, inches", 0.0254),
    NULL("", "", "", "", 0.0)
}

fun getMetricPlural(metricName: String): String {
    for (metric in MetricSystem.values()) {
        if (metric.metricName.contains(metricName.lowercase())) return metric.plural
    }
    return ""
}

fun getMetricCategory(metricName: String): String {
    for (metric in MetricSystem.values()) {
        if (metric.metricName.contains(metricName.lowercase())) return metric.category
    }
    return ""
}

fun getMetric(metricName: String): MetricSystem {
    for (metric in MetricSystem.values()) {
        if (metric.metricName.contains(metricName.lowercase())) return metric
    }
    return MetricSystem.NULL
}

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

            if (getMetricCategory(measure) == getMetricCategory(metric) &&
                getMetricCategory(measure) in arrayOf(WEIGHT, LENGTH)) {
                var inputMeasure = getMetric(measure).plural
                if (distance.toDouble() == 1.0) inputMeasure = getMetric(measure).singular
                val finalValue = getFinalConversion(distance.toDouble() * getMetric(measure).stdUnit, metric)
                println("${distance.toDouble()} $inputMeasure $finalValue")

                println()
            } else {
                if (getMetric(measure) == MetricSystem.NULL) {
                    if (getMetric(metric) == MetricSystem.NULL) {
                        println("Conversion from ??? to ??? is impossible")
                    } else {
                        println("Conversion from ??? to ${getMetricPlural(metric)} is impossible")
                    }
                } else if (getMetric(metric) == MetricSystem.NULL) {
                    if (getMetric(measure) == MetricSystem.NULL) {
                        println("Conversion from ??? to ??? is impossible")
                    } else {
                        println("Conversion from ??? to ${getMetricPlural(measure)} is impossible")
                    }
                } else {
                    println("Conversion from ${getMetricPlural(measure)} to ${getMetricPlural(metric)} is impossible")
                }

                println()
            }
        }
    } while (!exit)
}

fun getFinalConversion(num: Double, measure: String): String {
    val rs = num / getMetric(measure).stdUnit
    return if (rs == 1.0) {
        "is $rs ${getMetric(measure).singular}"
    } else {
        "is $rs ${getMetric(measure).plural}"
    }
}
