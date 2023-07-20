package tiled;

import java.awt.image.BufferedImage;

/**
 * Represents complete map from Tiled.
 */
public class TiledMap {
	
	protected String path;
	private int width;
	private int height;
	private int tileWidth;
	private int tileHeight;
	private TileSet[] tileSets;
	private TileLayer[] tileLayers;
	private ObjectGroup[] objectGroups;


	/**
	 * Protected constructor that prevents creating instances of
	 * this class.
	 */
	protected TiledMap() {}

	/**
	 * Returns path to parent folder of this map.
	 * @return String value that represents path.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets path to the current map
	 * @param path
	 */
	protected void setPath(String path) {
		this.path = path;
	}

	/**
	 * Returns width (number of tiles) of the map.
	 * @return number that represents width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets width (number of tiles) of the map.
	 * @param width
	 */
	protected void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Returns height (number of tiles) of the map.
	 * @return number that represents height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets height (number of tiles) of the map.
	 * @param height
	 */
	protected void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Returns width of the one tile.
	 * @return integer that represents width.
	 */
	public int getTileWidth() {
		return tileWidth;
	}

	/**
	 * Sets width of one tile.
	 * @param tileWidth integer that represents width.
	 */
	protected void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	/**
	 * Returns height of the one tile.
	 * @return integer that represents height.
	 */
	public int getTileHeight() {
		return tileHeight;
	}

	/**
	 * Sets height of one tile.
	 * @param tileHeight integer that represents height.
	 */
	protected void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

	/**
	 * Returns array that contains all TileSet object of this map.
	 * @return array that cinsists of TileSet objects.
	 */
	public TileSet[] getTileSets() {
		return tileSets;
	}

	/**
	 * Sets TileSet object array.
	 * @param tileSets
	 */
	protected void setTileSets(TileSet[] tileSets) {
		this.tileSets = tileSets;
	}

	/**
	 * Calculates total map height in pixels.
	 * @return integer that represents map height in pixels.
	 */
	public int getPixelHeight() {
		return this.getHeight() * this.getTileHeight();
	}

	/**
	 * Calculates total map width in pixels.
	 * @return integer that represents map width in pixels.
	 */
	public int getPixelWidth() {
		return this.getWidth() * this.getTileWidth();
	}

	/**
	 * Returns all TileLayer objects of this map as the array.
	 * @return array that contains all TileLayer objects.
	 */
	public TileLayer[] getTileLayers() {
		return tileLayers;
	}

	/**
	 * Sets array of TileSet objects
	 * @param layers array to be set.
	 */
	protected void setTileLayers(TileLayer[] layers) {
		this.tileLayers = layers;
	}

	/**
	 * Returns first TilLayer object in map with target name.
	 * @param name target name of TileLayer object
	 * @return TileLayer object with target name or null if TileLayer object with souch name
	 * does not exist.
	 */
	public TileLayer getTileLayer(String name) {

		for (TileLayer tl: getTileLayers()) {

			if (tl.getName() == name) {
				return  tl;
			}
		}

		return  null;
	}


	/**
	 * Returns first TilLayer object in map with target id.
	 * @param id target id of TileLayer object
	 * @return TileLayer object with target id or null if TileLayer object with souch id
	 * does not exist.
	 */
	public TileLayer getTileLayer(int id) {

		for (TileLayer tl: getTileLayers()) {

			if (tl.getId() == id) {
				return  tl;
			}
		}

		return  null;
	}

	/**
	 * Returns array that consists of all ObjectGroup objects of this map.
	 * @return ObjectGroup layers array.
	 */
	public ObjectGroup[] getObjectGroups() {
		return objectGroups;
	}

	/**
	 * Sets value as ObjectGroup array.
	 * @param objectGroups array to be set as value.
	 */
	protected void setObjectGroups(ObjectGroup[] objectGroups) {
		this.objectGroups = objectGroups;
	}

	/**
	 * Returns TileSet object that contains Tile image with the target id.
	 * @param id target id
	 * @return TileSet object that contains Tile image with the target id or null if such TileSet does not exist in map.
	 */
	public TileSet getTargetTileSet(int id) {

		TileSet[] tsArray = getTileSets();
		// 2
		// 1, 201, 215, 233, 224, 228, 234, 235, 243
		for (int i = 1; i < tsArray.length; i++) {

			TileSet current = tsArray[i];

			if (current.getFirstGId() == id) {
				return current;
			}
			else if (current.getFirstGId() > id) {
				return tsArray[i - 1];
			}

		}

		if (id > tsArray[tsArray.length - 1].getFirstGId()) {
			return tsArray[tsArray.length - 1];
		}

		return null;
	}

	/**
	 * Returns target ObjectLayer object that contains Tile image with the target id.
	 * @param firstGid represents id (because there is no frame sets).
	 * @return returns target object with specified id.
	 */
	public ObjectLayer getTargetObjectLayer(int firstGid) {

		for (ObjectGroup group: getObjectGroups()) {

			for (ObjectLayer layer: group.getObjectLayers()) {

				if (layer.getGid() == firstGid) {
					return layer;
				}
			}
		}

		return null;
	}


	/**
	 * Returns target TileLayer object if this object has property 'sprite-layer'
	 * and TileLayer object has equal name to target name. If such object is not
	 * found, method returns null value.
	 * @param name target sprite-layer name.
	 * @return TileLayer object or null if object is not found.
	 */
	public TileLayer getSpriteLayer(String name) {

		for (TileLayer tl: getTileLayers()) {
			if (tl.hasStringProperty("sprite-layer") && tl.getName().equals(name)) {
				return tl;
			}
		}
		return null;
	}
}
