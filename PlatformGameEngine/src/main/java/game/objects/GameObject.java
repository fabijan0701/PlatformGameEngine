package game.objects;

import game.physics.CollisionType;
import tiled.*;

import java.awt.image.BufferedImage;

public class GameObject {

    private int x;
    private int y;
    private int xOld;
    private int yOld;
    private int width;
    private int height;
    private TileLayer layer;
    private CollisionType collisionType;
    private BufferedImage image;

    public GameObject(int x, int y, int width, int height, TileLayer layer, CollisionType type) {

        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setLayer(layer);
        this.setxOld(x);
        this.setyOld(y);
        this.setCollisionType(type);
    }


    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public TileLayer getLayer() {
        return layer;
    }
    public void setLayer(TileLayer layer) {
        this.layer = layer;
    }

    public int getxOld() {
        return xOld;
    }
    public void setxOld(int xOld) {
        this.xOld = xOld;
    }

    public int getyOld() {
        return yOld;
    }
    public void setyOld(int yOld) {
        this.yOld = yOld;
    }

    public boolean isVisible() {
        return this.getLayer().isVisible();
    }
    public void setVisible(boolean visible) {
        this.getLayer().setVisible(visible);
    }

    public CollisionType getCollisionType() {
        return this.collisionType;
    }
    public void setCollisionType(CollisionType type) {
        this.collisionType = type;
    }
    public double getDistanceFrom(GameObject otherObject) {

        double absDX = Math.abs(otherObject.getX() - this.getX());
        double absDY = Math.abs(otherObject.getY() - this.getY());

        return Math.sqrt(Math.pow(absDX, 2) + Math.pow(absDY, 2));
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
