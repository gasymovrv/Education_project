package ru.gasymovrv.kotlintest.patterns

object MementoGame {

    @JvmStatic
    fun main(args: Array<String>) {
        val file = File()
        val game = Game()

        game.set("LVL_1", 30000)
        print(game)
        file.addSave(game.save())
        println(" - Сохранились")

        game.set("LVL_2", 55000)
        println(game)

        game.set("LVL_3", 80000)
        print(game)
        file.addSave(game.save())
        println(" - Сохранились")

        game.set("LVL_4", 120000)
        println(game)

        println()

        println("загружаемся из 1го сохранения")
        game.load(file.getSave(0))
        println(game)

        println("загружаемся из последнего сохранения")
        game.load(file.getLastSave())
        println(game)
    }
}

//Originator (объект, состояние которого будем сохранять в Memento)
class Game {

    //state
    private var level: String? = null
    private var ms = 0

    fun set(level: String?, ms: Int) {
        this.level = level
        this.ms = ms
    }

    //setMemento
    fun load(save: Save) {
        level = save.level
        ms = save.ms
    }

    //createMemento
    fun save(): Save {
        return Save(level, ms)
    }

    override fun toString(): String {
        return "Game [level=$level, ms=$ms]"
    }
}

//Memento
class Save(
    //state
    val level: String?,
    val ms: Int
)

//Caretaker
class File {

    private val save: MutableList<Save> = ArrayList()

    fun getLastSave() = save.last()
    fun getSave(i: Int) = save[i]

    fun addSave(save: Save) {
        this.save.add(save)
    }
}
