package Geometry;
import Background.GameLevel;
import Background.GameEnvironment;
import Sprites.CollisionInfo;
import Sprites.Paddle;
import Sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Nir Akay 207387770
 * */
public class Ball implements Sprite {
    static final int DEFAULT_FRAME = 200;
    private Point center;
    private java.awt.Color color;
    private int r;
    private GameEnvironment gameEnvironment;
    private Velocity velocity;
    private Paddle paddle;
    private int startXFrame;
    private int endXFrame;
    private int startYFrame;
    private int endYFrame;

    /**
    * constructor.
     * @param center the center point of the ball
     * @param color the color of the ball
     * @param r the radius of the ball
     * @param gE the game environment of our game
     * */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gE) {
        this.center = center;
        this.r = r;
        this.color = color;
        velocity = new Velocity(1, 1);
        this.startXFrame = 0;
        this.endXFrame = DEFAULT_FRAME;
        this.startYFrame = 0;
        this.endYFrame = DEFAULT_FRAME;
        this.gameEnvironment = gE;
        paddle = null;
    }

    /**
     * constructor.
     * @param x the center X's center value of the ball
     * @param y the center Y's center value of the ball
     * @param color the color of the ball
     * @param r the radius of the ball
     * @param gE the game environment of our game
     * */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gE) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.startXFrame = 0;
        this.endXFrame = DEFAULT_FRAME;
        this.startYFrame = 0;
        this.endYFrame = DEFAULT_FRAME;
        this.gameEnvironment = gE;
        paddle = null;
    }

    /**
     * @param startX the start of the frame X axis
     * @param endX the end of the frame X axis
     * @param startY the start of the frame X axis
     * @param endY the end of the frame X axis
     * the function set the frame of the ball
     * */
    public void setFrame(int startX, int startY, int endX, int endY) {
        this.startXFrame = startX;
        this.startYFrame = startY;
        this.endXFrame = endX;
        this.endYFrame = endY;
    }

    /**
     * @return the x coordinate of the center
     * */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y coordinate of the center
     * */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius
     * */
    public int getSize() {
        return this.r;
    }

    /**
     * @return the color of the ball
     * */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @param surface the surface to draw on him
     * draw the ball on the given DrawSurface.
     * */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), this.r);
    }
    /**
     * @param v new velocity
     * set new velocity to the ball.
     * */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param dx new change in x coordinate
     * @param dy new change in y coordinate
     * set new velocity to the ball.
     * */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);

    }

    /**
     * @param paddle the paddle of the game
     * Set the paddle of the game in Ball.
     * */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    /**
     * @return the velocity of the ball
     * */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Get the ball out from the rectangle.
     * @param rect the shape of the paddle
     * @param center the center of the Ball
     * @param startFrame from where the paddle can move
     * @param endFrame until where the paddle can move
     * */
    private void dontEatMe(Point center, Rectangle rect, int startFrame, int endFrame) {
        if (center.getX() > rect.getUpperLeft().getX()
                && center.getX() < rect.getUpperLeft().getX() + rect.getWidth()
                && center.getY() > rect.getUpperLeft().getY()
                && center.getY() < rect.getUpperLeft().getY() + rect.getHeight()) {
            if (this.center.getX() < rect.getUpperLeft().getX() + rect.getWidth() / 2) {
                center.setY(rect.getUpperLeft().getY() - 2);
                center.setX(rect.getUpperLeft().getX() - Math.abs(this.velocity.getDx()) - 1);
                this.velocity.setDy(-Math.abs(this.velocity.getDy()));
                if (this.velocity.getDx() > 0) {
                    this.velocity.setDx(-this.velocity.getDx());
                }
            } else {
                center.setY(rect.getUpperLeft().getY() - this.velocity.getDy() - 1);
                center.setX(rect.getUpperLeft().getX() + rect.getWidth() + Math.abs(this.velocity.getDx()) + 1);
                this.velocity.setDy(-Math.abs(this.velocity.getDy()));
                if (this.velocity.getDx() < 0) {
                    this.velocity.setDx(-this.velocity.getDx());
                }
            }
            if (this.center.getX() >= endFrame) {
                center.setX(endFrame - 1);
                center.setY(rect.getUpperLeft().getY() - this.r);
            }
            if (this.center.getX() <= startFrame) {
                center.setX(startFrame + 1);
                center.setY(rect.getUpperLeft().getY() - this.r);
            }
        }
    }

    /**
     * place the Ball in different place.
     * */
    public void moveOneStep() {
        if (getX() - r <= startXFrame && this.velocity.getDx() < 0) {
            setVelocity(new Velocity(-this.velocity.getDx(), this.velocity.getDy()));
        }
        if (getY() - r <= startYFrame && this.velocity.getDy() < 0) {
            setVelocity(new Velocity(this.velocity.getDx(), -this.velocity.getDy()));
        }
        if (getX() + r >= endXFrame && this.velocity.getDx() > 0) {
            setVelocity(new Velocity(-this.velocity.getDx(), this.velocity.getDy()));
        }
        if (this.paddle != null) {
            dontEatMe(this.center, this.paddle.getCollisionRectangle(),
                    this.paddle.getStartFrame(), this.paddle.getEndFrame());
        }
        Line traj = new Line(this.center, new Point(this.center.getX() + this.velocity.getDx(),
                        this.center.getY() + this.velocity.getDy()));
        CollisionInfo info = this.gameEnvironment.getClosestCollision(traj);
        if (info != null) {
            Point newP = new Point(info.collisionPoint().getX() - (
                    this.velocity.getDx() / Math.abs(this.velocity.getDx())), info.collisionPoint().getY() - (
                    this.velocity.getDy() / Math.abs(this.velocity.getDy())));
            if (this.velocity.getDy() == 0) {
                newP.setY(this.center.getY());
            }
            if (this.velocity.getDx() == 0) {
                newP.setX(this.center.getX());
            }
            this.center = newP;
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
            return;
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Makes the ball move.
     * */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * the function remove the ball from game g.
     * @param gameLevel the game we want to remove from
     * */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}