package Sprites;
import Background.Counter;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 *@author Nir Akay 207387770
 **/
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private Block scoreBlock;
    private String name;
    static final int NARROW_SIDE_RECT = 25;
    static final int DEFAULT_WIDTH = 800;
    static final int SIZE_TEXT = 20;
    static final int TEXT_START_X = 250;
    static final int TEXT_START_Y = 18;

    /**
     * constructor.
     * @param counter a Counter to initialize.
     * @param name the name of the level
     * */
    public ScoreIndicator(Counter counter, String name) {
        this.scoreCounter = counter;
        this.scoreBlock = new Block(new Rectangle(new Point(0, 0), DEFAULT_WIDTH,
                NARROW_SIDE_RECT), Color.WHITE);
        this.name = name;
    }

    /**
     * nothing.
     * */
    public void timePassed() {
    }

    /**
     * draw this class on the screen.
     * @param d the DrawSurface we draw on him
     * */
    public void drawOn(DrawSurface d) {
        this.scoreBlock.drawOn(d);
        d.drawText(TEXT_START_X, TEXT_START_Y, "Score: " + this.scoreCounter.getValue()
                + "              Level Name: " + this.name, SIZE_TEXT);
    }
}
