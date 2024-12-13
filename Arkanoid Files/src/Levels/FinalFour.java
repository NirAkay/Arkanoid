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
public class FinalFour implements LevelInformation {
    private int move1;
    private int move2;

    /**
     * @return the number of balls
     * */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * @return The initial velocity of each ball
     * */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            velocities.add(new Velocity(i, -4));
        }
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
        return "Final Four";
    }

    /**
     * @param d the surface we draw on him
     * @param index the index of rainbow
     * paint rainbows
     * */
    private void rainbow(DrawSurface d, int index) {
        int move = this.move1;
        if (index == 1) {
            move = this.move2;
        }
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(80 + i * 10 + index * 500 + move, 475, 60 + i * 10 + index * 450 + move, 600);
        }
        Color color = Color.GRAY.brighter();
        d.setColor(Color.GRAY.brighter());
        for (int i = 0; i < 3; i++) {
            d.fillCircle(100 + i * 32 + index * 500 + move, 475, 30);
            if (i % 2 == 0) {
                color = color.darker();
                d.setColor(color);
            }
        }
        color = Color.GRAY.brighter();
        for (int i = 0; i < 2; i++) {
            d.fillCircle(116 + i * 32 + index * 500 + move, 508, 30);
            color = color.darker();
            d.setColor(color);
    }
        if (this.move1 == 750) {
            this.move1 = -200;
        }
        if (this.move2 == 250) {
            this.move2 = -700;
        }
        if (index == 0) {
            this.move1++;
        } else {
            this.move2++;
        }
    }

    /**
     * @return a sprite with the background of the level
     * */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(103, 163, 199));
                d.fillRectangle(0, 0, 800, 600);
                for (int i = 0; i < 2; i++) {
                    rainbow(d, i);
                }
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
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                blocks.add(new Block(new Rectangle(new Point(25 + j * 50, 150 + i * 25), 50, 25), colors[i]));
            }
        }
        return blocks;
    }

    /**
     * @return the number of blocks we need to remove
     * */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}