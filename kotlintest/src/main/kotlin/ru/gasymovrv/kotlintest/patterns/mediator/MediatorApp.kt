package ru.gasymovrv.kotlintest.patterns.mediator

object MediatorApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val chat = TextChat()

        val admin: User = Admin(chat, "Руслан")
        val u1: User = SimpleUser(chat, "Ваня")
        val u2: User = SimpleUser(chat, "Вова")
        val u3: User = SimpleUser(chat, "Ахмед")

        chat.addUser(admin)
        chat.addUser(u1)
        chat.addUser(u2)
        chat.addUser(u3)

        admin.outbox("Привет")
        println()
        u1.outbox("Хай")
        println()
        u2.outbox("Прив")
        println()
        u3.outbox("Салам")
    }
}

//--------------------------------------Component или Colleague----------------------------------
//Component или Colleague
abstract class User(private val chat: Chat, var name: String) {

    //компонент отправляет сообщение посреднику
    fun outbox(message: String) {
        chat.sendMessage(message, this)
    }

    //компонент получает сообщение от посредника
    abstract fun inbox(message: String)
}

//ConcreteComponent или ConcreteColleague
class Admin(chat: Chat, name: String) :
    User(chat, name) {

    override fun inbox(message: String) {
        println("Администратор " + name + " получает сообщение '" + message + "'")
    }
}

//ConcreteComponent или ConcreteColleague
class SimpleUser(chat: Chat, name: String) :
    User(chat, name) {

    override fun inbox(message: String) {
        println("Пользователь " + name + " получает сообщение '" + message + "'")
    }
}

//--------------------------------------------Mediator----------------------------------------
//Mediator
interface Chat {

    fun sendMessage(message: String, user: User)
}

//ConcreteMediator
class TextChat : Chat {

    //clients
    private var admin: Admin? = null
    private val users: MutableList<SimpleUser> = ArrayList()

    fun addUser(u: User?) {
        if (u is SimpleUser) {
            users.add(u)
        } else if (u is Admin && admin == null) {
            this.admin = u
        } else {
            throw RuntimeException("Админ уже есть")
        }
    }

    override fun sendMessage(message: String, user: User) {
        //транслируем сообщения от user всем кроме user
        if (user is Admin) {
            for (u in users) {
                u.inbox(user.name + "(Админ): " + message)
            }
        }
        if (user is SimpleUser) {
            for (u in users) {
                if (u !== user) u.inbox(user.name + ": " + message)
            }
            admin!!.inbox(user.name + ": " + message)
        }
    }
}
