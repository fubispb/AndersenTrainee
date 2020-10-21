package patterns.adapter;

import java.io.FileNotFoundException;

public class AdapterPattern {

    public static void main(String[] args) {
        VectorGraphicsInterface g1 = new VectorAdapterFromRaster();
        g1.drawLine();
        g1.drawSquare();
        VectorGraphicsInterface g2 = new VectorAdapterFromRaster2(new RasterGraphics());
        g2.drawLine();
        g2.drawSquare();


    }
}

interface VectorGraphicsInterface {
    void drawLine();

    void drawSquare();
}

class RasterGraphics {
    void drawRasterLine() {
        System.out.println("Drawing line");
    }

    void drawRasterSquare() {
        System.out.println("Drawing square");
    }
}

class VectorAdapterFromRaster extends RasterGraphics implements VectorGraphicsInterface {
    public void drawLine() {
        drawRasterLine();
    }

    public void drawSquare() {
        drawRasterSquare();
    }
}

class VectorAdapterFromRaster2 implements VectorGraphicsInterface {
    private final RasterGraphics raster;

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