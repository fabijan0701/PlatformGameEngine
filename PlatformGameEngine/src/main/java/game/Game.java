package game;

import display.GameWindow;
import display.Renderer;
import game.objects.GameObject;
import game.objects.Sprite;
import io.input.Input;
import io.output.GameOutput;
import scenes.GameScene;
import tiled.TileLayer;
import usr.GameSetup;
import usr.SceneUpdate;

import java.awt.event.KeyEvent;
import java.util.HashMap;


/**
 *
 */
public class Game{

    private static int fps;
    private static boolean running;
    private static boolean initialized = false;
    public static GameWindow display;
    public static GameOutput out;

    public static Input input;


    public static void init() {

        Game.running = false;

        Game.scenes = new HashMap<>();

        Game.out = new GameOutput();

        Game.display = new GameWindow(400, 400);

        GameSetup.setupScenes();

        GameSetup.mainSetup();

        if (Game.scenes.isEmpty()) {
            Game.out.printError("No scenes loaded!");
            return;
        }

        if (Game.display.getRenderer() == null) {
            Game.out.printWarning("Renderer is not set!");
            return;
        }
        else {
            Game.out.printInfo("Renderer: " + Game.display.getRenderer().getClass().toString());
        }

        for (GameScene scene: Game.scenes.values()) {
            Game.out.printInfo("Scene " + scene.getName() + " loaded");
            for (TileLayer tl: scene.getMap().getTileLayers()) {
                if (tl.isSpriteLayer()) {
                    Game.out.printInColor("Sprite layer: " + tl.getName() + " is loaded!", GameOutput.GREEN);
                }
            }
        }

        Game.out.printInfo("Active scene: " + Game.getActiveScene().getName());
        Game.display.setVisible(true);

        Game.input = new Input();
        Game.display.addKeyListener(Game.input);

        Game.initialized = true;
        Game.out.printInfo("Game initialized");
        Game.start();
    }

    /**
     * Returns whether GameEngine is running or not.
     * @return boolean value that gives us information whether engine is running.
     */
    public static boolean isRunning() {
        return Game.running;
    }

    /**
     * Returns current FPS of the game.
     */
    public static int getFps() {
        return Game.fps;
    }

    /**
     * Sets the desired game FPS.
     */
    public static void setFps(int fps) {
        Game.fps = fps;
    }
    /**
     * Starts the game.
     */
    public static void start() {

        if (!Game.initialized) {
            Game.out.printError("Game is not yet initialized!");
            return;
        }
        Game.running = true;
        gameLoop();
    }

    /**
     * Stops the game.
     */
    public static void stop() {
        Game.running = false;
    }

    /**
     * Default game loop method. Screen is refreshed n times per second. Number n is
     * defined by frames in second (fps) which can be adjusted by using 'setFps' method.
     * It works by calculating time of each frame, which is calculated by dividing 1 by fps
     * and then calculating delta time by subtracting current time (in nano seconds) by time
     * of the previous frame and then creating loop that runs until passed time until it is
     * greater than or equal to defined interval. Then state is updated and screen is refreshed
     * for each frame.
     */
    public static void gameLoop() {

        // Nano time of the last frame (converted into seconds).
        double lastFrameTime = System.nanoTime() / 1E9;

        // Nano time of the current frame (converted into current).
        double currentTime = 0.0;

        // Definition of time interval.
        double interval =  1.0 / Game.getFps();

        // While game is running.
        while (Game.isRunning()) {

            // Accumulated time.
            double accTime = 0;

            // Getting the current time (and converting into seconds).
            currentTime = System.nanoTime() / 1E9;

            // Calculating delta time.
            double dt = currentTime - lastFrameTime;

            // Setting time of this frame to be time of the last frame.
            lastFrameTime = currentTime;

            // 'Freezing' time until accumulated time is greater than or
            // equal to time interval.
            while (accTime <= interval) {
                accTime += dt;
            }

            // Updating the game
            Game.update(accTime);

            // Draw map
            Game.display.getRenderer().repaint();
        }
    }

    /**
     * Method that updates game depending on player's input.
     * @param deltaTime argument that is sent to method so object is moving same
        independently of the machine it's running on.
     */
    public static void update(double deltaTime) {

        Game.input.update();
        SceneUpdate.update(Game.input);
        
    }


    public static String getTitle() {
        return Game.display.getTitle();
    }

    public static void setTitle(String title) {
        Game.display.setTitle(title);
    }

    // === Scenes ===

    private static GameScene activeScene;
    public static HashMap<String, GameScene> scenes;

    public static GameScene getActiveScene() {
        return Game.activeScene;
    }

    public static void setActiveScene(String sceneName) {

        if (!scenes.containsKey(sceneName)) {
            Game.out.printError("Scene with name " + "'" + sceneName + "'" + " does not exist in Game!");
            return;
        }
        Game.activeScene = scenes.get(sceneName);
        Game.display.setRenderer(Game.activeScene.getCamera());
    }
}
