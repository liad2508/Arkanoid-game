/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This interface is to sign whenever the beingHit object is hit.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - being hit object
     * @param hitter - the ball
     */
    void hitEvent(Block beingHit, Ball hitter);
 }