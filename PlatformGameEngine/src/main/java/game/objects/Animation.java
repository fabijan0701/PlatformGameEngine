package game.objects;

import game.Game;
import tiled.TileLayer;
import tiled.TileSet;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Animation implements Runnable{

    private int[] frameSets;
    private double deltaTime;
    private int[] layerData;
    private Sprite container;


   public Animation(int[] frameSets, double deltaTime, Sprite container) {

       setFrameSets(frameSets);
       setDeltaTime(deltaTime);
   }

    public double getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(double deltaTime) {
        this.deltaTime = deltaTime;
    }

    public int[] getFrameSets() {
        return frameSets;
    }

    public void setFrameSets(int[] frameSets) {

       if (frameSets.length == 0) {
           Game.out.printWarning("Frame set array should not be empty");
           return;
       }

       this.frameSets = frameSets;
    }


    @Override
    public void run() {

        int[][] data = container.getLayer().getData();

        for (int frameId: getFrameSets()) {

           int row = frameId / container.getLayer().getWidth();
           int col = frameId % container.getLayer().getWidth();

           int realIndex = data[row][col];
           int firstG = container.getLayer().getData()[0][0];

           TileSet ts = Game.getActiveScene().getMap().getTargetTileSet(firstG);
           BufferedImage tile = ts.getTile(realIndex);
           container.setImage(tile);

            try {
                Thread.sleep((long) deltaTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Sprite getContainer() {
        return container;
    }

    public void setContainer(Sprite container) {
        this.container = container;
    }
}
