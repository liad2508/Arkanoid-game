import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07
 * This class is in charge of displaying the current score.
 *
 */

public class ScoreIndicator implements Sprite {

    private Counter score;
    /**
    * constructor for ScoreIndicator class.
    * @param score - get the first score of the game(0)
    */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * add ScoreIndicator to the game (as a sprite).
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {

        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 25);
        Point p3 = new Point(800, 25);
        Point p4 = new Point(800, 0);

        // first draw the frame of each block in black
        d.setColor(Color.black);
        d.drawRectangle(0, 0, 800, 600);

        d.setColor(Color.black);
        // the center of each block
        int x = 300;
        int y = 20;
        d.drawText(x, y, "Score: " + Integer.toString(this.score.getValue()), 20);


    }

    @Override
    public void timePassed() {
        // TODO Auto-generated method stub

    }

}
