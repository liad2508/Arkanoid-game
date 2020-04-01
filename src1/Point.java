
/**
* @author Liad Cohen <liad.cohen2508@gmail.com>
* @version 1.6
* @since 2019-03-07
* This class does some operation on point.
*/
public class Point {
    // Fields
    private double x;
    private double y;

    /**
    * Constructor for class point.
    * @param x - location of the x point
    * @param y - location of the y point
    */
    public Point(double x, double y) {
    this.x = x;
    this.y = y;
    }

    /**
    * distance - return the distance of this point to the other point.
    * @param other Another point.
    * @return double - Distance between 2 points.
    */
    public double distance(Point other) {
    return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
    * equals - return true if the points are equal, false otherwise.
    * @param other Another point.
    * @return boolean - true or false.
    */
    public boolean equals(Point other) {
    return (this.x == other.x && this.y == other.y);
    }

   /**
   * Return the x value of this point.
   * @return double - X value.
   */
   public double getX() {
    return this.x;
    }
   /**
   * Return the y value of this point.
   * @return double - Y value. */
   public double getY() {
    return this.y;
    }
  }