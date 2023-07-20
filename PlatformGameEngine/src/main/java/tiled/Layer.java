package tiled;

import java.util.HashMap;

public class Layer {

    private int id;
    private String name;
    private HashMap<String, Property> properties;

    /**
     * Default constructor that takes all 3 arguments
     * @param id
     * @param name
     * @param properties
     */
    public Layer(int id, String name, HashMap<String, Property> properties) {
        setId(id);
        setName(name);
        setProperties(properties);
    }

    /**
     * Returns id of the object
     * @return integer value that represents id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id to object value
     * @param id
     */
    protected void setId(int id) {
        this.id = id;
    }

    /**
     * Returns name of the map object
     * @return String that represents name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name to the object
     * @param name String that represents name.
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Returns properties as HashMap.
     */
    public HashMap<String, Property> getProperties() {
        return properties;
    }

    /**
     * Sets properties to layer.
     * @param properties
     */
    protected void setProperties(HashMap<String, Property> properties) {
        this.properties = properties;
    }
}
