package tiled;

import java.util.HashMap;

/**
 * Class that represents any object that can be found on Tiled map.
 */
public class MapObject extends Layer {


    private int width;
    private int height;
    private boolean visible;

    private int x;

    private int y;

    /**
     * Default constructor.
     * @param id
     * @param name
     * @param width
     * @param height
     * @param visible
     */
    public MapObject(int id, String name, int x, int y, int width, int height, boolean visible, HashMap<String, Property> properties) {
        super(id, name, properties);

        this.setWidth(width);
        this.setHeight(height);
        this.setVisible(visible);
        this.setX(x);
        this.setY(y);
    }

    /**
     * Returns width (number of tiles) of the layer objects
     * @return integer that represents horizontal number of tiles.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width to map object
     * @param width integer that represents width
     */
    protected void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns height (number of tiles) of the map object.
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height to the current tiled object.
     * @param height integer that represents height
     */
    protected void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns whether map object is visible.
     * @return boolean value that represents visibility.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets map object to be visible or not in the game.
     * @param visible boolean value that represents visibility
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Returns x coordinate (in pixels) of the current MapObject.
     * @return integer value that represents x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets value to the x coordinate (in pixels) of the current map
     * @param x represents value to be set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns y coordinate (in pixels) of the current MapObject.
     * @return integer value that represents y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets value to the y coordinate (in pixels) of the current map
     * @param y represents value to be set.
     */
    public void setY(int y) {
        this.y = y;
    }
}
