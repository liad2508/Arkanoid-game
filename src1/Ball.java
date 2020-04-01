import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class does some operation on ball.
 */

public class Ball implements Sprite, HitListener {

    // Fields
    private Point center;
    private int radius;
    private Color color;
    private Velocity v;
    private Point guiP1;
    private Point guiP2;
    private GameEnvironment gameEnvironment;

    /**
     * Constructor for class ball.
     * @param center - center of the ball (contain x and y)
     * @param r - radius of the ball
     * @param color - color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {

    this.center = center;
    this.radius = r;
    this.color = color;
    this.v = new Velocity(0, 0);
    }

    /**
     * another constructor for class Ball.
     * @param x The x of the center of the ball
     * @param y The y of the center of the ball
     * @param r - radius of the ball
     * @param color - color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
    this.center = new Point(x, y);
    this.radius = r;
    this.color = color;
    this.v = new Velocity(0, 0);

    }

    /**
     * Return the x value of the center.
     * @return  X value
     */

    public int getX() {
    return (int) this.center.getX();
    }

    /**
     * Return the y value of the center.
     * @return  Y value
     */
    public int getY() {
    return (int) this.center.getY();
    }

    /**
     * Return the radius of the ball.
     * @return  radius
     */
    public int getSize() {
    return this.radius;
    }

    /**
     * Return the color of the ball.
     * @return  color
     */
    public java.awt.Color getColor() {
    return this.color;

    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface  A surface to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {

    surface.setColor(this.getColor());
    surface.fillCircle((int) center.getX(), (int) center.getY(), this.radius);
    }

    /**
     * initialize the field velocity of ball (class).
     * @param vel - velocity of the ball
     */
    public void setVelocity(Velocity vel) {
    this.v = vel;

    }

    /**
     * initialize the field velocity of ball (class).
     * @param dx - change in position on the `x` axes.
     * @param dy - change in position on the `y` axes.
     */
    public void setVelocity(double dx, double dy) {
    this.v = new Velocity(dx, dy);

    }

    /**
     * Return the velocity of the ball.
     * @return velocity
     */
    public Velocity getVelocity() {
    return this.v;

    }

    /**
     * initialize the fields guiP1 and guiP2 of ball.
     * @param x1 The x of the first point
     * @param y1 The y of the first point
     * @param x2 The x of the second point
     * @param y2 The y of the second point
     */
    public void setBorder(int x1, int y1, int x2, int y2) {
    this.guiP1 = new Point(x1, y1);
    this.guiP2 = new Point(x2, y2);
    }

    /**
     * Return the the x of the first point.
     * @return x of the first point
     */
    public int getGuiX1() {
    return (int) this.guiP1.getX();
    }

    /**
     * Return the the y of the first point.
     * @return y of the first point
     */
    public int getGuiY1() {
    return (int) this.guiP1.getY();
    }

    /**
     * Return the the x of the second point.
     * @return x of the first point
     */
    public int getGuiX2() {
    return (int) this.guiP2.getX();
    }

    /**
     * Return the the y of the first point.
     * @return y of the first point
     */
    public int getGuiY2() {
    return (int) this.guiP2.getY();
    }

    /**
     * set the border for the ball.
     * change the movement of the ball according of his collisions with the collidables
     * such as blocks and the paddle.
     */
    public void moveOneStep() {
    // the line the ball move on him
    Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
    // Check if moving on this trajectory will hit anything
    CollisionInfo hitPoint = this.gameEnvironment.getClosestCollision(trajectory);

    // If there is no collision the ball continues to move regally
    if (hitPoint == null) {
        this.center = this.getVelocity().applyToPoint(this.center);
    } else {
        // move the ball to "almost" the hit point
        Point p = hitPoint.collisionObject().getCollisionRectangle().almostHitPoint(hitPoint);
        this.center = this.getVelocity().applyToPoint(p);
        // notify the hit object that a collision occurred and update the velocity to the new velocity.
        this.v = hitPoint.collisionObject().hit(this, hitPoint.collisionPoint(), this.v);
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    //this.center = this.getVelocity().applyToPoint(this.center);

    // Sets the borders on the right and the left
    if ((this.center.getX() >= this.guiP2.getX() - this.radius && this.v.getDx() >= 0)
    || (this.center.getX() <= this.radius + this.guiP1.getX() && this.v.getDx() <= 0)) {

    this.v.setDx(-this.v.getDx());
    }
    // Sets the limits up and down
    if ((this.center.getY() >= this.guiP2.getY() - this.radius && this.v.getDy() >= 0)
    || this.center.getY() <= this.radius + this.guiP1.getY() && this.v.getDy() <= 0) {

    this.v.setDy(-this.v.getDy());
        }
    }
    /**
     * get the game environment.
     * @return the gameEnvironment
     */
    public GameEnvironment game() {
        return this.gameEnvironment;
    }
    /**
     * set the game environment.
     * @param g - the game environment
     */
    public void setGame(GameEnvironment g) {
        this.gameEnvironment = g;
    }
    /**
     * called move one step.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * add the ball to the game as a sprite.
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove the ball from the game as a sprite.
     * @param g - the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.addHitListener(hitter);
    }
}
