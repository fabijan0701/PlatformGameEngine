package display;

import javax.swing.*;
import java.awt.*;

/**
 * GameWindow represents window that displays game. Extends JFrame class
 * from Java Swing package.
 */
public class GameWindow extends JFrame {

    private Renderer renderer;

    /**
     * Default constructor that takes 2 integer arguments that represents width and height
     * of a GameWindow.
     * @param widthPx width (in pixels)
     * @param heightPx height (in pixels)
     */
    public GameWindow(int widthPx, int heightPx) {

        // Setting size and position in constructor
        this.setSize(widthPx, heightPx);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        renderer.setVisible(true);
        this.renderer = renderer;
        this.add(renderer);
        this.setSize(renderer.getSize());
    }
}
