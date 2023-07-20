package io.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private final static int TOTAL_KEYS = 256;
    private KeyState[] keys;
    private KeyState[] keysOld;

    public Input() {

        keys = new KeyState[TOTAL_KEYS];
        keysOld = new KeyState[TOTAL_KEYS];

        // Setup
        for (int k = 0; k < TOTAL_KEYS; k++) {
            keys[k] = KeyState.RELEASED;
            keysOld[k] = KeyState.RELEASED;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = KeyState.PRESSED;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = KeyState.RELEASED;
    }

    public void update() {

        // Keys
        for (int keyCode = 0; keyCode < TOTAL_KEYS; keyCode++) {
            keysOld[keyCode] = keys[keyCode];
        }
    }

    public boolean isKey(int keyCode) {
        return keys[keyCode] == KeyState.PRESSED;
    }

    public boolean isKeyUp(int keyCode) {
        return keys[keyCode] == KeyState.RELEASED && keysOld[keyCode] == KeyState.PRESSED;
    }

    public boolean isKeyDown(int keyCode) {
        return keys[keyCode] == KeyState.PRESSED && keysOld[keyCode] == KeyState.RELEASED;
    }


}
