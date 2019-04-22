import kotlin.math.round
import kotlin.random.Random

fun main(args: Array<String>) {
    var iterations = 1
    var seed = 1
    var verb = false
    if (args.isNotEmpty()) {
        iterations = args[1].toInt()
    }
    if (args.size > 2) {
        seed = args[3].toInt()
    }
    if (args.size > 4) {
        verb = args[5].toBoolean()
    }

    val names: Array<String> = arrayOf("pomposity", "learning_curve", "optimism", "atleast", "done_percent", "productivity_new",
        "productivity_exp", "d", "ep", "nprod", "np", "ts", "to", "r")
    val mc = MonteCarlo(names)
    mc.generator(iterations, verb, seed)
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

class MonteCarlo(names: Array<String>) {

    val builder: AttributeBuilder = AttributeBuilder(names)


    fun generator(iters: Int, verbose: Boolean, seed: Int) {
        for (i in 1..iters) {
            var row = "{"
            builder.attrs.forEach { a ->
                val min = a.component2().get("min").toString().toDouble()
                val max = a.component2().get("max").toString().toDouble()
                val r = Random(seed).nextDouble(min, max).round(2)
                row += "'" + (a.component1() + "': " + r + ", ")
            }
            row += "'verbose': ${verbose.toString().capitalize()}}"
            println(row)
        }
    }
}

class AttributeBuilder(fields: Array<String>) {
    var attrs: MutableMap<String, Map<String, Any>> = mutableMapOf()
    init {
        for (f in fields) {
            this.attrs.put(f, range(f))
        }
    }

    fun range(field: String): Map<String, Any> {
        var min: Any = 0
        var max: Any = 0
        when {
            field.equals("pomposity") -> {
                min = 0
                max = 100
            }
            field.equals("learning_curve") -> {
                min = 1
                max = 100
            }
            field.equals("optimism") -> {
                min = 0.1
                max = 100
            }
            field.equals("atleast") -> {
                min = 0
                max = 10
            }
            field.equals("done_percent") -> {
                min = 0
                max = 10
            }
            field.equals("productivity_new") -> {
                min = 0
                max = 1
            }
            field.equals("productivity_exp") -> {
                min = 1
                max = 10
            }
            field.equals("d") -> {
                min = 0
                max = 1
            }
            field.equals("ep") -> {
                min = 1
                max = 10
            }
            field.equals("nprod") -> {
                min = 0.1
                max = 10
            }
            field.equals("np") -> {
                min = 1
                max = 30
            }
            field.equals("ts") -> {
                min = 1
                max = 100
            }
            field.equals("to") -> {
                min = 1
                max = 100
            }
            field.equals("r") -> {
                min = 1
                max = 1000
            }

        }
        return mapOf("min" to min, "max" to max)
    }
}
