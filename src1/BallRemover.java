/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class is in charge of when a ball reaches the bottom of the screen -
 *  remove him from the game
 */


public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor for this class.
     * @param game - the game
     * @param remainingBalls - the number of ball in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }


    @Override
    public void hitEvent(Block deathBlock, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
        deathBlock.removeHitListener(hitter);

    }

    /**
     *
     * @return the number of remaining balls
     */
    public int getRemainingBalls() {
        return this.remainingBalls.getValue();
    }

}
