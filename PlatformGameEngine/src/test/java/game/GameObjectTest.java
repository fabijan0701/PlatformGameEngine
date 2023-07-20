package game;

import game.objects.GameObject;
import game.physics.CollisionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    @Test
    void getDistanceFrom() {

        GameObject g1 = new GameObject(3, 3, 100, 100, null, CollisionType.CIRCLE);
        GameObject g2 = new GameObject(6, 3, 100, 100, null, CollisionType.SQUARE);

        assertEquals(3.0, g1.getDistanceFrom(g2));
    }
}