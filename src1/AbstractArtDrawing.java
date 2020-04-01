import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class draw 10 lines in black and their middle point at
 * blue and intersection points in red
 */
public class AbstractArtDrawing {

    /**
     * create 2 random points for random line.
     * @return Line - random line
     */
    Line generateRandomLine() {

        Random rand = new Random();
        // get integer in range 1-400
        int x1 = rand.nextInt(400) + 1;
        // get integer in range 1-300
        int y1 = rand.nextInt(300) + 1;
        int x2 = rand.nextInt(400) + 1;
        int y2 = rand.nextInt(300) + 1;

        Line l = new Line(x1, y1, x2, y2);
        return l;
    }

    /**
     * draw 10 lines the middle point of each line and the intersection between
     * them.
     */

    void drawLine() {

        GUI window = new GUI("Random Lines Example", 400, 300);
        DrawSurface surface = window.getDrawSurface();
        // An array of lines
        Line[] linesArr = new Line[10];
        for (int i = 0; i < 10; ++i) {

            Line l1 = generateRandomLine();
            linesArr[i] = l1;
            surface.setColor(Color.BLACK);
            // draw each line
            surface.drawLine((int) l1.start().getX(), (int) l1.start().getY(), (int) l1.end().getX(),
                    (int) l1.end().getY());
            // draw the middle points in blue
            surface.setColor(Color.BLUE);
            surface.fillCircle((int) l1.middle().getX(), (int) l1.middle().getY(), 3);
        }

        drawIntersectionPoints(linesArr, surface);
        window.show(surface);
    }

    /**
     * draw intersection points of the lines.
     * @param linesArr - An array of lines
     * @param surface  - A surface to draw on
     */
    void drawIntersectionPoints(Line[] linesArr, DrawSurface surface) {

        // use 2 loops to pass all the lines
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (linesArr[i].isIntersecting(linesArr[j])) {
                    surface.setColor(Color.RED);
                    surface.fillCircle((int) linesArr[i].intersectionWith(linesArr[j]).getX(),
                            (int) linesArr[i].intersectionWith(linesArr[j]).getY(), 3);
                }

            }
        }
    }

    /**
     * create object of the class.
     * @param args - array of strings from the command line
     */
    public static void main(String[] args) {

        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawLine();
    }

}
