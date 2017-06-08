/**
 * Created by irenenaya on 6/7/17.
 */

package vigenere

// small extension function to check if all chars in a String are alphabetical
fun String.allAlpha() : Boolean {
    for (i in this) {
        if (! i.isLetter()) return false
    }
    return true
}
// function to apply encryption formula
fun converted(c : Char, k : Char) : Char {
    when {
        c.isUpperCase() -> return ((c.toInt() - 'A'.toInt() + k.toInt() - 'a'.toInt()) % 26 + 'A'.toInt()).toChar()
        else -> return ((c.toInt() - 'a'.toInt() + k.toInt() - 'a'.toInt()) % 26 + 'a'.toInt()).toChar()
    }
}
// tail recursive encryption function. If the plaintext is not alpha, the key is not increased and the character is appended as is
tailrec fun encrypt(p: String, k : String, indP : Int, indK: Int, acc : String) : String {
    if (indP == p.length) return acc
    when {
        ! p[indP].isLetter() -> return encrypt(p, k, indP+1, indK, acc+p[indP])
        else -> return encrypt(p, k, indP+1, (indK + 1) % k.length, acc + converted(p[indP], k[indK]))
    }
}

fun main (vararg argv : String) {
    if (argv.size == 0) {
        println("Usage: vigenere [key]")
        return
    }
    if (! argv[0].allAlpha()) {
        println("Key must be alphabetical")
        return
    }

    val key = argv[0].toLowerCase()

    print("plaintext: ")
    val plaintext = readLine()
    if (plaintext == null) return
    print("ciphertext: ")
    println(encrypt(plaintext, key, 0, 0, ""))
}