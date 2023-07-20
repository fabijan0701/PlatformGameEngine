package usr.obj;

import game.objects.Sprite;
import game.physics.CollisionType;
import tiled.TileLayer;

public class MyObject extends Sprite {

    public MyObject(int x, int y, int width, int height, TileLayer layer) {
        super(x, y, width, height, layer, CollisionType.SQUARE);

        this.setVisible(true);

    }



}
