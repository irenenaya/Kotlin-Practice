/**
 * Created by irenenaya on 6/6/17.
 */

tailrec fun getInt(): Int {
    var ret : Int? = null
    try {
        ret = readLine()?.toInt()
    }
    catch (e: NumberFormatException) {
        print("Retry: ")
    }
    return ret ?: getInt()
}

fun main(args: Array<String>) {
    var height : Int
    do {
        print("Height: ")
        height = getInt()
    } while (height !in 0..23)

    for (i in 1..height) {
        for (j in 1..height - i) print(" ")
        for (j in 0..i) print("#")
        print('\n')
    }
}