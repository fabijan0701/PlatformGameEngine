package display;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameWindowTest {

    @Test
    @DisplayName("Window init test")
    void windowInitTest() {

        GameWindow testWindow = new GameWindow(1280, 720);
        testWindow.setVisible(true);
    }
}