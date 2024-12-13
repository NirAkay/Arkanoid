package Levels;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Sprites.Block;
import Sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Nir Akay 207387770
 * */
public class DirectHit implements LevelInformation {

    /**
     * @return the number of balls
     * */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * @return The initial velocity of each ball
     * */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -4));
        return velocities;
    }

    /**
     * @return the paddle speed
     * */
    public int paddleSpeed() {
        return 8;
    }

    /**
     * @return the paddle width
     * */
    public int paddleWidth() {
        return 100;
    }

    /**
     * @return the level name
     * */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * @return a sprite with the background of the level
     * */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.BLUE);
                for (int i = 0; i < 3; i++) {
                    d.drawCircle(400, 170, 100 - i * 20);
                }
                d.drawLine(400, 130, 400, 50);
                d.drawLine(400, 210, 400, 290);
                d.drawLine(360, 170, 280, 170);
                d.drawLine(440, 170, 520, 170);
            }

            @Override
            public void timePassed() {
            }
        };
    }

    /**
     * @return List that contains all the blocks in this level
     * */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(380, 150), 40, 40), Color.RED));
        return blocks;
    }

    /**
     * @return the number of blocks we need to remove
     * */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}