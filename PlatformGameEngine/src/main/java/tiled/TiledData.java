package tiled;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * TiledData is a static class that can be used to get
 * data from Tiled export file that should be in '.tml/.xml'
 * format. This class cannot have any instances.
 */
public class TiledData {

	/**
	 * Searches for child node of the target node that has the target name.
	 * If such child is not found, returned value is null.
	 * @param node target node
	 * @param targetName target child name
	 * @return node if child is found else returns null
	 */
	private static Element getTargetChild(Node node, String targetName) {

		NodeList children = node.getChildNodes();
		for (int c = 0; c < children.getLength(); c++) {
			if (children.item(c) instanceof Element) {
				Element e = (Element) children.item(c);
				if (e.getNodeName() == targetName) {
					return e;
				}
			}
		}

		return null;
	}


	private static HashMap<String, Property> getProperties(Node parent) {

		// Properties hashmap
		HashMap<String, Property> propList =  new HashMap<>();

		// Properties
		Element properties = getTargetChild(parent, "properties");
		if (properties != null) {

			// Getting all the elements with <property> tag from properties
			NodeList allProp = properties.getElementsByTagName("property");

			// For each property
			for (int p = 0; p < allProp.getLength(); p++) {

				// Getting property node
				Node property = allProp.item(p);

				// Getting it's attributes
				NamedNodeMap propAttributes = property.getAttributes();

				// Getting property name and value data
				String propName = propAttributes.getNamedItem("name").getNodeValue();
				String propValue = propAttributes.getNamedItem("value").getNodeValue();

				// Property is for now supposed to always have STRING type!!!
				// Creating Property object
				Property newProperty = new Property(PropertyType.STRING, propName, propValue);

				// Putting property object to hashmap with property name as key
				propList.put(propName, newProperty);
			}
		}
		return propList;
	}


	/**
	 * Private constructor that prevents class from creating instances.
	 */
	private TiledData() {};

