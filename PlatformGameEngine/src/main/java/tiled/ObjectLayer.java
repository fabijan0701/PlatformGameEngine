package tiled;

import java.util.HashMap;

/**
 * Represents one object layer from Tiled. Extends MapObject.
 * The crucial difference between this class and TileLayer is
 * that ObjectLayer object gets starting position on map
 * from Tiled.
 */
public class ObjectLayer extends MapObject {

    private int gid;

    /**
     * * Default and only constructor.
     */
    public ObjectLayer(int id, String name, int gId, int x, int y, int width, int height,
                       boolean visible, HashMap<String, Property> properties) {

        super(id, name, x, y, width, height, visible, properties);

        this.setGid(gId);
    }

    /**
     * Returns value of gId from Tiled.
     * @return integer that represents gId from Tiled.
     */
    public int getGid() {
        return gid;
    }

    /**
     * Sets value to Tiled gId parameter.
     * @param gid represents integer that will be set as value.
     */
    protected void setGid(int gid) {
        this.gid = gid;
    }
}
