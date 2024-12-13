package Sprites;
import Background.GameLevel;
import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Nir Akay 207387770
 * */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Color color;
    private Rectangle paddle;
    private int step;
    static final int NARROW_ANGLE = 30;
    static final int WIDE_ANGLE = 60;
    private static final double EPSILON = Math.pow(10, -10);
    private int startFrame;
    private int endFrame;

    /**
     * constructor.
     * @param color the color of the paddle
     * @param rect the shape of the rectangle
     * @param endFrame side of the frame for the paddle
     * @param keyboard keyboard sensor
     * @param startFrame side of the frame for the paddle
     * @param speed the speed of the paddle
     * */
    public Paddle(Color color, Rectangle rect, biuoop.KeyboardSensor keyboard,
                  int startFrame, int endFrame, int speed) {
        this.color = color;
        this.paddle = rect;
        this.keyboard = keyboard;
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        this.step = speed;
    }

    /**
     * @return side of the frame for the paddle
     * */
    public int getEndFrame() {
        return endFrame;
    }

    /**
     * @return side of the frame for the paddles
     * */
    public int getStartFrame() {
        return startFrame;
    }

    /**
     * Move the ball one step left.
     * */
    public void moveLeft() {
        if (this.paddle.getUpperLeft().getX() - this.step < startFrame) {
            this.paddle.setUpperLeft(new Point(startFrame + this.step + EPSILON,
                    this.paddle.getUpperLeft().getY()));
        }
        this.paddle.setUpperLeft(new Point(this.paddle.getUpperLeft().getX() - this.step,
                this.paddle.getUpperLeft().getY()));
    }

    /**
     * Move the ball one step right.
     * */
    public void moveRight() {
        if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() + this.step > endFrame) {
            this.paddle.setUpperLeft(new Point(endFrame - this.step - this.paddle.getWidth(),
                    this.paddle.getUpperLeft().getY()));
        }
        this.paddle.setUpperLeft(new Point(this.paddle.getUpperLeft().getX() + this.step,
                this.paddle.getUpperLeft().getY()));
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draw the sprite to the screen.
     * @param d our surface
     * */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
        (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * @return the "collision shape" of the object.
     * */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param currentVelocity the current velocity of the bal
     * @param collisionPoint the point where the collision occurred
     * @param hitter the hitter ball
     * @return a new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double fifthLen = this.paddle.getWidth() / 5;
        double speed = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx())
                + (currentVelocity.getDy() * currentVelocity.getDy()));
        if (collisionPoint.getX() >= this.paddle.getUpperLeft().getX()
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + fifthLen
        && collisionPoint.getY() == this.paddle.getUpperLeft().getY()) {
            return Velocity.fromAngleAndSpeed(-WIDE_ANGLE, speed);
        }
        if ((collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + fifthLen
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + 2 * fifthLen)) {
            return Velocity.fromAngleAndSpeed(-NARROW_ANGLE, speed);
        }
        if (collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + 3 * fifthLen
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + 4 * fifthLen) {
            return Velocity.fromAngleAndSpeed(NARROW_ANGLE, speed);
        }
        if (collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + 4 * fifthLen
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + 5 * fifthLen
                && collisionPoint.getY() == this.paddle.getUpperLeft().getY()) {
            return Velocity.fromAngleAndSpeed(WIDE_ANGLE, speed);
        }
        if (collisionPoint.getX() == this.paddle.getUpperLeft().getX()
                || collisionPoint.getX() == this.paddle.getUpperLeft().getX() + this.paddle.getWidth()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * Add this paddle to the game.
     * @param g the game we add the paddle to
     * */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}