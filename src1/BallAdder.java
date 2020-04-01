import java.awt.Color;
/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class is in charge of when a ball hit special block
 * add one more ball to the game.
 *
 */
public class BallAdder implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;
    private GameEnvironment environment;

    /**
     * constructor for this class.
     * @param game - the game
     * @param remainingBalls - need to know how many balls we already have
     * @param environment - the ball need to know his environment (colliadbles)
     */
    public BallAdder(GameLevel game, Counter remainingBalls, GameEnvironment environment) {
        this.game = game;
        this.remainingBalls = remainingBalls;
        this.environment = environment;
    }


    @Override
    public void hitEvent(Block deathBlock, Ball hitter) {
        // when hit the special block, create new ball and add him to the game
        Ball ball1 = new Ball(60, 300, 5, Color.MAGENTA);
        this.remainingBalls.increase(1);
        Velocity v = Velocity.fromAngleAndSpeed(25, 7);
        ball1.setVelocity(v);
        ball1.setBorder(0, 0, 800, 600);
        ball1.addToGame(this.game);
        ball1.setGame(this.environment);
    }

    /**
    *
    * @return the number of remaining balls
    */
    public int getRemainingBalls() {
        return this.remainingBalls.getValue();
    }

}