package Geometry;
/**
 * @author Nir Akay 207387770
 * */
public class Line {
    private Point start;
    private Point end;

    /**
     * @param start the point where the line start
     * @param end the point where the line end
     * constructor.
     * */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @param x1 coordinate for start
     * @param x2 coordinate for start
     * @param y1 coordinate for end
     * @param y2 coordinate for end
     * constructor.
     * */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line
     * */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * @return the middle point of the line
     * */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * @return the start point of the line
     * */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     * */
    public Point end() {
        return this.end;
    }

    /**
     * @param end present the end point of line
     * @param start present the start point of line
     * @return return the incline of the line
     * */
    private double incline(Point start, Point end) {
        if (start.getX() == end.getX()) {
            return 0;
        }
        return (start.getY() - end.getY()) / (start.getX() - end.getX());
    }

    /**
     * @param x a value of coordinate
     * @param y a value of coordinate
     * @param inc the incline of a line
     * @return the c value of line for the equation "y = mx + c"
     * */
    private double getC(double x, double y, double inc) {
        return y - (x * inc);
    }

    /**
     * @param other line we want to check
     * @param line line  we want to check
     * @param c1 the c value of line
     * @param c2 the c value of line other
     * @param inc1 the incline of line
     * @param inc2 the incline of other
     * @return the intersection point
     * */
    private Point parallelAxis(Line line, Line other, double inc1, double inc2, double c1, double c2) {
        if (line.start().getX() == line.end().getX()) {
            if (inTheMiddleOfTwoLines(line, other, line.start().getX(), line.start().getX() * inc2 + c2)) {
                return null;
            }
            return new Point(line.start().getX(), line.start().getX() * inc2 + c2);
        }
        if (other.start().getX() == other.end().getX()) {
            if (inTheMiddleOfTwoLines(other, line, other.start().getX(), other.start().getX() * inc1 + c1)) {
                return null;
            }
            return new Point(other.start().getX(), other.start().getX() * inc1 + c1);
        }
        return null;
    }

    /**
     * @param p1 first end point
     * @param p2 second end point
     * @param x coordinate to check if he is in the line
     * @param y coordinate to check if he is in the line
     * @return true if x,y are between the points else false
     * */
    private boolean inTheMiddle(Point p1, Point p2, double x, double y) {
        if (x >= p1.getX() && x <= p2.getX() && y >= p1.getY() && y <= p2.getY()) {
            return true;
        }
        return x >= p2.getX() && x <= p1.getX() && y >= p1.getY() && y <= p2.getY();
    }

    /**
     * @param l1 line we want to check
     * @param l2 line we want to check
     * @param y coordinate we want to check
     * @param x coordinate we want to check
     * @return true if our coordinates are not inside our lines
     * */
    private boolean inTheMiddleOfTwoLines(Line l1, Line l2, double x, double y) {
        if (!(inTheMiddle(l1.start(), l1.end(), x, y)
                || inTheMiddle(l1.end(), l1.start(), x, y))) {
            return true;
        }
        return (!(inTheMiddle(l2.start(), l2.end(), x, y)
                || inTheMiddle(l2.end(), l2.start(), x, y)));
    }

    /**
     * @param other the line that he check if he is intersection with him
     * @return true if the lines intersect, false otherwise
     * */
    public boolean isIntersecting(Line other) {
        if (equals(other)) {
            return true;
        }
        double inc1 = incline(this.start, this.end), inc2 = incline(other.start(), other.end());
        if (inc1 == inc2) {
            if (inTheMiddle(this.start, this.end, other.end().getX(), other.end().getY())) {
                return true;
            }
            if (inTheMiddle(this.start, this.end, other.start().getX(), other.start().getY())) {
                return true;
            }
            if (inTheMiddle(other.start(), other.start(), this.start.getX(), this.start.getY())) {
                return true;
            }
            if (inTheMiddle(other.start(), other.start(), this.end.getX(), this.end.getY())) {
                return true;
            }
        }
        return intersectionWith(other) != null;
    }

