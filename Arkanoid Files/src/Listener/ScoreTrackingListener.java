package Listener;
import Background.Counter;
import Geometry.Ball;
import Sprites.Block;

/**
 *@author Nir Akay 207387770
 **/
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    static final int HIT = 5;

    /**
     * constructor.
     * @param scoreCounter a Counter to initialize
     * */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * the function rise the score when we hit occurred.
     * @param hitter the hitter ball
     * @param beingHit the block that being hit
     * */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(HIT);
    }
}