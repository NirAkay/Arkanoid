package Geometry;
import java.util.ArrayList;

/**
 *@author Nir Akay 207387770
 **/
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * @param upperLeft start point
     * @param height of our rectangle
     * @param width of our rectangle
     * constructor
     * */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * @param line a line we want to check
     * @return a (possibly empty) List of intersection points
     * with the specified line.
     * */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> ret = new ArrayList<>();
        java.util.List<Line> lines = new ArrayList<>();
        Point[] corners = {new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height),
        new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()), this.upperLeft,
                new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height)};
        for (int i = 0; i < corners.length; i++) {
            lines.add(new Line(corners[i], corners[(i + 1) % corners.length]));
        }
        for (Line lineI : lines) {
            Point p = line.intersectionWith(lineI);
            if (p != null && !ret.contains(p)) {
                ret.add(p);
            }
        }
        return ret;
    }

    /**
     * @return width
     * */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return height
     * */
    public double getHeight() {
        return  this.height;
    }

    /**
     * @return the base point of this rectangle
     * */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @param newUpper new upperLeft point
     * */
    public void setUpperLeft(Point newUpper) {
        this.upperLeft = newUpper;
    }
}