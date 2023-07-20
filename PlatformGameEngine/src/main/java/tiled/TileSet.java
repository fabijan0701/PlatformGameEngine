package tiled;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Represents TileSet object from Tiled.
 */
public class TileSet {
	
	private int firstGid;
	private String name;
	private int tileWidth;
	private int tileHeight;
	private int tileCount;
	private int columns;
	private BufferedImage image;
	
	
	public TileSet(int firstGid, String name, int tileWidth, int tileHeight, int tileCount, int columns,
				   String imageSrc) {

		this.firstGid = firstGid;
		this.name = name;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tileCount = tileCount;
		this.columns = columns;
		
		
		try {
			this.image = TiledData.getImage(imageSrc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public int getFirstGId() {
		return firstGid;
	}
	protected void setFirstGId(int id) {
		this.firstGid = id;
	}
	public String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	public int getTileWidth() {
		return tileWidth;
	}
	protected void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}
	public int getTileHeight() {
		return tileHeight;
	}
	protected void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}
	public int getTileCount() {
		return tileCount;
	}
	protected void setTileCount(int tileCount) {
		this.tileCount = tileCount;
	}
	public int getColumns() {
		return columns;
	}
	protected void setColumns(int columns) {
		this.columns = columns;
	}
	public BufferedImage getImage() {
		return image;
	}
	protected void setImage(BufferedImage image) {
		this.image = image;
	}

	
	@Override
	public String toString() {
		String output = "";
		
		output += "Id: " + this.getFirstGId() + "\n";
		output += "Name: " + this.getName() + "\n";
		output += "Tiles: " + this.getTileCount() + "\n";
		output += "Tile width: " + this.getTileWidth() + "\n";
		output += "Tile height: " + this.getTileHeight() + "\n";
		output += "Image: " + this.getImage() + "\n";
		
		return output;
	}


	/**
	 * Returns BufferedImage object that represents tile.
	 * @param id represents id from export file (in layer data).
	 * @return BufferedImage that represents tile.
	 */
	public BufferedImage getTile(int id) {

		int realId = id - firstGid;

		int row = realId / getColumns();
		int col = realId % getColumns();

		/*
		System.out.println("Columns: " + getColumns());
		System.out.println("Real id: " + realId);
		System.out.println("Row: " + row);
		System.out.println("Col: " + col);
		 */

		return getImage().getSubimage(
				(col) * getTileWidth(),
				(row) * getTileHeight(),
				getTileWidth(),
				getTileHeight()
		);
	}
}
