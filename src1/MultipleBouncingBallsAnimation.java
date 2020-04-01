import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
import biuoop.Sleeper;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07
 * This class draw animation loop with multiple balls
 */
public class MultipleBouncingBallsAnimation {

    /**
     * Creates a quantity and size of balls by command line and bounce them on the screen.
     * @param args array of strings with the size of each ball (radius).
     */
    public static void main(String[] args) {

        // temporary array with the size of each ball (type int)
        int[] sizeBalls = stringsToInts(args);
        // create array of balls
        Ball[] ballsArr;
        ballsArr = new Ball[args.length];

        // initialize each ball with random place, size, color and velocity
        for (int i = 0; i < sizeBalls.length; ++i) {

            Point randomPoint = generateRandomPoint();
            ballsArr[i] = new Ball(randomPoint, sizeBalls[i], Color.blue);
            ballsArr[i].setBorder(0, 0, 600, 800);
            // There is inverse ratio between the size of the ball and his velocity
            Velocity v = Velocity.fromAngleAndSpeed(30, 150 / sizeBalls[i]);
            ballsArr[i].setVelocity(v);

        }
        // create gui in size 600x800
        GUI gui = new GUI("Multiple Bouncing Balls", 600, 800);
        Sleeper sleeper = new Sleeper();

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            // draw the ball on the screen and move him each iteration of the loop
            for (int i = 0; i < ballsArr.length; ++i) {

                ballsArr[i].moveOneStep();
                ballsArr[i].drawOn(d);

            }
            gui.show(d);
            sleeper.sleepFor(50);

        }
    }

    /**
     * @param numbers array of strings with the size of the balls
     * @return new array with the size of the ball in type int
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] arr;
        arr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = Integer.parseInt(numbers[i]);
        }
        return arr;

    }

    /**
     * give random x and y to create random point.
     * @return new random point
     */
    public static Point generateRandomPoint() {

        Random rand = new Random();
        // get integer in range 1-600
        int x1 = rand.nextInt(600) + 1;
        // get integer in range 1-800
        int y1 = rand.nextInt(800) + 1;

        Point p = new Point(x1, y1);
        return p;
    }
}
