package usr;

import game.Game;
import io.input.Input;

import java.awt.event.KeyEvent;

public class SceneUpdate {

    public static void update(Input input) {

        if (input.isKeyDown(KeyEvent.VK_E)) {
            Game.out.printInfo("Key down");
        }

        if (input.isKeyUp(KeyEvent.VK_E)) {
            Game.out.printInfo("Key up");
        }
    }
}
