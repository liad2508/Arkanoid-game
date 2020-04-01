import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07
 * This class draw animation loop with one ball
 */
public class BouncingBallAnimation {

    /**
     * draw one ball and move him on the screen.
     * @param args array of strings
     */
    public static void main(String[] args) {

        // initialize the ball fields with the constructor
        Ball ball = new Ball(0, 0, 30, java.awt.Color.black);
        // The size of the gui
        GUI gui = new GUI("title", 200, 200);
        ball.setBorder(0, 0, 200, 200);
        Sleeper sleeper = new Sleeper();
        ball.setVelocity(2, 2);

        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            // wait for 50 milliseconds
            sleeper.sleepFor(50);

        }
    }
}
