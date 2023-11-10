import java.lang.IllegalStateException

fun main(args: Array<String>) {
    println("Hello World!")

    val age = 10L
    var name = "롱"

    println(String.format("age : %d, name : %s", age, name))

}

fun startWithA1(str: String?) :Boolean {
    return str?.startsWith("A")
        ?: throw IllegalStateException("null")
//    if (str == null) {
//        throw IllegalStateException("null")
//    }
//    return str.startsWith("A")
}

fun startWithA2(str: String?) :Boolean? {
    return str?.startsWith("A")
//    if (str == null) {
//        return null
//    }
//    return str.startsWith("A")
}

fun startWithA3(str: String?) :Boolean {
    return str?.startsWith("A") ?: false
//    if (str == null) {
//        return false
//    }
//    return str.startsWith("A")
}

fun startWith(str: String?): Boolean {
    return str!!.startsWith("A")
}