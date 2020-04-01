/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This interface does add and remove listeners from the list.
 */

public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - the listener to be added
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - the listener to be removed
     */
    void removeHitListener(HitListener hl);
 }