	/**
	 * Static method that gets XML export (from tiled) as document (DOM).
	 * @param path represents XML file's path
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document getDocument(String path) throws ParserConfigurationException, SAXException, IOException {
		
		// Getting XML map file
		File file = new File(path);
		
		// Reading XML Map file
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db = dbf.newDocumentBuilder();  
		Document doc = db.parse(file);  
		doc.getDocumentElement().normalize();  
		
		return doc;  
	}

	
	/**
	 * Gets any image from desired path.
	 * @param src represents path to image source
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage getImage(String src) throws IOException {

		// Getting file from path
		File file = new File(src);
		
		// Checking whether file exists
		if (!file.exists()) {
			throw new IOException();
		}
		
		return ImageIO.read(file);
	}
	
	
	/**
	 * Method that gets all TileSets from target XML Tiled map export as
	 * the array that consists of TileSet objects.
	 * @param path represents path to parent folder of XML export
	 * @param xmlFile name of target XML Tiled map export
	 * @return Array that consists of all TileSet objects.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static TileSet[] getTileSets(String path, String xmlFile) throws ParserConfigurationException, SAXException, IOException {
		
		// DOM style object that is created from XML file
		Document doc = TiledData.getDocument(path+ "\\" + xmlFile);
		
		// All nodes that have <tileset> tag in document (doc)
		NodeList nodeList = doc.getElementsByTagName("tileset");
		
		// All nodes that have <image> tag in document (doc)
		NodeList images = doc.getElementsByTagName("image");
		
		// TileSet array that will be 'filled' with TileSets and returned
		TileSet[] tileSetArray = new TileSet[nodeList.getLength()];
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			
			// Each node in nodeList
			Node node = nodeList.item(i);
			
			// Node's all attributes
			NamedNodeMap attributes = node.getAttributes();
			
			// Getting values from XML attributes of each node
			int id = Integer.parseInt(attributes.getNamedItem("firstgid").getNodeValue());
			String name = attributes.getNamedItem("name").getNodeValue();
			int width = Integer.parseInt(attributes.getNamedItem("tilewidth").getNodeValue());
			int height = Integer.parseInt(attributes.getNamedItem("tileheight").getNodeValue());
			int count = Integer.parseInt(attributes.getNamedItem("tilecount").getNodeValue());
			int columns = Integer.parseInt(attributes.getNamedItem("columns").getNodeValue());
		
			// Getting image
			String imgName = images.item(i).getAttributes().getNamedItem("source").getNodeValue();
			String imgPath = path + "\\" + imgName;
			
			// Creating TileSet
			TileSet ts = new TileSet(id, name, width, height, count, columns, imgPath);
			
			// 'Filling' the array
			tileSetArray[i] = ts;
		}
		return tileSetArray;
	}


	/**
	 * Returns all TileLayer objects of the target map.
	 * @param path represents path to the parent folder of the target map
	 * @param xmlFile represents XML file export of target map
	 * @return Array that contains TileLayers
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static TileLayer[] getTileLayers(String path, String xmlFile) throws ParserConfigurationException, IOException, SAXException {

		// Getting DOM of XML map file
		Document mapDocument = TiledData.getDocument(path + "\\" + xmlFile);

		// Getting all nodes with <layer> tag
		NodeList layerNodeList = mapDocument.getElementsByTagName("layer");

		// Getting all nodes with <data> tag
		NodeList dataNodeList = mapDocument.getElementsByTagName("data");

		// Array that will be returned
		TileLayer[] layers = new TileLayer[layerNodeList.getLength()];

		// For each node in <layer> node lit
		for (int i = 0; i < layerNodeList.getLength(); i++) {

			// Each layer node in list
			Node layerNode = layerNodeList.item(i);

			// Getting attributes of node
			NamedNodeMap attributes = layerNode.getAttributes();

			// Basic attributes
			int id = Integer.parseInt(attributes.getNamedItem("id").getNodeValue());
			int width = Integer.parseInt(attributes.getNamedItem("width").getNodeValue());
			int height = Integer.parseInt(attributes.getNamedItem("height").getNodeValue());
			String name = attributes.getNamedItem("name").getNodeValue();

			/* If attribute 'visible' does not exist, we are setting 'visible' to false,
			   otherwise 'visible' is true, so we are going to uset result of 'this method
			   equals null' statement */
			boolean visible = attributes.getNamedItem("visible") == null;

			// Getting <data> element
			Node dataNode = dataNodeList.item(i);
			String[] data = dataNode.getFirstChild().getNodeValue().split("\n");
			int[][] matrixData = new int[height][width];

			// Saving layer data to int matrix
			for (int h = 1; h < height + 1; h++) {

				String[] rowStr = data[h].split(",");

				if (rowStr.length == width) {

					for (int w = 0; w < width; w++) {

						matrixData[h - 1][w] = Integer.parseInt(rowStr[w]);
					}
				}
			}

			// Properties
			HashMap<String, Property> propMap = getProperties(layerNode);

			// Creating TileLayer object
			TileLayer tl = new TileLayer(id, name, width, height, visible, propMap, matrixData);

