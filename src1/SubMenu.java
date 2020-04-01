import java.awt.Color;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @param <T> the task for the menu
 * @since 2019-06-10 This class use to show the sub menu as animation.
 */
public class SubMenu<T> implements Menu<T> {

    private List<Menu<T>> subMenu;
    private List<String> subMenuLevel;
    private List<String> subMenuKeys;
    private List<String> subMenuMessages;
    private boolean stop;
    private KeyboardSensor keyboardSensor;
    //private T status;
    private Menu<T> status;
    private String statusLevel;


    /**
     * constructor for the sub menu.
     * @param subMenuLevel - string that is the level set
     * @param subMenuKeys - the char to chose level set (the default is easy, medium, hard)
     * @param subMenuMessages - the level name
     * @param keyboardSensor - keyboard of the game
     */

    public SubMenu(List<String> subMenuLevel, List<String> subMenuKeys, List<String> subMenuMessages,
            KeyboardSensor keyboardSensor) {
        this.subMenuLevel = subMenuLevel;
        this.subMenuKeys = subMenuKeys;
        this.subMenuMessages = subMenuMessages;
        this.stop = false;
        this.keyboardSensor = keyboardSensor;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        Point p = new Point(0, 0);
        Sprite s1 = new Block(p, 800, 600, Color.orange);
        //s1.drawOn(d);
        ImageIcon icon = new ImageIcon("sea.jpg");
        Image image = icon.getImage();
        d.drawImage(0, 0, image);
        d.setColor(Color.magenta);
        d.drawText(250, 70, "Select Level Set", 50);

        for (int j = 0; j < this.subMenuMessages.size(); j++) {

            int xLocation = 50;
            int yLocation = 120 + 60 * j;
            String text = "(" + this.subMenuKeys.get(j) + ") " + this.subMenuMessages.get(j);
            d.setColor(new Color(0, 153, 0));
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

    @Override
    public T getStatus() {
        //return this.status.getStatus();
        return this.status.getStatus();
    }


    @Override
    public void addSelection(String key, String message, T returnVal) {
        // TODO Auto-generated method stub

    }


    @Override
    public void setStatus(T s) {
        // TODO Auto-generated method stub

    }


    @Override
    public void addSubMenu(String key, String message, Menu<T> sub) {
        // TODO Auto-generated method stub

    }


    @Override
    public void addSubMenu(String key, String message, String sub) {
        // TODO Auto-generated method stub

    }


    @Override
    public String getStatus1() {
        return this.statusLevel;
    }

}
