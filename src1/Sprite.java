import biuoop.DrawSurface;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This interface does some operation on sprites.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d - the surface to draw on
     */
       void drawOn(DrawSurface d);
       /**
        * notify the sprite that time has passed.
        */
       void timePassed();
}
