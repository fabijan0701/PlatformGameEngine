package tiled;

import java.util.HashMap;

/**
 * Represents TileLayer from Tiled. Extends MapObject.
 */
public class TileLayer extends MapObject{

    private int[][] data;

    /**
     * Default (and only) constructor that takes 6 arguments. Sets x and y values to 0px both.
     * @param id
     * @param name
     * @param width
     * @param height
     * @param visible
     * @param data
     */
    public TileLayer(int id, String name, int width, int height, boolean visible, HashMap<String, Property> properties, int[][] data) {
        super(id, name, 0, 0, width, height, visible, properties);

        setData(data);
    }

    /**
     * Returns layer data in the form of 2d integer array.
     * @return integer 2d data array
     */
    public int[][] getData() {
        return data;
    }

    /**
     * Sets layer data
     * @param data 2d integer array to be set as layer data.
     */
    protected void setData(int[][] data) {
        this.data = data;
    }

    /**
     * Returns whether TileSet object contains property with target String key.
     * @param property String key of target property
     * @return boolean value
     */
    public boolean hasStringProperty(String property) {
        return getProperties().containsKey(property);
    }

    /**
     * Returns whether TileLayer object has sprite layer property.
     */
    public boolean isSpriteLayer() {
        return this.hasStringProperty("sprite-layer");
    }
}
