package display;

import game.objects.Sprite;
import scenes.GameScene;

import javax.swing.*;
import java.awt.*;

public abstract class Renderer extends JPanel {

    public Renderer(int width, int height) {
        setSize(width, height);
    }


    abstract void drawScene(GameScene scene, Graphics g);
    abstract void drawSprite(Sprite s, Graphics g);

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.CYAN);
        g.fillRect(0,0,getWidth(), getHeight());
        g.setColor(Color.RED);
        g.drawString("Renderer is not set!" ,0, 0);
    }
}
