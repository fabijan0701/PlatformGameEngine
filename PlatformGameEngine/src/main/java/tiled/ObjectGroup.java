package tiled;

import java.util.HashMap;

/**
 * Represents ObjectGroup layer from Tiled.
 */
public class ObjectGroup extends Layer{

    private ObjectLayer[] objectLayers;

    /**
     * Default constructor that takes all 4 arguments.
     */
    public ObjectGroup(int id, String name, HashMap<String, Property> properties, ObjectLayer[] layers) {
        super(id, name, properties);

        this.setObjectLayers(layers);
    }

    /**
     * Returns array that contains all the ObjectLayer objects of this group.
     * @return array of ObjectLayer instances.
     */
    public ObjectLayer[] getObjectLayers() {
        return objectLayers;
    }

    /**
     * Sets value of ObjectLayer array.
     * @param objectLayers array to be set as value.
     */
    protected void setObjectLayers(ObjectLayer[] objectLayers) {
        this.objectLayers = objectLayers;
    }
}
