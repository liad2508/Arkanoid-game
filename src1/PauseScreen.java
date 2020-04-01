import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-05-20 This class enable to pause the game.
 */

public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private KeyPressStoppableAnimation keyPressStoppableAnimation;

    /**
     * Constructor for the pause screen.
     * @param k - the keyboard
     */
    public PauseScreen(KeyboardSensor k) {
       this.keyboard = k;
       this.stop = false;
       this.keyPressStoppableAnimation = new KeyPressStoppableAnimation(this.keyboard,
               KeyboardSensor.SPACE_KEY, this, this.stop);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
       d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
       /*
       if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
           this.stop = true;
           }
           */

       this.keyPressStoppableAnimation.doOneFrame(d);

    }
    @Override
    public boolean shouldStop() {
        //return this.stop;
        return this.keyPressStoppableAnimation.shouldStop();
        }
 }