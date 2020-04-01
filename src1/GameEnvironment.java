import java.util.ArrayList;
import java.util.List;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class does some operation on game environment such as collidables.
 */
public class GameEnvironment {

    // field - list of collidables
    private List<Collidable> collidables;

    /**
     * Constructor for class gameEnvironment. create list with collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the collidable to be added to the list
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * remove the given collidable from the environment.
     * @param c the collidable to be removed from the list
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }


    /**
     * find the minimum distance between point and the collidables.
     *
     * @param list - list that contains optional intersection points and the
     *  object to collide with
     * @param currentPoint The point from which the distance is measured
     * @return CollisionInfo - with the minimum sidtance of the point and the object
     *         to collide with
     */
    private CollisionInfo minimumDidstance(List<CollisionInfo> list, Point currentPoint) {
        // save the distance from the current point to the first point in the list
        double minDistance = currentPoint.distance(list.get(0).collisionPoint());
        Point minPoint = list.get(0).collisionPoint();
        CollisionInfo temp = new CollisionInfo(minPoint, list.get(0).collisionObject());
        // loop that run all the collision info
        for (CollisionInfo c : list) {
            if (currentPoint.distance(c.collisionPoint()) <= minDistance) {
                minPoint = c.collisionPoint();
                minDistance = currentPoint.distance(c.collisionPoint());
                temp = new CollisionInfo(minPoint, c.collisionObject());
            }
        }
        return temp;
    }

    /**
     * the ball moving from line.start() to line.end(). If the ball will not collide
     * with any of the collidables in this collection, return null. Else, return the
     * information about the closest collision that is going to occur.
     *
     * @param trajectory - line that the ball move on him
     * @return CollisionInfo - information about the closest collision that is going
     *         to occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        // create lists of points and collisionInfo (that contains points and
        // collidables)
        List<CollisionInfo> list1 = new ArrayList<>();
        List<Point> list2 = new ArrayList<Point>();
        for (Collidable c : collidables) {
            // find the intersection points with each collidables
            list2 = (c.getCollisionRectangle().intersectionPoints(trajectory));
            if (!list2.isEmpty()) {
                // save in list of collisionInfo the closest point and the object
                CollisionInfo tempCollisionInfo = new CollisionInfo(minDidstance(list2, trajectory.start()), c);
                list1.add(tempCollisionInfo);
                list2.clear();
            }
        }
        // if the ball will not collide with any of the collidables
        if (list1.isEmpty()) {
            return null;
        // find the closest collision
        } else {
            return minimumDidstance(list1, trajectory.start());
        }
    }

    /**
     * find the closest point from point to list of points.
     *
     * @param list of points
     * @param currentPoint - The point from which the distance is measured
     * @return the closet point
     */
    private Point minDidstance(List<Point> list, Point currentPoint) {
        // save the first distance from the current point
        double minDistance = currentPoint.distance(list.get(0));
        Point minPoint = list.get(0);
        for (Point p : list) {

            if (currentPoint.distance(p) <= minDistance) {
                minPoint = p;
                minDistance = currentPoint.distance(p);
            }
        }
        return minPoint;
    }
}
