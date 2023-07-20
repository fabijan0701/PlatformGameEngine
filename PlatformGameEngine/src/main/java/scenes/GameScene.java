package scenes;

import display.Renderer;
import game.objects.GameObject;
import tiled.TiledMap;

import java.util.ArrayList;

public abstract class GameScene {

    public abstract void setup();
    private final ArrayList<GameObject> objects;

    private TiledMap map;
    private String name;

    private Renderer camera;

    public GameScene(String name, Renderer camera, TiledMap map) {

        setName(name);
        setCamera(camera);
        setMap(map);
        objects = new ArrayList<>();
    }


    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TiledMap getMap() {
        return map;
    }
    public void setMap(TiledMap map) {
        this.map = map;
        this.getCamera().setSize(map.getPixelWidth(), map.getPixelHeight());
    }

    public Renderer getCamera() {
        return this.camera;
    }

    public void setCamera(Renderer r) {
        this.camera = r;
    }
}
