package game.objects;

import game.physics.CollisionType;
import tiled.TileLayer;


public class Sprite extends GameObject {

    public Sprite(int x, int y, int width, int height, TileLayer layer, CollisionType type) {
        super(x, y, width, height, layer, type);
    }
}
