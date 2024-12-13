package Listener;
import Background.Counter;
import Background.GameLevel;
import Geometry.Ball;
import Sprites.Block;

/**
 *@author  nir akay 207387770
 **/
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param removedBlocks a Counter to initialize
     * @param gameLevel a Game to initialize
     * */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * remove the block from the game and its listener from the game.
     * @param hitter the hitter ball
     * @param beingHit the block that being hit
     * */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}