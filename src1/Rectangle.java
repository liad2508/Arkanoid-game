import java.util.ArrayList;
import java.util.List;
/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class does some operation on rectangle.
 */
public class Rectangle {

    // Fields
    private Point upperLeft;
    private double width;
    private double height;

    // Create a new rectangle with location and width/height.
    /**
     * Constructor for class rectangle. Create a new rectangle with location and
     * width/height.
     *
     * @param upLeft - the up left point of the rectangle
     * @param width  - width of the rectangle
     * @param height - the height of the rectangle
     */
    public Rectangle(Point upLeft, double width, double height) {

        this.upperLeft = upLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * collect list of intersection points.
     *
     * @param line - the line that the intersection occur with him
     * @return Return a (possibly empty) List of intersection points with the
     *         specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // create lists that save the intersection points
        List<Point> list1 = new ArrayList<Point>();
        List<Point> list2 = new ArrayList<Point>();
        // calculate each line of the rectangle
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point downRight = new Point(this.upperLeft.getX() + this.width, downLeft.getY());
        Line horizonalUp = new Line(this.upperLeft, upperRight);
        Line horizonalDown = new Line(downLeft, downRight);
        Line verticalLeft = new Line(this.upperLeft, downLeft);
        Line verticalRight = new Line(upperRight, downRight);

        // check the intersection of each side of the rectangle with the line (that the
        // ball will move)
        if (horizonalUp.isIntersecting(line)) {
            list2.add(horizonalUp.intersectionWith(line));
        }
        if (horizonalDown.isIntersecting(line)) {
            list2.add(horizonalDown.intersectionWith(line));
        }
        if (verticalLeft.isIntersecting(line)) {
            list2.add(verticalLeft.intersectionWith(line));
        }
        if (verticalRight.isIntersecting(line)) {
            list2.add(verticalRight.intersectionWith(line));
        }

        return list2;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return Point - upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @param speed - the speed of the paddle (the distance he can move at each press on the keyboard
     * move the paddle right if the right key press by the user.
     */
    public void moveToRight(int speed) {
        this.upperLeft = new Point(this.upperLeft.getX() + speed, this.upperLeft.getY());
    }

    /**
     * @param speed - the speed of the paddle (the distance he can move at each press on the keyboard
     * move the paddle left if the left key press by the user.
     */
    public void moveToLeft(int speed) {
        this.upperLeft = new Point(this.upperLeft.getX() - speed, this.upperLeft.getY());
    }

    /**
     * move the ball to "almost" the hit point, but just slightly before it.
     *
     * @param c - contains details about the collision object and the collision
     *          Point
     * @return new Point, the slightly before the hit point
     */
    public Point almostHitPoint(CollisionInfo c) {
        double p1 = 0, p2 = 0;

        // if the hit point is on the horizonal lines of the rectangle
        if (c.collisionPoint().getX() >= c.collisionObject().getCollisionRectangle().upperLeft.getX()
              && c.collisionPoint().getX() <= c.collisionObject().getCollisionRectangle().upperLeft.getX()
                        + c.collisionObject().getCollisionRectangle().getWidth()) {
            // if the hit point on the up horizonal line of the rectangle
            if (c.collisionPoint().getY() <= (c.collisionObject().getCollisionRectangle().upperLeft.getY())) {
                p1 = c.collisionPoint().getX();
                // move the ball little bit up
                p2 = c.collisionPoint().getY() - 5;
            }
            // if the hit point on the down horizonal line of the rectangle
            if (c.collisionPoint().getY() >= (c.collisionObject().getCollisionRectangle().upperLeft.getY()
                    + c.collisionObject().getCollisionRectangle().getHeight())) {
                p1 = c.collisionPoint().getX();
                // move the ball little bit down
                p2 = c.collisionPoint().getY() + 5;
            }
        }
        // if the hit point is on the vertical lines of the rectangle
        if (c.collisionPoint().getY() >= c.collisionObject().getCollisionRectangle().upperLeft.getY()
              && c.collisionPoint().getY() <= c.collisionObject().getCollisionRectangle().upperLeft.getY()
                        + c.collisionObject().getCollisionRectangle().getHeight()) {
            // if it is on the left side
            if (c.collisionPoint().getX() <= c.collisionObject().getCollisionRectangle().upperLeft.getX()) {
                // move the ball little bit left
                p1 = c.collisionPoint().getX() - 5;
                p2 = c.collisionPoint().getY();
            }
            // if it is on the right side
            if (c.collisionPoint().getX() >= c.collisionObject().getCollisionRectangle().upperLeft.getX()
                    + c.collisionObject().getCollisionRectangle().getWidth()) {
                // // move the ball little bit right
                p1 = c.collisionPoint().getX() + 5;
                p2 = c.collisionPoint().getY();
            }
        }
        return new Point(p1, p2);
    }

}
