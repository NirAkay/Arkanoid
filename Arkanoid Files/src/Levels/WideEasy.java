package Levels;

import Geometry.Point;
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
public class WideEasy implements LevelInformation {
    private int move;
    /**
     * @return the number of balls
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * @return The initial velocity of each ball
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = -5; i < 6; i++) {
            if (i != 0) {
                velocities.add(Velocity.fromAngleAndSpeed(10 * i, 3));
            }
        }
        return velocities;
    }

    /**
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return 2;
    }

    /**
     * @return the paddle width
     */
    public int paddleWidth() {
        return 600;
    }

    /**
     * @return the level name
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * @return a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.WHITE);
                Color[] colors = {new Color(250, 215, 140), new Color(240, 180, 25),
                        new Color(240, 190, 25)};
                Color color = new Color(250, 215, 140);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.BLUE);
                d.fillRectangle(26, 560, 749, 40);
                d.setColor(new Color(141, 89, 61));
                d.fillRectangle(30 + move, 585, 40, 10);
                d.setColor(Color.BLACK);
                d.drawLine(50 + move, 590, 50 + move, 565);
                d.setColor(Color.RED);
                d.fillRectangle(51 + move, 565, 10, 5);
                move++;
                if (move == 750) {
                    move = -30;
                }
                d.setColor(color);
                for (int i = -20; i < 100; i++) {
                    d.drawLine(150, 150, 150 + i * 5, 250);
                }
                for (int i = 0; i < 3; i++) {
                    d.setColor(colors[i]);
                    d.fillCircle(150, 150, 60 - 10 * i);
                }

            }

            @Override
            public void timePassed() {
            }
        };
    }

    /**
     * @return List that contains all the blocks in this level
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color color = Color.RED;
        for (int i = 0; i < 15; i++) {
            if (i == 2) {
                color = Color.ORANGE;
            }
            if (i == 4) {
                color = Color.YELLOW;
            }
            if (i == 6) {
                color = Color.GREEN;
            }
            if (i == 9) {
                color = Color.BLUE;
            }
            if (i == 11) {
                color = Color.PINK;
            }
            if (i == 13) {
                color = Color.CYAN;
            }
            blocks.add(new Block(new Geometry.Rectangle(
                    new Point(25 + i * 50, 250), 50, 25), color));
        }
        return blocks;
    }

    /**
     * @return the number of blocks we need to remove
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
