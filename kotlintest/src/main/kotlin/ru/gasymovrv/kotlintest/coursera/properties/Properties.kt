package ru.gasymovrv.kotlintest.coursera.properties

val foo1: Int = run {
  println("Calculating the answer...")
  42
} // creates lambda and invokes it immediately

val foo2: Int
  get() {
    println("Calculating the answer...")
    return 42
  }

//lazy property
val lazyValue: String by lazy {
  println("computed!")//we do not show it before we call 'lazyValue'
  "Hello!"
}

class LateInitExample {
  lateinit var lateinitVar: String //only var and non-nullable

  fun initializeLogic() {
    println("LateInitExample.lateinitVar.isInitialized=${::lateinitVar.isInitialized}")
    lateinitVar = "value"
    println("LateInitExample.lateinitVar.isInitialized=${::lateinitVar.isInitialized}")
  }
}

interface User {
  val nickname: String
}

interface Session {
  val user: User
}

class FacebookUser(val accountId: Int) : User {

  override val nickname = getFacebookName(accountId)
}

private fun getFacebookName(accountId: Int): String {
  println("getFacebookUserName")
  return "Nickname$accountId"
}

class SubscribingUser(val email: String) : User {

  override val nickname: String
    get() {
      println("getSubscribingUserName")
      return email.substringBefore('@')
    }
}

//cast property with custom getter
fun analyzeSession(session: Session) {
//  if (session.user is FacebookUser) {
//    println(session.user.accountId) //Smart cast to 'FacebookUser' is impossible, because 'session.user' is a property that has open or custom getter
//  }
  val user = session.user
  if (user is FacebookUser) {
    println(user.accountId)
  }
}

//property as an extension
val String.medianChar
  get(): Char? {
    println("Calculating...")
    return getOrNull(length / 2)
  }

var StringBuilder.lastCHar: Char
  get() = this[length - 1]
  set(value) {
    this.setCharAt(length - 1, value)
  }