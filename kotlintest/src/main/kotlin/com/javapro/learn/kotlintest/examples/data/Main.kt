package com.javapro.learn.kotlintest.examples.data

fun main() {
    val person = Person(10, "Vasya")
    val copy = person.copy(name = "Oleg")
    println(copy)

    person.age = 3
    person.name = "Vasiliy"
    println(person)

    val (age, name) = person
    println(age)
    println(name)
}