/**
 * Created by irenenaya on 6/7/17.
 */
package caesar

// extension function for safely converting string to Int. If the string has non numerical characters, the exception is
// caught and we return null (instead of crashing). For Kotlin 1.1 and onwards, toIntOrNull() can be used instead
fun String.safeToInt() : Int? {
    var ret : Int?
    try {
        ret = this.toInt()
    }
    catch (e: NumberFormatException) { return null }
    return ret
}

fun encrypt(i : Char, key : Int) : Char {
    when {
        !i.isLetter() -> return i
        i.isUpperCase() -> return ((i.toInt() - 'A'.toInt() + key) % 26 + 'A'.toInt()).toChar()
        else -> return ((i.toInt() - 'a'.toInt() + key) % 26 + 'a'.toInt()).toChar()
    }
}
fun main  (vararg argv: String)  {

    // error checking for no command line
    if (argv.size != 1) {
        println("Usage: caesar [key]")
        return
    }
    val key = argv[0].safeToInt()
    // error checking: key == null if user entererd non numeric key
    if (key == null) {
        println("Invalid key")
        return
    }
    print("plaintext: ")
    val plaintext = readLine()
    if (plaintext == null) {
        println("\n Error reading plaintext")
        return
    }
    print("ciphertext: ")
    for (i in plaintext) {
        print(encrypt(i, key))
    }
    print('\n')
}