import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class does some operation on sprites.
 */
public class SpriteCollection {
    // field - list of sprites
    private List<Sprite> sprites;

    /**
     * Constructor for class spriteCollection. create list with sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * add all the sprites to the list.
     * @param s - the sprite to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /*
    public void addSprite(LevelSpecificationReader s) {
        this.sprites.add(s);
    }
*/
    /**
     * remove all the sprites from the list.
     * @param s - the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites1 = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : sprites1) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d - surface to draw on him all the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}