			// Putting TileLayer object in array
			layers[i] = tl;
		}

		return layers;
	}


	/**
	 * Returns all ObjectGroup layers of the target map
	 * @param path represents path to the parent folder of the target map
	 * @param xmlFile represents XML file export of target map
	 * @return Array that contains TileLayers
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static ObjectGroup[] getObjectGroups(String path, String xmlFile) throws ParserConfigurationException, IOException, SAXException {

		// Getting DOM of XML map file
		Document mapDocument = TiledData.getDocument(path + "\\" + xmlFile);

		// Getting all nodes with <layer> tag
		NodeList ogLayers = mapDocument.getElementsByTagName("objectgroup");

		// Array that will be returned
		ObjectGroup[] groups = new ObjectGroup[ogLayers.getLength()];

		// For each node in list
		for (int i = 0; i < ogLayers.getLength(); i++) {

			// Each node
			Node ogNode = ogLayers.item(i);

			// Attributes (id and name)
			int id = Integer.parseInt(ogNode.getAttributes().getNamedItem("id").getNodeValue());
			String name = ogNode.getAttributes().getNamedItem("name").getNodeValue();

			// Properties
			HashMap<String, Property> propMap = getProperties(ogNode);

			// Casting node to Element
			Element nodeElement = (Element) ogNode;

			// Getting all elements with <object>
			NodeList objects = nodeElement.getElementsByTagName("object");

			// Object array
			ObjectLayer[] objectLayers = new ObjectLayer[objects.getLength()];

			// For each node of nodes with <object> tags
			for (int o = 0; o < objects.getLength(); o++) {

				// Each node
				Node n = objects.item(o);

				// Getting it's attributes
				NamedNodeMap attributes = n.getAttributes();

				// Getting data
				int oId = Integer.parseInt(attributes.getNamedItem("id").getNodeValue());
				String oName = attributes.getNamedItem("name").getNodeValue();
				int gId = Integer.parseInt(attributes.getNamedItem("gid").getNodeValue());
				int x = (int) Double.parseDouble(attributes.getNamedItem("x").getNodeValue());
				int y = (int) Double.parseDouble(attributes.getNamedItem("y").getNodeValue());
				int width = (int) Double.parseDouble(attributes.getNamedItem("width").getNodeValue());
				int height = (int) Double.parseDouble(attributes.getNamedItem("height").getNodeValue());

				/* If attribute 'visible' does not exist, we are setting 'visible' to false,
			   	otherwise 'visible' is true, so we are going to uset result of 'this method
			   	equals null' statement */
				boolean visible = attributes.getNamedItem("visible") == null;

				// Properties
				HashMap<String, Property> objPropMap = getProperties(n);

				// Creating new ObjectLayer object
				ObjectLayer ol = new ObjectLayer(oId, oName, gId, x, y, width, height, visible, objPropMap);

				// Putting into array
				objectLayers[o] = ol;
			}

			// Creating ObjectGroup object
			ObjectGroup g = new ObjectGroup(id, name, propMap, objectLayers);

			// Putting into array
			groups[i] = g;
		}
		return groups;
	}


	/**
	 * Method that is used for loading the whole map from XML file export.
	 *  IMPORTANT: All the images should be in the same folder as XML Tiled map export file!
	 * @param path represents path to parent folder of XML export
	 * @param xmlFile name of target XML Tiled map export
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static TiledMap loadTiledMap(String path, String xmlFile) throws ParserConfigurationException, SAXException, IOException {
		
		// Getting DOM of XML map file
		Document mapDocument = TiledData.getDocument(path + "\\" + xmlFile);
		
		// Getting map attributes
		Node mapNode = mapDocument.getElementsByTagName("map").item(0);
		NamedNodeMap attributes = mapNode.getAttributes();
		
		// Getting parameters out of map attributes
		int width = Integer.parseInt(attributes.getNamedItem("width").getNodeValue());
		int height = Integer.parseInt(attributes.getNamedItem("height").getNodeValue());;
		int tileWidth = Integer.parseInt(attributes.getNamedItem("tilewidth").getNodeValue());;
		int tileHeight = Integer.parseInt(attributes.getNamedItem("tileheight").getNodeValue());;
		
		// Creating map object
		TiledMap newMap = new TiledMap();
		
		// Setting map attributes
		newMap.setPath(path);
		newMap.setWidth(width);
		newMap.setHeight(height);
		newMap.setTileWidth(tileWidth);
		newMap.setTileHeight(tileHeight);
	
		// Setting TileSet objects array
		newMap.setTileSets(TiledData.getTileSets(path, xmlFile));

		// Setting TileLayer objects array
		newMap.setTileLayers(TiledData.getTileLayers(path, xmlFile));

		// Setting ObjectGroup objects array
		newMap.setObjectGroups(TiledData.getObjectGroups(path, xmlFile));
		
		return newMap;
	}

}
