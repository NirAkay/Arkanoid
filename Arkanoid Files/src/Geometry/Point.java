package Geometry;
/**
 * @author Nir Akay 207387770
 * */
public class Point {
    private double x;
    private double y;
    private static final double EPSILON = Math.pow(10, -10);

    /**
     * constructor.
     * @param x a coordinate
     * @param y a coordinate
     * */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other second point to calculate the distance between them
     * @return the distance of this point to the other point
     * */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }
    /**
     * @param other point to equal with
     * @return true is the points are equal, false otherwise
     * */
    public boolean equals(Point other) {
        return Math.abs(other.getX() - this.x) <= EPSILON && Math.abs(other.getY() - this.y) <= EPSILON;
    }

    /**
     * @return the x coordinate
     * */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     * */
    public double getY() {
        return this.y;
    }

    /**
     * set new coordinate.
     * @param x coordinate
     * */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * set new coordinate.
     * @param y coordinate
     * */
    public void setY(double y) {
        this.y = y;
    }
}