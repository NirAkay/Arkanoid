package Sprites;
import Background.GameLevel;
import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Listener.HitListener;
import Listener.HitNotifier;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nir Akay 207387770
 * */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * @param color the color of the rectangle
     * @param rectangle a rectangle
     * constructor
     * */
    public Block(Rectangle rectangle, Color color) {
        this.color = color;
        this.rect = rectangle;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return the shape of this block
     * */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * @param collisionPoint the point we have collision
     * @param currentVelocity the velocity of the ball
     * @param hitter the hitter ball
     * @return new velocity after the hit
     * */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        if ((collisionPoint.getX() == this.rect.getUpperLeft().getX()
                || collisionPoint.getX() == this.rect.getUpperLeft().getX() + this.rect.getWidth())
                && (collisionPoint.getY() == this.rect.getUpperLeft().getY()
                || collisionPoint.getY() == this.rect.getUpperLeft().getY() + this.rect.getHeight())) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if ((collisionPoint.getX() == this.rect.getUpperLeft().getX()
                || collisionPoint.getX() == this.rect.getUpperLeft().getX() + this.rect.getWidth())) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (collisionPoint.getY() == this.rect.getUpperLeft().getY()
                || collisionPoint.getY() == this.rect.getUpperLeft().getY() + this.rect.getHeight()) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * @param d the surface of the screen
     * */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * We add this block to the game.
     * @param g the game we want to add this block to
     * */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * nothing.
     * */
    public void timePassed() {
    }

    /**
     * the function remove the ball from the game.
     * @param gameLevel a game we want to remove from him
     * */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * the function activate all the listeners.
     * @param hitter the hitter ball*/
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * the function add HitListener to the list.
     * @param hl a HitListener
     * */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * the function remove HitListener from the list.
     * @param hl a HitListener
     * */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
