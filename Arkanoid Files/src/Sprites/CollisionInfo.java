package Sprites;

import Geometry.Point;

/**
 * @author Nir Akay 207387770
 * */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor.
     * @param p the point at which the collision occurs.
     * @param obj the collidable object involved in the collision.
     * */
    public CollisionInfo(Point p, Collidable obj) {
        this.collisionPoint = p;
        this.collisionObject = obj;
    }

    /**
     * @return the point at which the collision occurs.
     * */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     * */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}