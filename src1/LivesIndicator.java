import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07
 * This class is in charge of displaying the current live.
 *
 */

public class LivesIndicator  implements Sprite {

    private Counter numberOfLives;
    private Counter remainBalls;
    private LevelInformation levelInformation;

   /**
    *
    * @param numberOfLives - get the number of life in the start of the game
    * @param remainBalls - the number of balls in the game
    * @param level - the level that run now
    */
    public LivesIndicator(Counter numberOfLives, Counter remainBalls, LevelInformation level) {
        this.numberOfLives = numberOfLives;
        this.remainBalls = remainBalls;
        this.levelInformation = level;

    }

    /**
     * add LivesIndicator to the game (as a sprite).
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.black);
        d.drawText(50, 20, "Lives: " + Integer.toString(this.numberOfLives.getValue()), 20);
        d.drawText(480, 20, "Level Name: " + this.levelInformation.levelName(), 20);

    }

    //@Override
    @Override
    public void timePassed() {
        // TODO Auto-generated method stub

    }


}
