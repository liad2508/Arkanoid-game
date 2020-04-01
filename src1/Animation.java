import biuoop.DrawSurface;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-05-20.
 * This interface does some operation on animation objects.
 */


public interface Animation {
    /**
     * do one frame each time (loop).
     * @param d - the surface to draw on
     */
    void doOneFrame(DrawSurface d);
    /**
     * A condition to stop the animation.
     * @return boolean - true or false
     */
    boolean shouldStop();



}
