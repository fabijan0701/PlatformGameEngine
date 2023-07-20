package setup;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import tiled.TiledData;
import tiled.TiledMap;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameSetupTest {

    @Test
    void mapLoadTest() throws ParserConfigurationException, IOException, SAXException {

        TiledMap map = TiledData.loadTiledMap("src\\main\\resources\\maps\\lvl1", "map.tmx");
        assertNotNull(map);
    }
}