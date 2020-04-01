/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07
 * This class is in charge of counting the score of the player in the game.
 *
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor for ScoreTrackingListener class.
     * @param scoreCounter - get the first score of the game(0)
     */
    public ScoreTrackingListener(Counter scoreCounter) {
       this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // if removing ball it's 15 points (5 for the hit, destroying a block is worth and additional 10 points)
        if (beingHit.getNumHits() == 0) {
            this.currentScore.increase(15);
        } else {
            this.currentScore.increase(5);
        }
    }
 }