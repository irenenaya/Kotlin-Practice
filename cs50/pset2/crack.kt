/**
 * Created by irenenaya on 6/7/17.
 */

//package crack

// library for UnixCrypt
import org.apache.commons.codec.digest.UnixCrypt

// Creates a list of only alphabetic chars
fun asciichars(): List<Char> {
    return ('A'..'z').filter{ it.isLetter()}
}

// recursive function that generates strings and checks if they match the encrypted password
fun recfindStrings(pwd : String, salt : String, i : Int, acc : String, asc : List<Char>) :Boolean {
    if (acc.length == i ) {
        if( UnixCrypt.crypt(acc, salt) == pwd ) {
            println(acc)
            return true
        }
        return false
    }
    for (c in asc) {
         if (recfindStrings(pwd, salt, i, acc + c, asc)) return true
    }
    return false
}

// iterates the lengths of valid passwords to create and find matching ones
fun findpass(pwd: String, salt : String, asc : List<Char>) : Boolean {
    for (i in 1..4) {
        if (recfindStrings(pwd, salt, i, "", asc)) return true
        println(i)
    }
    return false
}

fun main(vararg argv : String) {
    if (argv.size != 1) {
        println("Usage: crack.CrackKt hash")
        return
    }
    val start = System.currentTimeMillis() // to benchmark time of execution
    val salt = argv[0].substring(0,2)
    val asciis = asciichars()

    if (! findpass(argv[0], salt, asciis)) println("Not found")
    val end = System.currentTimeMillis()

    println("Time: " + (end - start))

}