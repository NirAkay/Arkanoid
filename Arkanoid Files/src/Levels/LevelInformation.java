package Levels;
import Geometry.Velocity;
import Sprites.Block;
import Sprites.Sprite;
import java.util.List;
/**
 * @author Nir Akay 207387770
 * */
public interface LevelInformation {
    /**
     * @return the number of balls
     * */
    int numberOfBalls();
    /**
     * @return The initial velocity of each ball
     * */
    List<Velocity> initialBallVelocities();
    /**
     * @return the paddle speed
     * */
    int paddleSpeed();
    /**
     * @return the paddle width
     * */
    int paddleWidth();
    /**
     * @return the level name
     * */
    String levelName();
    /**
     * @return a sprite with the background of the level
     * */
    Sprite getBackground();
    /**
     * @return List that contains all the blocks in this level
     * */
    List<Block> blocks();
    /**
     * @return the number of blocks we need to remove
     * */
    int numberOfBlocksToRemove();
}