/*import java.awt.Color;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
*/

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class does some operation on collisionInfo - contains collision point and
 * collidable object.
 */
public class CollisionInfo {
    // fields
    private Point collisionInfo;
    private Collidable collidableObject;
    /**
     * Constructor for class block, initialize the collision point and the object to collide with.
     * @param p - collision point
     * @param object - object to collide with
     */
    public CollisionInfo(Point p, Collidable object) {
        this.collisionInfo = p;
        this.collidableObject = object;
    }

    /**
     * the point at which the collision occurs.
     * @return point - get the collision point
     */
       public Point collisionPoint() {
           return this.collisionInfo;
       }

       /**
        *
        * @return the collidable object involved in the collision
        */
       public Collidable collisionObject() {
           return this.collidableObject;
       }
}
