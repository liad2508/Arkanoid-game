import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
import biuoop.Sleeper;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07
 * This class draw animation loop with multiple balls and two rectangle
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * Creates a quantity and size of balls by command line. Creates two frames
     * (rectangles) and bounce the ball inside them.
     * @param args array of strings with the size of each ball (radius).
     */
    public static void main(String[] args) {

        GUI gui = new GUI("Multiple Frames", 600, 600);
        // temporary array with the size of each ball (type int)
        int[] sizeBalls = stringsToInts(args);
        // create array of balls
        Ball[] ballsArr;
        ballsArr = new Ball[args.length];
        Sleeper sleeper = new Sleeper();
        // loop that run on the first half of the balls and initialize them
        for (int i = 0; i < sizeBalls.length / 2; ++i) {

            Point randomPoint = generateRandomPoint1();
            ballsArr[i] = new Ball(randomPoint, sizeBalls[i], Color.red);
            // There is inverse ratio between the size of the ball and his velocity
            Velocity v = Velocity.fromAngleAndSpeed(30, 150 / sizeBalls[i]);
            ballsArr[i].setVelocity(v);

        }
        // loop that run on the first half of the balls and initialize them
        for (int j = ballsArr.length / 2; j < ballsArr.length; j++) {

            Point randomPoint = generateRandomPoint2();
            ballsArr[j] = new Ball(randomPoint, sizeBalls[j], Color.black);
            // There is inverse ratio between the size of the ball and his velocity
            Velocity v = Velocity.fromAngleAndSpeed(30, 100 / sizeBalls[j]);
            ballsArr[j].setVelocity(v);
        }

        while (true) {
            DrawSurface rectangle = gui.getDrawSurface();
            // create the first rectangle
            rectangle.setColor(Color.GREEN);
            rectangle.fillRectangle(50, 50, 500, 500);
            // create the second rectangle
            rectangle.setColor(Color.YELLOW);
            rectangle.fillRectangle(450, 450, 600, 600);

            // draw and move the first half of the balls
            for (int i = 0; i < ballsArr.length / 2; ++i) {

                ballsArr[i].setBorder(50, 50, 550, 550);
                ballsArr[i].moveOneStep();
                ballsArr[i].drawOn(rectangle);
            }
            // draw and move the second half of the balls
            for (int j = ballsArr.length / 2; j < ballsArr.length; j++) {

                ballsArr[j].setBorder(450, 450, 600, 600);
                ballsArr[j].moveOneStep();
                ballsArr[j].drawOn(rectangle);
            }

            gui.show(rectangle);
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
    public static Point generateRandomPoint1() {

        Random rand = new Random();
        int x1 = rand.nextInt(450) + 50; // get integer in range 50-500
        int y1 = rand.nextInt(450) + 50; // get integer in range 50-500

        Point p = new Point(x1, y1);
        return p;

    }

    /**
     * give random x and y to create random point.
     * @return new random point
     */
    public static Point generateRandomPoint2() {

        Random rand = new Random();
        int x1 = rand.nextInt(150) + 450; // get integer in range 450-600
        int y1 = rand.nextInt(150) + 450; // get integer in range 450-600

        Point p = new Point(x1, y1);
        return p;
    }

}