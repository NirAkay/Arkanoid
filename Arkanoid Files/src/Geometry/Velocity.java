package Geometry;
/**
 *@author Nir Akay 207387770
 **/
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx the change in the x's axis
     * @param dy the change in the y's axis
     * */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return the change in the X axis
     * */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the change in the Y axis
     * */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param dy new change in Y axis
     * set a new change in Y axis
     * */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * @param dx new change in X axis
     * set a new change in X axis
     * */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * @param p the point we want to move
     * @return return the point in another place
     * */
    public Point applyToPoint(Point p) {
        return new Point(this.dx + p.getX(), this.dy + p.getY());
    }

    /**
     * @param angle the angle of the change direction
     * @param speed the speed of the change
     * @return new velocity that change by angle and speed
     * */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx, dy;
        while (angle < 0) {
            angle += 360;
        }
        if ((angle > 270)) {
            angle -= 270;
            dx = speed * -Math.cos(Math.toRadians(angle));
            dy = speed * -Math.sin(Math.toRadians(angle));
        } else {
            dx = speed * Math.sin(Math.toRadians(angle));
            dy = speed * -Math.cos(Math.toRadians(angle));
        }
        return new Velocity(dx, dy);
    }
}