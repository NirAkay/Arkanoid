package Listener;
import Background.Counter;
import Background.GameLevel;
import Geometry.Ball;
import Sprites.Block;

/**
 *@author  nir akay 207387770
 **/
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param gameLevel a Game to initialize
     * @param removedBlocks a Counter to initialize
     * */
    public BallRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBlocks;
    }

    /**
     *  ball that fall out from game should be removed from the game.
     * @param beingHit the block that being hit
     * @param hitter the hitter ball
     *  */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
