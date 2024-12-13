package Sprites;
import biuoop.DrawSurface;
/**
 *@author Nir Akay 207387770
 **/
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d our surface
     * */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}