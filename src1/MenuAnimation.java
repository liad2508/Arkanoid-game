import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @param <T> the task for the menu
 * @since 2019-06-10 This class use to show the main menu as animation.
 */
public class MenuAnimation<T> implements Menu<T> {

    private T status;
    private String statusLevel;
    private String title;
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private boolean stop;
    private List<String> menuKeys;
    private List<String> menuMessages;
    private List<T> menuReturnVal;
    private List<Menu<T>> subMenu;
    private List<String> subMenuKeys;
    private List<String> subMenuMessages;
    private List<String> subMenuLevel;


    /**
     * constructor for this class.
     * @param s - title
     * @param runner - run the animation
     * @param keyboardSensor - the keyboard of the game
     */
    public MenuAnimation(String s, AnimationRunner runner, KeyboardSensor keyboardSensor) {
        this.title = s;
        this.runner = runner;
        this.keyboardSensor = keyboardSensor;
        this.status = null;
        this.stop = false;
        this.menuKeys = new ArrayList<>();
        this.menuMessages = new ArrayList<>();
        this.menuReturnVal = new ArrayList<>();
        this.subMenu = new ArrayList<>();
        this.subMenuKeys = new ArrayList<>();
        this.subMenuMessages = new ArrayList<>();
        this.subMenuLevel = new ArrayList<>();
    }


    /**
     *
     */
    public MenuAnimation() {

    }


    @Override
    public void doOneFrame(DrawSurface d) {
        Point p = new Point(0, 0);
        Sprite s = new Block(p, 800, 600, Color.lightGray);


        /*
        String backgroundPath = "background_images/sea.jpg";
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

        ImageIcon icon = new ImageIcon("sea.jpg");
        Image image = icon.getImage();
         d.drawImage(0, 0, image);
        //d.setColor(Color.lightGray);
        //d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(0, 102, 0));
        d.drawText(310, 70, title, 50);

        for (int i = 0; i < this.menuMessages.size(); i++) {

            int xLocation = 50;
            int yLocation = 120 + 60 * i;
            String text = "(" + this.menuKeys.get(i) + ") " + this.menuMessages.get(i);
            d.setColor(new Color(255, 255, 0));
            d.drawText(xLocation, yLocation, text, 35);

        }

        for (int i = 0; i < this.menuReturnVal.size(); i++) {

            if (this.keyboardSensor.isPressed(this.menuKeys.get(i))) {

                this.status = this.menuReturnVal.get(i);
                this.stop = true;

            }
        }
    }

    /**
     *
     * @return list of sub menu
     */
    public List<Menu<T>> getSubMenu() {
        return subMenu;
    }


    /**
     *
     * @param sub to be added
     */
    public void setSubMenu(List<Menu<T>> sub) {
        this.subMenu = sub;
    }


    /**
     * getter for sub menu.
     * @return the keys of the sub menu
     */
    public List<String> getSubMenuKeys() {
        return subMenuKeys;
    }


    /**
     *
     * @param subKeys - keep all the keys of sub menu
     */
    public void setSubMenuKeys(List<String> subKeys) {
        this.subMenuKeys = subKeys;
    }


    /**
     *
     * @return list of level (easy, medium etc)
     */
    public List<String> getSubMenuMessages() {
        return subMenuMessages;
    }


    /***
     * getter for messages in sub menu.
     * @param subMessages - messages in sub menu
     */
    public void setSubMenuMessages(List<String> subMessages) {
        this.subMenuMessages = subMessages;
    }


    @Override
    public boolean shouldStop() {
        return this.status != null;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.menuKeys.add(key);
        this.menuMessages.add(message);
        this.menuReturnVal.add(returnVal);
        //this.partialMenu.add((Menu<T>)null);
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(T s) {
        this.status = s;
    }


    @Override
    public void addSubMenu(String key, String message, Menu<T> sub) {
        this.subMenuKeys.add(key);
        this.subMenuMessages.add(message);
        this.subMenu.add(sub);

    }


    @Override
    public void addSubMenu(String key, String message, String sub) {
        this.subMenuKeys.add(key);
        this.subMenuMessages.add(message);
        this.subMenuLevel.add(sub);

    }


    @Override
    public String getStatus1() {
       return this.statusLevel;
    }

}
