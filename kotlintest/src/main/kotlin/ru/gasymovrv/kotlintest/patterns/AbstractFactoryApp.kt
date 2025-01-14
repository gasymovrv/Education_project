package ru.gasymovrv.kotlintest.patterns

import kotlin.math.pow
import kotlin.math.sqrt

object AbstractFactoryApp {

    @JvmStatic
    fun main(args: Array<String>) {
        val factory = DeviceFactory.getFactoryByCountryCode("RU")

        val m = factory.mouse
        val k = factory.keyboard
        val t = factory.touchpad

        m.click()
        k.print()
        t.track(10, 35)
    }
}

//-------------------------------abstract products----------------------------------
interface Mouse {

    fun click()
}

interface Keyboard {

    fun print()
}

interface Touchpad {

    fun track(deltaX: Int, deltaY: Int)
}

//-------------------------------concrete products 1----------------------------------
class RuMouse : Mouse {

    override fun click() {
        println("Щелчок мышью")
    }
}

class RuKeyboard : Keyboard {

    override fun print() {
        print("Печатаем строку")
    }
}

class RuTouchpad : Touchpad {

    override fun track(deltaX: Int, deltaY: Int) {
        val s = sqrt(deltaX.toDouble().pow(2.0) + deltaY.toDouble().pow(2.0)).toInt()
        println("Передвинулись на $s пикселей")
    }
}

//-------------------------------concrete products 2----------------------------------
class EnMouse : Mouse {

    override fun click() {
        println("Mouse click")
    }
}

class EnKeyboard : Keyboard {

    override fun print() {
        print("Print")
    }
}

class EnTouchpad : Touchpad {

    override fun track(deltaX: Int, deltaY: Int) {
        val s = sqrt(deltaX.toDouble().pow(2.0) + deltaY.toDouble().pow(2.0)).toInt()
        println("Moved $s pixels")
    }
}

//-------------------------------abstract factory----------------------------------
interface DeviceFactory {

    val mouse: Mouse
    val keyboard: Keyboard
    val touchpad: Touchpad

    companion object {

        fun getFactoryByCountryCode(lang: String): DeviceFactory {
            return when (lang) {
                "RU" -> RuDeviceFactory()
                "EN" -> EnDeviceFactory()
                else -> throw RuntimeException("Unsupported Country Code: $lang")
            }
        }
    }
}

//-------------------------------concrete factories----------------------------------
class EnDeviceFactory : DeviceFactory {

    override val mouse: Mouse
        get() = EnMouse()
    override val keyboard: Keyboard
        get() = EnKeyboard()
    override val touchpad: Touchpad
        get() = EnTouchpad()
}

class RuDeviceFactory : DeviceFactory {

    override val mouse: Mouse
        get() = RuMouse()
    override val keyboard: Keyboard
        get() = RuKeyboard()
    override val touchpad: Touchpad
        get() = RuTouchpad()
}
