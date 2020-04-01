
/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class does some operations on line.
 * This class does some operation on velocity of the ball.
 */

public class Velocity {

    // Fields
    private double dx;
    private double dy;



    /**
     * constructor for class velocity.
     * @param dx change in position on the `x` axes.
     * @param dy change in position on the `y` axes.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Return the location on the x axes.
     * @return double - dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * initialize  the field dx.
     * @param dx1 location on the X axes
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }

    /**
     * Return the location on the Y axes.
     * @return double - dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * initialize  the field dy.
     * @param dy1 location on the Y axes
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }


    /**
     *  Take a point with position (x,y) and return a new point.
     *    with position (x+dx, y+dy).
     * @param p the current point
     * @return new point
     */
    public Point applyToPoint(Point p) {
        double newPointX = this.dx + p.getX();
        double newPointY = this.dy + p.getY();
        Point newPoint = new Point(newPointX, newPointY);
        return newPoint;

    }

    /**
     * specify the velocity in terms and angle and a speed.
     * Dismantling elements for x-axis and y-axis.
     * @param angle Angle of the ball
     * @param speed Speed of the ball
     * @return velocity of the ball
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRadian = Math.toRadians(angle);
        double dx = Math.sin(angleRadian) * speed;
        double dy = -Math.cos(angleRadian) * speed;
        return new Velocity(dx, dy);
    }
}
