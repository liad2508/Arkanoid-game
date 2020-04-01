import java.awt.Color;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-06-10 This class use to show the sub menu as animation.
 */
public class SubMenu1 implements Animation {

    private List<String> subMenuLevel;
    private List<String> subMenuKeys;
    private List<String> subMenuMessages;
    private boolean stop;
    private KeyboardSensor keyboardSensor;
    private String statusLevel = "reset";

    /**
     * constructor for the sub menu.
     * @param subMenuLevel - string that is the level set
     * @param subMenuKeys - the char to chose level set (the default is easy, medium, hard)
     * @param subMenuMessages - the level name
     * @param keyboardSensor - keyboard of the game
     */
    public SubMenu1(List<String> subMenuLevel, List<String> subMenuKeys, List<String> subMenuMessages,
            KeyboardSensor keyboardSensor) {

        this.subMenuLevel = subMenuLevel;
        this.subMenuKeys = subMenuKeys;
        this.subMenuMessages = subMenuMessages;
        this.stop = false;
        this.keyboardSensor = keyboardSensor;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        /*
        String backgroundPath = "background_images/submenu.jpg";
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


        ImageIcon icon = new ImageIcon("subMenu.jpg");
        Image image = icon.getImage();
        d.drawImage(0, 0, image);


        d.setColor(Color.magenta);
        d.drawText(250, 70, "Select Level Set", 50);

        for (int j = 0; j < this.subMenuMessages.size(); j++) {

            int xLocation = 50;
            int yLocation = 120 + 60 * j;
            String text = "(" + this.subMenuKeys.get(j) + ") " + this.subMenuMessages.get(j);
            d.setColor(Color.yellow);
            d.drawText(xLocation, yLocation, text, 35);
        }

        for (int i = 0; i < this.subMenuLevel.size(); i++) {

            if (this.keyboardSensor.isPressed(this.subMenuKeys.get(i))) {


                //
                this.statusLevel = this.subMenuLevel.get(i);
                //this.status = (T) this.subMenu.get(i);
                this.stop = true;
            }
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * add levels to sub menu.
     * @param key -  the char to chose level set (the default is easy, medium, hard)
     * @param message - level name
     * @param subMenu - string that is the level set
     */
    public void addSubMenu(String key, String message, String subMenu) {
        this.subMenuKeys.add(key);
        this.subMenuMessages.add(message);
        this.subMenuLevel.add(subMenu);

    }

    /**
     * getter for field status.
     * @return the status
     */
    public String getStatus() {
        return this.statusLevel;
    }
    /**
     * setter for stop.
     * @param b - boolean
     */
    public void setStop(boolean b) {
        this.stop = false;
    }
    /**
     * setter for field status.
     * @param a - string
     */
    public void setStatus(String a) {
        this.statusLevel = a;
    }

}
