

import java.awt.Color;
import java.awt.Image;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-06-10 This class in order to deal with draw blocks with image an
 */
public class ColorsParser {
    private Image image = null;
    private Color color = null;

    /**
     * create new color from string.
     * if it a image or a color.
     *
     * @param s - the color.
     */
    public ColorsParser(String s) {
        //image
        if (s.contains("image")) {
            String nameImage = s.substring("image".length() + 1, s.length() - 1);
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(nameImage);
                this.image = ImageIO.read(is);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            // RGB
            if (s.contains("RGB")) {
                s = s.substring("color(RGB(".length(), s.length() - 2);
                String[] xyz = s.split(",");
                int[] numbers = new int[xyz.length];
                for (int i = 0; i < xyz.length; i++) {
                    numbers[i] = Integer.parseInt(xyz[i]);
                }
                this.color = new Color(numbers[0], numbers[1], numbers[2]);
            } else {
                //color.
                Map<String, Color> colorMap = new TreeMap<String, Color>();
                colorMap.put("black", Color.BLACK);
                colorMap.put("blue", Color.BLUE);
                colorMap.put("cyan", Color.cyan);
                colorMap.put("gray", Color.gray);
                colorMap.put("lightGray", Color.lightGray);
                colorMap.put("green", Color.green);
                colorMap.put("orange", Color.orange);
                colorMap.put("pink", Color.pink);
                colorMap.put("red", Color.red);
                colorMap.put("white", Color.WHITE);
                colorMap.put("yellow", Color.yellow);

                s = s.substring("color(".length(), s.length() - 1);
                Color c = colorMap.get(s);
                this.color = c;
            }
        }
    }

    /**
     * getColor.
     *
     * @return - the color .
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * drawOnBlocks.
     *
     * @param d     DrawSurface.
     * @param block - Rectangle.
     */
    public void drawOnBlocks(DrawSurface d, Rectangle block) {
        // draw image
        if (image != null) {
            try {
                d.drawImage((int) block.getUpperLeft().getX(), (int) block.getUpperLeft().getY(),
                        this.image);

            } catch (Exception e) {
                return;
            }
            //draw color.
        } else {

            d.setColor(this.color);
            d.fillRectangle((int) block.getUpperLeft().getX(), (int) block.getUpperLeft().getY(),
                    (int) (block.getWidth()),
                    (int) (block.getHeight()));

        }
    }

}
