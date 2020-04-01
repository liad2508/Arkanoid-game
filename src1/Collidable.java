/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class does some operations on line.
 * This interface does some operation on collidables objects.
 */

public interface Collidable {

    /**
     *
     * @return  Return the "collision shape" of the object.
     */
       Rectangle getCollisionRectangle();

       /**
        * Notify the object that we collided with it at collisionPoint with
        *a given velocity.
        * The return is the new velocity expected after the hit (based on
        * the force the object inflicted on us).
        * @param collisionPoint - the collision point between the lines
        * @param currentVelocity - of the ball
        * @param hitter - the hitter ball
        * @return the new velocity after the hit
        */
       Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);


}
