package io.output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOutputTest {

    GameOutput output;
    @BeforeEach
    void setup() {
        output = new GameOutput();
    }

    @Test
    void printlnTest() {
        output.println("Hello");
        output.println_r("Adio");
        output.println_r("Mislav");
    }

    @Test
    void colorTest() {
        output.printInColor("Hello", GameOutput.BLUE);
        output.printWarning("Warning!");
        output.printError("Loading error!");
    }

}