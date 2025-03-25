package patterns

import java.io.FileNotFoundException

object AdapterApp {

    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        //1-й способ через наследование
        val g1: VectorGraphicsInterface = VectorAdapterFromRaster()
        g1.drawLine()
        g1.drawSquare()
        //2-й способ через композицию
        val g2: VectorGraphicsInterface = VectorAdapterFromRaster2(RasterGraphics())
        g2.drawLine()
        g2.drawSquare()
    }
}

//--------------------------------Adaptee (интерфейс который будем адаптировать)---------------------------------------
open class RasterGraphics {

    fun drawRasterLine() {
        println("Рисуем линию")
    }

    fun drawRasterSquare() {
        println("Рисуем квадрат")
    }
}

//--------------------------------Adapter (интерфейс который будем использовать)---------------------------------------
interface VectorGraphicsInterface {

    fun drawLine()
    fun drawSquare()
}

//ConcreteAdapter 1. Реализация через наследование
class VectorAdapterFromRaster : RasterGraphics(), VectorGraphicsInterface {

    override fun drawLine() {
        drawRasterLine()
    }

    override fun drawSquare() {
        drawRasterSquare()
    }
}

//ConcreteAdapter 2. Реализация через композицию
class VectorAdapterFromRaster2(raster: RasterGraphics?) :
    VectorGraphicsInterface {

    var raster: RasterGraphics? = null //new RasterGraphics();

    init {
        this.raster = raster
    }

    override fun drawLine() {
        raster!!.drawRasterLine()
    }

    override fun drawSquare() {
        raster!!.drawRasterSquare()
    }
}
