import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-05-20 This class count down in each level of the game.
 * // The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) secods, before
// it is replaced with the next one.
 */

public class CountdownAnimation implements Animation {

   private boolean stop;
   private double numOfSeconds;
   private int countFrom;
   private SpriteCollection gameScreen;



   /**
    * Constructor for the countDown class.
    * @param numOfSeconds - num of second to wait
    * @param countFrom - the number we start counting down from
    * @param gameScreen - all the sprites of the game
    */
   public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {

       this.numOfSeconds = numOfSeconds;
       this.countFrom = countFrom;
       this.gameScreen = gameScreen;
       this.stop = false;


        }

   @Override
public void doOneFrame(DrawSurface d) {
       // first draw all the sprites
       this.gameScreen.drawAllOn(d);
       Sleeper sleeper = new Sleeper();
       d.setColor(new Color(255, 70, 70));
       // if the number bigger than 0 draw him
       if (this.countFrom > 0) {
       d.drawText(390, 330, Integer.toString(countFrom), 100);
       }
       if (this.countFrom == 0) {
           d.drawText(300, 350, "GO!!!", 100);
       }
       if (this.countFrom == 3) {
           this.countFrom--;
           return;
       }
       this.countFrom--;
       // wait before replace to the next number
       long time = (long) ((1000 * this.numOfSeconds) / 3);
       sleeper.sleepFor(time);
   }

   @Override
public boolean shouldStop() {

       // conditional stop for the count down
       return this.countFrom < -1;
   }
}