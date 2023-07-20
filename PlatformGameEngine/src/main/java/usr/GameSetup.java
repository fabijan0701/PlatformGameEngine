package usr;


import display.StaticCamera;
import game.Game;
import org.xml.sax.SAXException;
import scenes.GameScene;
import tiled.TileLayer;
import tiled.TiledData;
import tiled.TiledMap;
import usr.obj.MyObject;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class GameSetup {

    public static void mainSetup() {

        Game.setTitle("Test game");
        Game.setFps(30);
    }

    public static void setupScenes() {


        TiledMap map;
        try {
            map = TiledData.loadTiledMap("src\\main\\resources\\maps\\lvl1", "map.tmx");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        GameScene scene = new GameScene("first level", new StaticCamera(), map) {

            @Override
            public void setup() {
            }
        };


        Game.scenes.put(scene.getName(), scene);
        Game.setActiveScene(scene.getName());


        TileLayer layer = scene.getMap().getSpriteLayer("mainch1");
        MyObject obj = new MyObject(100, 100, 100, 100, layer);
        Game.getActiveScene().getObjects().add(obj);
    }
}
