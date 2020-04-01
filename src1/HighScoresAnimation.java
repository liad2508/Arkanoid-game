import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-06-10 This class use to show the high score table as animation.
 */

public class HighScoresAnimation implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private KeyPressStoppableAnimation keyPressStoppableAnimation;
    private HighScoresTable scoresTable;

    /**
     * constructor for this class.
     * @param keyboard - the keyboard of the game
     * @param scoresTable - all the date (table), containing the names of the players and thier scores
     */
    public HighScoresAnimation(KeyboardSensor keyboard, HighScoresTable scoresTable) {
        this.keyboard = keyboard;
        this.stop = false;
        this.scoresTable = scoresTable;
        this.keyPressStoppableAnimation = new KeyPressStoppableAnimation(this.keyboard,
                KeyboardSensor.SPACE_KEY, this, this.stop);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Point p = new Point(0, 0);
        /*
        String backgroundPath = "background_images/champion.jpg";
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(backgroundPath);
        BufferedImage img;
        try {
            img = ImageIO.read(is);
            d.drawImage(0, 0, img);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */

        ImageIcon icon = new ImageIcon("champion.jpg");
        Image image = icon.getImage();
        d.drawImage(0, 0, image);

        d.setColor(new Color(255, 128, 0));
        d.drawText(30, 70, "High Scores", 50);
        d.setColor(new Color(255, 153, 51));
        for (int i = 0; i < this.scoresTable.size(); i++) {
            /*
            d.drawText(50, 30 + 30 * i, this.scoresTable.getHighScores().get(i).getName()
                    + "        " +  Integer.toString(this.scoresTable.getHighScores().get(i).getScore())
                    + "\r\n", 25);
                    */
            d.drawText(30, 130 + 40 * i, this.scoresTable.getHighScores().get(i).getName(), 25);
            d.drawText(200, 130 + 40 * i, Integer.toString(this.scoresTable.getHighScores().get(i).getScore())
                    + "\r\n", 25);
        }

        this.keyPressStoppableAnimation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {


       return this.keyPressStoppableAnimation.shouldStop();
    }

    /**
     * setter fot the boolean field stop.
     * @param b - boolean
     */
    public void setStop(boolean b) {
        this.stop = false;
    }

    /**
     *
     * @return keyPressStoppableAnimation -
     * display and stop displaying animation according to the key press of the player.
     *
     */
    public KeyPressStoppableAnimation getStoppable() {

        return this.keyPressStoppableAnimation;
    }

}
