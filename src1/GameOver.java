import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class is in charge of display the end screen of the game.
 *
 */

public class GameOver implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private KeyPressStoppableAnimation keyPressStoppableAnimation;
    private Counter numberOfLives;
    private Counter score;

    /**
     * constructor for end screen.
     * @param k - the keyboard
     * @param numberOfLives - numbre of lives
     * @param score - the score of the player at the end of the game
     */
    public GameOver(KeyboardSensor k, Counter numberOfLives, Counter score) {
       this.keyboard = k;
       this.stop = false;
       this.keyPressStoppableAnimation = new KeyPressStoppableAnimation(this.keyboard,
               KeyboardSensor.SPACE_KEY, this, this.stop);
       this.numberOfLives = numberOfLives;
       this.score = score;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // if the player lose
        if (this.numberOfLives.getValue() == 0) {
            d.setColor(new Color(255, 0, 0));
            d.drawText(200, 300, "Game Over. Your score is " + this.score.getValue(), 30);

        // if the player win the game
        } else {
            d.setColor(new Color(25, 151, 227));
            d.drawText(200, 300, "You Win!! Your score is " + this.score.getValue(), 30);
        }
        this.keyPressStoppableAnimation.doOneFrame(d);


    }

    @Override
    public boolean shouldStop() {
        return this.keyPressStoppableAnimation.shouldStop();
    }

}
