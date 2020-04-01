import java.awt.Color;
import java.util.List;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-05-20
 * This interface is to contain infornation about each level like number of balls,
 * velocities, how the background and the blocks looks like and ets.
 */

public interface LevelInformation {
    /**
     *
     * @return number of balls in this level
     */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    /**
     *
     * @return list of velocities of all the balls in this level
     */
    List<Velocity> initialBallVelocities();
    /**
     *
     * @return the paddle of the speed (the distance he can move at each press on the keyboard
     */
    int paddleSpeed();
    /**
     *
     * @return the width of the paddle
     */
    int paddleWidth();
    /**
     *
     * @return Point - x and y of the left up point of the paddle
     */
    Point paddleLocation();

    /**
     * the level name will be displayed at the top of the screen.
     * @return the name of the level
     */
    String levelName();

    /**
     *
     * @return Sprite - Returns a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return List of blocks
     */
    List<Block> blocks();
    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    /**
     * @return number of blocks to remove
     */
    int numberOfBlocksToRemove();
    /**
     * create all the balls of the level with their size, location, color.
     * @return list of balls
     */
    List<Ball> balls();
    /**
     * add each level as a sprite to the game in order to draw him.
     * @param g the game level
     */
    void addToGame(GameLevel g);
    /**
     *
     * @return number of hits that need to destroy blocks in this level
     */
    int getNumHits();
    /**
     *
     * @return the color of the background
     */
    Color getColor();

 }