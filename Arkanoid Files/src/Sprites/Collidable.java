package Sprites;
import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;

/**
 * @author Nir Akay 207387770
 * */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     * */
    Rectangle getCollisionRectangle();

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
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}