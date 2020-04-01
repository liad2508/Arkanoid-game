import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class is in charge of display and stop displaying animation
 * according to the key press of the player.
 *
 */

public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private  boolean stop = false;
    private boolean isAlreadyPressed = true;


    /**
     *
     * @param sensor - the keyboard
     * @param key - the key that stop the animation
     * @param animation - the animation to run
     * @param stop - boolean, to know to stop the animation
     */
   public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation, boolean stop) {
   this.sensor = sensor;
   this.key = key;
   this.animation = animation;
   this.stop = stop;

}

@Override
public void doOneFrame(DrawSurface d) {


    if (this.sensor.isPressed(key)) {
        if (isAlreadyPressed) {
            return;
        }
        this.stop = true;

    } else {
        this.isAlreadyPressed = false;
    }


}

/**
 * setter for stop.
 * @param b - boolean
 */
public void setStop(boolean b) {
    this.stop = false;
}

@Override
public boolean shouldStop() {

    return this.stop;

}
}