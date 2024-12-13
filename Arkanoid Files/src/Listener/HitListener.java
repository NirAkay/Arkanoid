package Listener;
import Sprites.Block;
import Geometry.Ball;

/**
 *@author Nir Akay 207387770
 **/
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param hitter the hitter ball
     * @param beingHit the block that being hit
     * */
    void hitEvent(Block beingHit, Ball hitter);
}
