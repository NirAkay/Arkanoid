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
public class GreenLevel implements LevelInformation {
    static final int DEFAULT_WIDTH = 800;
    static final int DEFAULT_HEIGHT = 600;
    static final int RECT_WIDTH = 51;
    static final int RECT_HEIGHT = 23;
    static final int START_PAINTING_X = 265;
    static final int START_PAINTING_Y = 150;
    static final int ROW_NUMBERS = 5;
    static final int MAX_BLOCK_IN_ROW = 10;
    static final int DY = -4;
    static final int DX = 3;
    private int radar;

    /**
     * @return the number of balls
     * */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * @return The initial velocity of each ball
     * */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocities.add(new Velocity(DX * Math.pow(-1, i), DY));
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
        return "Green 3";
    }

    /**
     * @param i position to arrange the blocks
     * @param j position to arrange the blocks
     * @param color the color of the block
     * @return block by color.
     * */
    private Block createBlockByColor(int i, int j, Color color) {
        return new Block(new Rectangle(new Point(START_PAINTING_X + RECT_WIDTH * (i + j),
                START_PAINTING_Y + i * RECT_HEIGHT), RECT_WIDTH, RECT_HEIGHT), color);
    }

    /**
     * @param blocks List of Blocks
     * Arrange the block and add them to the game.
     * */
    private void createBlocks(List<Block> blocks) {
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        for (int i = 0; i < ROW_NUMBERS; i++) {
            for (int j = 0; j < MAX_BLOCK_IN_ROW - i; j++) {
                blocks.add(createBlockByColor(i, j, colors[i]));
            }
        }
    }

    /**
     * @return a sprite with the background of the level
     * */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.GREEN.darker().darker());
                d.fillRectangle(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
                d.setColor(Color.GRAY.darker().darker().darker());
                d.fillRectangle(70, 440, 100, 160);
                d.setColor(Color.GRAY.darker().darker());
                d.fillRectangle(105, 380, 30, 60);
                d.setColor(Color.GRAY.darker());
                d.fillRectangle(115, 180, 10, 200);
                d.setColor(Color.ORANGE);
                d.fillCircle(120, 170, 12);
                d.setColor(Color.RED);
                d.fillCircle(120, 170, 8);
                d.setColor(Color.WHITE);
                d.fillCircle(120, 170, 4);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        d.fillRectangle(77 + 19 * i, 448 + 34 * j, 11, 27);
                    }
                }
                d.setColor(Color.ORANGE);
                d.drawCircle(120, 170, 12 + radar);
                radar++;
                if (radar == 8) {
                    radar = 0;
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
        createBlocks(blocks);
        return blocks;
    }

    /**
     * @return the number of blocks we need to remove
     * */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
