package ru.gasymovrv.kotlintest.coursera.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main() {
  //example1()
  //example2()
  //example3()
  //example4()
  //example5()
  //example6Coroutine()
  //example6Thread()
  //example7()
  //example8()
  example9()
  println("Main done")
}

//----------------------------- example 1 -------------------------------------------------

fun example1() = runBlocking { // this: CoroutineScope
  launch { // launch a new coroutine and continue
    delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
    println("World 1!") // print after delay
  }
  println("Hello") // main coroutine continues while a previous one is delayed
}

//----------------------------- example 2 -------------------------------------------------

fun example2() = runBlocking { // this: CoroutineScope
  launch { doWorld1() }
  println("Hello") // main coroutine continues while a previous one is delayed
}

// this is your first suspending function
suspend fun doWorld1() {
  delay(1000L)
  println("World!")
}

//----------------------------- example 3 -------------------------------------------------

// Sequentially executes doWorld followed by "Done"
fun example3() = runBlocking {
  doWorld2()
  println("Done")
}

// Concurrently executes both sections
suspend fun doWorld2() = coroutineScope { // this: CoroutineScope
  launch {
    delay(2000L)
    println("World 2")
  }
  launch {
    delay(1000L)
    println("World 1")
  }
  println("Hello")
}

//----------------------------- example 4 -------------------------------------------------

// Sequentially executes doWorld followed by "Done"
fun example4() = runBlocking {
  val job = launch { // launch a new coroutine and keep a reference to its Job
    delay(1000L)
    println("World!")
  }
  println("Hello")
  job.join() // wait until child coroutine completes
  println("Done")
}

//----------------------------- example 5 -------------------------------------------------

fun example5() = runBlocking {
  val job = launch { // launch a new coroutine and keep a reference to its Job
    delay(1000L)
    println("World!")
  }
  job.invokeOnCompletion { println("Done") }
  println("Hello")
}

//----------------------------- example 6 -------------------------------------------------
//Coroutines ARE light-weight
fun example6Coroutine() = runBlocking {

  repeat(300_000) { i ->// launch a lot of coroutines
    launch {
      delay(5000L)
      print("$i ")
    }
  }
}

//Threads ARE MOT light-weight
fun example6Thread() {

  repeat(300_000) { i ->// launch a lot of threads
    thread {
      Thread.sleep(5000L)
      print("$i ")
    }
  }
}

//----------------------------- example 7 -------------------------------------------------
fun example7() = runBlocking {
  val deferred: Deferred<Int> = async {
    loadData()
  }
  println("waiting...")
  println(deferred.await())
}

suspend fun loadData(): Int {
  println("loading...")
  delay(1000L)
  println("loaded!")
  return 42
}

//----------------------------- example 8 -------------------------------------------------
fun example8() = runBlocking {
  // Launch a concurrent coroutine to check if the main thread is blocked
  launch {
    for (k in 1..3) {
      println("I'm not blocked $k")
      delay(100)
    }
  }
  // Collect the flow
  simple().collect { value -> println(value) }
}

fun simple(): Flow<Int> = flow { // flow builder
  for (i in 1..3) {
    delay(100) // pretend we are doing something useful here
    emit(i) // emit next value
  }
}

//----------------------------- example 9 -------------------------------------------------
fun example9() = runBlocking<Unit> {
  val channel = Channel<String>()
  launch {
    channel.send("A1")//1
    channel.send("A2")//2
    log("Sender A done")
  }
  launch {
    delay(500)
    channel.send("B1")//3
    log("Sender B done")
    //channel.close()
  }
  launch {
    //если канал не закрыт, то мы тут зависнем до тех пор, пока не появится 4ый элемент
    //Но если закрыт, то упадем с ошибкой
    //repeat(4) { val message = channel.receive() }

    repeat(3) {
      val message = channel.receive()
      log("receiver1: $message")
    }
  }
  println("The end of example 9")
}

fun log(message: Any?) {
  println("[${Thread.currentThread().name}] $message")
}


