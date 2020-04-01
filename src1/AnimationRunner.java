import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-05-20 This class run all the animation in the game.
 */

public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor for all the animations.
     * @param framesPerSecond - number of frames per second
     * @param gui - the gui of the game
     * @param sleeper - sleeper for wait a time
     */
    public AnimationRunner(int framesPerSecond, GUI gui, Sleeper sleeper) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
        this.sleeper = sleeper;

    }
    /**
     *
     * @param animation - get the current animation and run it
     */
    public void run(Animation animation) {
       int millisecondsPerFrame = 1000 / this.framesPerSecond;
       while (!animation.shouldStop()) {
          long startTime = System.currentTimeMillis(); // timing
          DrawSurface d = gui.getDrawSurface();

          animation.doOneFrame(d);


          gui.show(d);
          long usedTime = System.currentTimeMillis() - startTime;
          long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
          if (milliSecondLeftToSleep > 0) {
              this.sleeper.sleepFor(milliSecondLeftToSleep);
          }
       }

    }
 }