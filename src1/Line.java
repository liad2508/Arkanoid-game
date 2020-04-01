import java.util.ArrayList;
import java.util.List;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class does some operations on line.
 * This class does some operation on line.
 */
public class Line {
    // Fields
    private Point p1;
    private Point p2;

    /**
     * Constructor for class line.
     * @param start - location of the start point (x and y)
     * @param end   - location of the end point (x and y)
     */
    public Line(Point start, Point end) {
        this.p1 = start;
        this.p2 = end;
    }

    /**
     * Another constructor for class line.
     * @param x1 The x of the first point
     * @param y1 The y of the first point
     * @param x2 The x of the second point
     * @param y2 The y of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     * @return double - the length of the line
     */

    // Return the length of the line
    public double length() {

        return p1.distance(p2);
    }

    /**
     * Take 2 points and find their middle point.
     * @return Point - middle point
     */
    public Point middle() {

        double midPointX = (p1.getX() + p2.getX()) / 2;
        double midPointY = (p1.getY() + p2.getY()) / 2;
        Point midPoint = new Point(midPointX, midPointY);
        return midPoint;
    }

    /**
     * Returns the start point of the line.
     * @return point - start point of the line
     */
    public Point start() {

        return this.p1;
    }

    /**
     * Returns the end point of the line.
     * @return point - end point of the line
     */
    public Point end() {

        return this.p2;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     * @param other - another line
     * @return true or false
     */
    public boolean isIntersecting(Line other) {
        return (this.intersectionWith(other) != null);
    }

    /**
     * Returns the intersection point if the lines intersect. and null otherwise.
     * @param other - another line
     * @return Point - the intersection point if ther's
     */

    public Point intersectionWith(Line other) {
        double m1;
        double b1;
        double m2;
        double b2;


        // calculate the slope of each line
        m1 = (this.p2.getY() - this.p1.getY()) / (this.p2.getX() - this.p1.getX());
        m2 = (other.p2.getY() - other.p1.getY()) / (other.p2.getX() - other.p1.getX());

        // check if the slope equals
        if (m1 == m2) {
            // if the end of one line is the start of the other line
            if (this.p2.equals(other.p1)) {
                return this.p2;
            }
            if (this.p1.equals(other.p2)) {
                return this.p1;

            // if the lines not intersect or converges return null
            } else {
                return null;
            }
        }

        // Straight line equation
        b1 = this.p1.getY() - m1 * this.p1.getX();
        b2 = other.p1.getY() - m2 * other.p1.getX();

        // Solve System equations to find the potentially intersection point
        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;


         //if the current line is vertical
        if (this.p1.getX() - this.p2.getX() == 0 && this.p1.getX() <= Math.max(other.p1.getX(), other.p2.getX())
                && this.p1.getX() >= Math.min(other.p1.getX(), other.p2.getX())) {

            b2 = other.p2.getY() - (this.p1.getX() * m2);
            y = m2 * this.p1.getX()  + b2;
            if (y <= Math.max(this.p1.getY(), this.p2.getY()) && y >= Math.min(this.p1.getY(), this.p2.getY())) {
            return new Point(this.p1.getX(), m2 * this.p1.getX() + b2);
        }
    }

        Point intersectPoint = new Point(x, y);

        // check if the potentially intersection point located on the two lines
        if (intersectPoint.getX() <= Math.max(this.p1.getX(), this.p2.getX())
                && intersectPoint.getX() >= Math.min(this.p1.getX(), this.p2.getX())
                && intersectPoint.getX() <= Math.max(other.p1.getX(), other.p2.getX())
                && intersectPoint.getX() >= Math.min(other.p1.getX(), other.p2.getX())) {
            return intersectPoint;
        } else {
            return null;
        }
    }


    /**
     * equals - return true if the lines are equal, false otherwise.
     * @param other - another line
     * @return true of false
     */
    public boolean equals(Line other) {
        return (p1.equals(other.p1) && p2.equals(other.p2));
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect - the rectangle that we check if the line intersect with
     * @return closest intersection point (if there is)
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // create list of points
        List<Point> list = new ArrayList<Point>();
        // create the line
        Line l1 = new Line(this.p1, this.p2);
        // collect all the intersection point
        list = rect.intersectionPoints(l1);
        if (list.isEmpty()) {
            return null;
        }
        // find the closest point
        double minDistance = list.get(0).distance(this.p1);
        int index = 0;
        for (int i = 1; i < list.size(); i++) {
                if ((list.get(i).distance(this.p1)) <= minDistance) {
                    minDistance = list.get(i).distance(this.p1);
                    index = i;
                }
            }

            return list.get(index);
        }

    /**
     * find the closest point to the start of the line.
     * @param list of points
     * @return the closest Point
     */
    public Point minDidstance(List<Point> list) {

        double minDistance = this.p1.distance(list.get(0));
        Point minPoint = list.get(0);
        for (Point p : list) {

            if (this.p1.distance(p) <= minDistance) {
                minPoint = p;
                minDistance = this.p1.distance(p);
            }

        }
        return minPoint;
    }
 }

