package javapro.generics

fun main() {

}

class GenericOut<out T : List<String>>(val list: T) {

}

class GenericIn<T : List<String>>(var list: T) {

}
