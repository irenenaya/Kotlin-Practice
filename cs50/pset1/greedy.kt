/**
 * Created by irenenaya on 6/6/17.
 */

//package greedy
// extension functions for rounding
fun Float.round() : Float = (this + 0.5f).toInt().toFloat()
fun Double.round() : Double = (this + 0.5).toInt().toDouble()

tailrec fun getFloat(): Float {
    var ret : Float? = null

    // attempt to retrieve a float from input. toFloat() throws a NumberFormatException if input can't be
    // converted to a float so we catch and prompt for retry. ret will remain null if input is not valid.
    try {
        ret = readLine()?.toFloat()
    }
    catch (e: NumberFormatException) {
        print("Retry: ")
    }

    // return a float or recurse until ret not null
    return ret ?: getFloat()
}
// recursive function to get correct input from user
tailrec fun getChange() : Float {
    print("O hai, how much change do we owe? ")
    var ret = getFloat()
    if (ret < 0)  return getChange()

    return ret
}

fun main(vararg argv : String) {

    var change = (getChange() * 100).round().toInt()
    var coins = arrayOf(25,10,5,1)
    var total = 0
    for (i in coins) {
        total += change / i
        change %= i
    }

    println(total)
}