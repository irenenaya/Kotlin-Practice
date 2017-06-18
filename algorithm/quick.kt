/**
 * Created by irenenaya on 6/16/17.
 */

/**
 * Recursive implementation of quicksort that uses a random element as a pivot, instead
 * of using the first (or last) element.
 * It uses an extension function, partitionAroundPivot() that splits the list in 3 parts, with
 * the pivot in the center, so it works for any method used to choose pivot, and it works for lists with
 * repeated values.
 * It takes any Comparable type, that is any class that inherits from Comparable and has a compareTo()
 * defined.
 * It also works for Map and any other container that takes Pairs, as long as a comparison function is
 * passed to define the comparison.
 *
 */

/** ------------------------------------------------------------------------------------------------
 * Helper class and extension functions for partitioning around the pivot
 *------------------------------------------------------------------------------------------------ */
private data class  Three<T>(var left : List<T>, var center : List<T>, var right : List<T>)

private fun <T : Comparable<T>> List<T>.partitionAroundPivot(pivot : T) : Three<T>{
    val ret = Three<T>(emptyList(), emptyList(), emptyList())
    for (i in this) {
        if (i < pivot) ret.left += i
        else if (i > pivot) ret.right += i
        else ret.center +=i
    }
    return ret
}

// Overloaded partition function that takes a predicate. The predicate must return a negative value, zero,
// or a positive value, depending on the sorting order
private fun <T  ,U> List<Pair<T, U>>
        .partitionAroundPivot(pivot : Pair<T, U>, comp : ((x : Pair<T,U>, y : Pair<T,U>) -> Int))
        : Three<Pair<T,U>>{
    val ret = Three<Pair<T,U>>(emptyList(), emptyList(), emptyList())
    for (i in this) {
        if (comp(i, pivot) < 0) ret.left += i
        else if (comp(i, pivot) > 0) ret.right += i
        else ret.center +=i
    }
    return ret
}
/*-------------------------------------------------------------------------------------------------*/

/**-------------------------------------------------------------------------------------------------
 * Quicksort overloaded functions. The first function takes a List<T>, the second one takes a List
 * of Pairs<T,U> and a comparison function to define how the elements should be sorted
 --------------------------------------------------------------------------------------------------*/

fun <T: Comparable<T>> quickSortRec(data : List<T>) : List<T> = when  {
    data.size < 2 -> data
    else -> {
        val pivot  = data[(Math.random() * data.size ).toInt()]
        val (left, center, right) = data.partitionAroundPivot( pivot)
        quickSortRec(left) + center + quickSortRec(right)
    }
}

fun <T,U> quickSortRec(data : List<Pair<T,U>>, comp : ( (x : Pair<T,U>, y : Pair<T,U>) -> Int))
        : List<Pair<T,U>> =
        when {
            data.size < 2 -> data
            else -> {
                val pivot = data[(Math.random() * data.size).toInt()]
                val (left, center, right) = data.partitionAroundPivot(pivot, comp)
                quickSortRec(left, comp) + center + quickSortRec(right, comp)
            }
        }

fun <T : Comparable<T>> quickSortRec(data : Set<T>) = quickSortRec(data.toList()).toSet()
fun <T, U> quickSortRec(data : Map<T, U>, comp : ( (x : Pair<T,U>, y : Pair<T,U>) -> Int)) =
        quickSortRec(data.toList(), comp).toMap()

/*------------------------------------------------------------------------------------------------*/


/**------------------------------------------------------------------------------------------------
 * Test and usage
 *------------------------------------------------------------------------------------------------*/

 // Custom class inherits from Comparable and has overriden the compareTo operator
data class Person(val name: String, val age: Int, val height: Double) : Comparable<Person> {
    override operator fun compareTo(other : Person) : Int = this.name.compareTo(other.name)
}

fun main (vararg args : String) {
    val xs = mutableListOf("hello", "Nice",  "of", "the", "earth",  "says", "hi", "to", "all", "of", "you")
    val xx = quickSortRec(xs)
    println("Mutable List of strings")
    for (y in xx) println(y)

    // custom object
    val p1 = Person("john", 43, 1.5)
    val q1 = Person("mary", 22, 1.65)
    val px = listOf(p1, q1)
    val pw = quickSortRec(px)
    println("\nList of Custom Objects")
    for (p in pw) println("${p.name}, ${p.age}, ${p.height}")

    // Map
    val ma = mapOf(Pair("a", 3.2), Pair("c", 4.0), Pair("d", 1.1), Pair("e", 0.5), Pair("a", 1.2))
    val mx = quickSortRec(ma,
            { x : Pair<String,Double>, y : Pair<String,Double> ->
                if (x.first < y. first) -1 else if (x.first > y.first) 1 else 0})
    println("\nMap: ")
    for (m in mx) println("${m.key}, ${m.value}")

    // list of Pairs
    println("\nList of Pairs")
    val li = listOf(Pair("a", 2.2), Pair("D", 4.5))
    val ys = quickSortRec(li,
            { x : Pair<String,Double>, y : Pair<String,Double> ->
                if (x.second < y. second) -1 else if (x.second > y.second) 1 else 0})
    for (y in ys) println("${y.first} , ${y.second}")
}