    /**
     * @param other line we want to get their intersection
     * @return the intersection point if the lines intersect and null otherwise
     * */
    public Point intersectionWith(Line other) {
        double inc1 = incline(this.start, this.end), inc2 = incline(other.start(), other.end());
        if (inc1 == inc2) {
            //check if the lines start from the end of the other
            if (!equals(other) && (this.start.equals(other.start()) || this.start.equals(other.end()))
                    && !inTheMiddle(other.start(), other.end(), this.end.getX(), this.end.getY())) {
                //check if the other point is not in the other line
                if (this.start.equals(other.start()) && inTheMiddle(this.start, this.end,
                        other.end().getX(), other.end().getY())) {
                    return null;
                }
                //check if the other point is not in the other line
                if (this.start.equals(other.end()) && inTheMiddle(this.start, this.end,
                        other.start().getX(), other.start().getY())) {
                    return null;
                }
                return this.start;
            }
            //check if the lines start from the end of the other
            if (!equals(other) && (this.end.equals(other.start()) || this.end.equals(other.end()))
                    && !inTheMiddle(other.start(), other.end(), this.start.getX(), this.start.getY())) {
                //check if the other point is not in the other line
                if (this.end.equals(other.start()) && inTheMiddle(this.start, this.end,
                        other.end().getX(), other.end().getY())) {
                    return null;
                }
                //check if the other point is not in the other line
                if (this.end.equals(other.end()) && inTheMiddle(this.start, this.end,
                        other.start().getX(), other.start().getY())) {
                    return null;
                }
                return this.end;
            }
            //check if our lines intersecting like plus
            if (other.start().getX() == other.end().getX() && this.start.getY() == this.end.getY()) {
                //check if the other point is not in the other line
                if (inTheMiddleOfTwoLines(new Line(this.start, this.end), other,
                        other.start().getX(), this.start.getY())) {
                    return null;
                }
                return new Point(other.start().getX(), this.start.getY());
            }
            //check if our lines intersecting like plus
            if (other.start().getY() == other.end().getY() && this.start.getX() == this.end.getX()) {
                //check if the other point is not in the other line
                if (inTheMiddleOfTwoLines(new Line(this.start, this.end), other,
                        this.start.getX(), other.start().getY())) {
                    return null;
                }
                return new Point(this.start.getX(), other.start().getY());
            }
            return null;
        }
        double c1 = getC(this.start.getX(), this.start.getY(), inc1);
        double c2 = getC(other.start().getX(), other.start().getY(), inc2);
        double x = (c1 - c2) / (inc2 - inc1), y = x * inc1 + c1;
        //if we have line parallel to X axis
        if (this.end.getY() == this.start.getY()) {
            y = this.end.getY();
        }
        //if we have line parallel to X axis
        if (other.start().getY() == other.end().getY()) {
            y = other.end().getY();
        }
        //if we have line parallel to Y axis
        if (this.end.getX() == this.start.getX() || other.start().getX() == other.end().getX()) {
            return parallelAxis(new Line(this.start, this.end), other, inc1, inc2, c1, c2);
        }
        if (inTheMiddleOfTwoLines(new Line(this.start, this.end), other, x, y)) {
            return null;
        }
        return new Point(x, y);
    }

    /**
     * @param other line to check if he equal to this line
     * @return true is the lines are equal, false otherwise
     * */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end)
                || this.start.equals(other.end) && this.end.equals(other.start);
    }

    /**
     * @param rect a rectangle we want to check
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> points = rect.intersectionPoints(new Line(this.start, this.end));
        Point closest = null;
        if (!points.isEmpty()) {
            closest = points.remove(0);
        }
        for (Point p : points) {
            if (p.distance(this.start) < closest.distance(this.start)) {
                closest = p;
            }
        }
        return closest;
    }
}