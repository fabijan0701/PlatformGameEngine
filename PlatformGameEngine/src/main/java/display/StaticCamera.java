package display;

import game.Game;
import game.objects.GameObject;
import game.objects.Sprite;
import scenes.GameScene;
import tiled.*;

import java.awt.*;

public class StaticCamera extends Renderer{

    public StaticCamera() {
        super(0, 0);
        setBackground(Color.CYAN);
    }

    @Override
    void drawScene(GameScene scene, Graphics g) {

        // Converting graphics object to Graphics2D object
        Graphics2D graphics2D = (Graphics2D) g;

        TiledMap activeMap = scene.getMap();

        for (TileLayer tl : activeMap.getTileLayers()) {

            if (! tl.isVisible()) {
                continue;
            }

            if (tl.isSpriteLayer()) {
                continue;
            }

            int[][] layerData = tl.getData();

            for (int i = 0; i < tl.getHeight(); i++) {

                for (int j = 0; j < tl.getWidth(); j++) {

                    int id = layerData[i][j];

                    if (id != 0) {

                        TileSet ts = activeMap.getTargetTileSet(id);

                        Image tile = ts.getTile(id);

                        int width = ts.getTileWidth();
                        int height = ts.getTileHeight();
                        int x = j * activeMap.getTileWidth();
                        int y = i * activeMap.getTileHeight();

                        graphics2D.drawImage(tile, x, y, width, height, null);
                    }
                }
            }
        }
    }

    @Override
    void drawSprite(Sprite s, Graphics g) {

        if (! s.isVisible()) {
            return;
        }
        g.drawImage(s.getImage(), s.getX(), s.getY(), s.getWidth(), s.getHeight(), null);
    }


    @Override
    public void paint(Graphics g) {
        drawScene(Game.getActiveScene(), g);
    }
}
