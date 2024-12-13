package Background;
import Geometry.Line;
import Geometry.Point;
import Sprites.Collidable;
import Sprites.CollisionInfo;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Nir Akay 207387770
 * */
public class GameEnvironment {
    private List<Collidable> collisions;

    /**
     * constructor.
     * */
    public GameEnvironment() {
        collisions = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     * @param c add a collidable object to the list
     * */
    public void addCollidable(Collidable c) {
        this.collisions.add(c);
    }

    /**
     * remove Collidabe from the list.
     * @param c a Collidable we want to remove
     * */
    public void removeCollidable(Collidable c) {
        this.collisions.remove(c);
    }

    /**
     * @param trajectory the path that the will came through
     * @return  If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point tmp, closestPoint = null;
        Collidable collidable = null;
        for (int i = 0; i < this.collisions.size(); i++) {
            tmp = trajectory.closestIntersectionToStartOfLine(
                    this.collisions.get(i).getCollisionRectangle());
            if (tmp != null) {
                if (closestPoint != null) {
                    if (trajectory.start().distance(tmp) < trajectory.start().distance(closestPoint)) {
                        closestPoint = tmp;
                        collidable = this.collisions.get(i);
                    }
                } else {
                    closestPoint = tmp;
                    collidable = this.collisions.get(i);
                }
            }
        }
        return closestPoint != null ? new CollisionInfo(closestPoint, collidable) : null;
    }
}