package Sprites;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 *@author Nir Akay 207387770
 **/
public class SpriteCollection {
    private List<Sprite> spriteList;
    static final int DEFAULT_WIDTH = 800;
    static final int DEFAULT_HEIGHT = 600;
    static final int NARROW_SIDE_RECT = 25;

    /**
     * constructor.
     * */
    public SpriteCollection() {
        spriteList = new ArrayList<>();
    }

    /**
     * Add the sprite to the list.
     * @param s new sprite to add
     * */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * remove sprite from the list.
     * @param s the sprite we want to remove
     * */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     * */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed();
        }
    }

    /**
     * @param d the surface we draw on him
     * call drawOn(d) on all sprites.
     * */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
            if (i == 3) {
                d.setColor(Color.GRAY);
                d.drawRectangle(0, NARROW_SIDE_RECT, DEFAULT_WIDTH, NARROW_SIDE_RECT);
                d.drawRectangle(0, NARROW_SIDE_RECT, NARROW_SIDE_RECT, DEFAULT_HEIGHT - NARROW_SIDE_RECT);
                d.drawRectangle(DEFAULT_WIDTH - NARROW_SIDE_RECT, NARROW_SIDE_RECT,
                        NARROW_SIDE_RECT, DEFAULT_HEIGHT - NARROW_SIDE_RECT);
            }
        }
    }
}