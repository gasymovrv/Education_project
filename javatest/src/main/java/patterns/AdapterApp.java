package patterns;

import java.io.FileNotFoundException;

public class AdapterApp {

    public static void main(String[] args) throws FileNotFoundException {
        //1-й способ через наследование
        VectorGraphicsInterface g1 = new VectorAdapterFromRaster();
        g1.drawLine();
        g1.drawSquare();
        //2-й способ через композицию
        VectorGraphicsInterface g2 = new VectorAdapterFromRaster2(new RasterGraphics());
        g2.drawLine();
        g2.drawSquare();
    }
}

//--------------------------------Adaptee (интерфейс который будем адаптировать)---------------------------------------
class RasterGraphics {
    void drawRasterLine() {
        System.out.println("Рисуем линию");
    }

    void drawRasterSquare() {
        System.out.println("Рисуем квадрат");
    }
}

//--------------------------------Adapter (интерфейс который будем использовать)---------------------------------------
interface VectorGraphicsInterface {
    void drawLine();

    void drawSquare();
}

//ConcreteAdapter 1. Реализация через наследование
class VectorAdapterFromRaster extends RasterGraphics implements VectorGraphicsInterface {
    public void drawLine() {
        drawRasterLine();
    }

    public void drawSquare() {
        drawRasterSquare();
    }
}

//ConcreteAdapter 2. Реализация через композицию
class VectorAdapterFromRaster2 implements VectorGraphicsInterface {
    RasterGraphics raster = null;//new RasterGraphics();

    public VectorAdapterFromRaster2(RasterGraphics raster) {
        this.raster = raster;
    }

    public void drawLine() {
        raster.drawRasterLine();
    }

    public void drawSquare() {
        raster.drawRasterSquare();
    }
}
