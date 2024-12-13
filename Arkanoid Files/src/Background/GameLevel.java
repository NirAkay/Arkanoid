package Background;
import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;
import Levels.LevelInformation;
import Listener.BallRemover;
import Listener.BlockRemover;
import Listener.ScoreTrackingListener;
import Sprites.Block;
import Sprites.Collidable;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import Sprites.Paddle;
import Sprites.ScoreIndicator;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Nir Akay 207387770
 * */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter counterScore;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    private boolean running;
    static final int DEFAULT_WIDTH = 800;
    static final int DEFAULT_HEIGHT = 600;
    static final int NARROW_SIDE_RECT = 25;
    static final int PADDLE_HEIGHT = 15;
    static final int PADDLE_START_Y = 545;
    static final int RADIUS = 5;
    static final int WIN_SCORE = 100;

    /**
     * @param score Counter for the score
     * @param ar the Animation that run the game
     * @param level the level we want to activate
     * @param ks a Keyboard sensor
     * constructor.
     * */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counterBlocks = new Counter(level.numberOfBlocksToRemove());
        this.counterBalls = new Counter(level.numberOfBalls());
        this.counterScore = score;
        this.runner = ar;
        this.keyboard = ks;
        this.level = level;
    }

    /**
     * add new collideable to the list.
     * @param c new collidable to list
     * */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add new sprite to the list.
     * @param s new sprite to list
     * */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove collidable from the game.
     * @param c the collidable we want to remove
     * */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove sprite from the game.
     * @param s the sprite we want to remove
     * */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * create and add a ball to the game.
     * @param paddle the Paddle of the game
     * @param index the ball's number
     * */
    private void createBall(Paddle paddle, int index) {
        Ball ball = new Ball((double) DEFAULT_WIDTH / 2,
                DEFAULT_HEIGHT - NARROW_SIDE_RECT * 2 - PADDLE_HEIGHT - 1,
                RADIUS, Color.WHITE, this.environment);
        ball.setVelocity(this.level.initialBallVelocities().get(index));
        ball.setFrame(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        ball.setPaddle(paddle);
        this.addSprite(ball);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     * */
    public void initialize() {
        this.addSprite(this.level.getBackground());
        new Block(new Rectangle(new Point(0, NARROW_SIDE_RECT), DEFAULT_WIDTH,
                NARROW_SIDE_RECT), Color.GRAY).addToGame(this);
        new Block(new Rectangle(new Point(0, NARROW_SIDE_RECT),
                NARROW_SIDE_RECT, DEFAULT_HEIGHT - NARROW_SIDE_RECT), Color.GRAY).addToGame(this);
        new Block(new Rectangle(new Point(DEFAULT_WIDTH - NARROW_SIDE_RECT, NARROW_SIDE_RECT),
                NARROW_SIDE_RECT, DEFAULT_HEIGHT - NARROW_SIDE_RECT), Color.GRAY).addToGame(this);
        BlockRemover remove = new BlockRemover(this, this.counterBlocks);
        ScoreTrackingListener score = new ScoreTrackingListener(this.counterScore);
        Paddle paddle = new Paddle(Color.orange, new Rectangle(
                new Point((double) DEFAULT_WIDTH / 2 - (double) this.level.paddleWidth() / 2, PADDLE_START_Y),
                level.paddleWidth(), PADDLE_HEIGHT), this.keyboard, NARROW_SIDE_RECT,
                DEFAULT_WIDTH - NARROW_SIDE_RECT, this.level.paddleSpeed());
        paddle.addToGame(this);
        for (int i = 0; i < level.numberOfBalls(); i++) {
            createBall(paddle, i);
        }
        this.addSprite(new ScoreIndicator(this.counterScore, this.level.levelName()));
        Block death = new Block(new Rectangle(new Point(NARROW_SIDE_RECT, DEFAULT_HEIGHT + 1),
                DEFAULT_WIDTH - 2 * NARROW_SIDE_RECT, NARROW_SIDE_RECT), Color.GRAY);
        death.addHitListener(new BallRemover(this, this.counterBalls));
        death.addToGame(this);
        for (Block block: this.level.blocks()) {
            block.addHitListener(remove);
            block.addHitListener(score);
            block.addToGame(this);
        }
    }

    /**
     * @return if the animation need to stop
     * */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @param d the screen we paint on him
     * do one frame forward
     * */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    "space", new PauseScreen()));
        }
        if (this.counterBlocks.getValue() == 0) {
                this.counterScore.increase(WIN_SCORE);
                this.running = false;
        }
        if (this.counterBalls.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * Run the game -- start the animation loop.
     * */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * @return the current blocks count
     */
    public int getBlocksCount() {
        return this.counterBlocks.getValue();
    }

    /**
     * @return the current balls count
     */
    public int getBallsCount() {
        return this.counterBalls.getValue();
    }
}