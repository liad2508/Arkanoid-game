import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import biuoop.DrawSurface;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class does some operation on block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // Fields
    private Rectangle block;
    private Color color;
    private int numberOfHits;
    private List<HitListener> hitListeners = new LinkedList<>();
    private Map<Integer, ColorsParser> colorsParserMap;

    /**
     * Constructor for class block. Create a new rectangle with location and
     * width/height.
     *
     * @param upperLeft point of the block
     * @param width - of the block
     * @param height - of the block
     * @param color - of the block
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     *
     * @param rectangle - create the block according to the rectangle
     * @param stroke - the color of the block
     * @param points - number of hit points
     * @param colorsParserMap - map of color parser
     */
    public Block(Rectangle rectangle, Color stroke, int points, Map<Integer, ColorsParser> colorsParserMap) {
        this.color = stroke;
        this.numberOfHits = points;
        this.block = rectangle;
        this.colorsParserMap = colorsParserMap;
    }

    @Override
    /**
     * @return the block
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * Return the color of the block.
     *
     * @return color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * get the number of hit points.
     *
     * @return number of hit points
     */
    public int getNumHits() {
        return this.numberOfHits;
    }

    /**
     * set the the block with a positive number of hit-points.
     *
     * @param h number of hits
     */
    public void setNumHits(int h) {
        this.numberOfHits = h;
    }

    @Override
    /**
     * change the velocity according to the location of the hit point.
     *
     * @param collisionPoint  - the collision point between the ball and the block
     * @param currentVelocity - of the ball
     * @return new velocity expected after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // if there is hit and the block number of hit not 0 decrease by -1
        if (this.numberOfHits != 0) {
            this.numberOfHits -= 1;
        }
        // notify about hit
        this.notifyHit(hitter);

        // if the collision point is the corner change the x and y
        if ((collisionPoint.getX() == this.block.getUpperLeft().getX()
                && collisionPoint.getY() == this.block.getUpperLeft().getY())
                || (collisionPoint.getX() == (this.block.getUpperLeft().getX() + this.block.getWidth())
                        && collisionPoint.getY() == this.block.getUpperLeft().getY())
                || (collisionPoint.getX() == this.block.getUpperLeft().getX()
                        && collisionPoint.getY() == (this.block.getUpperLeft().getY()) + this.block.getHeight())
                || (collisionPoint.getX() == (this.block.getUpperLeft().getX() + this.block.getWidth()))
                        && (collisionPoint.getY() == (this.block.getUpperLeft().getY()) + this.block.getHeight())) {

            currentVelocity.setDy(-currentVelocity.getDy());
            currentVelocity.setDx(-currentVelocity.getDx());
            return currentVelocity;
        }

        // if the collision point is on the horizonal lines of the block change the y
        if (collisionPoint.getX() > this.block.getUpperLeft().getX()
                && collisionPoint.getX() < this.block.getUpperLeft().getX() + this.block.getWidth()) {
            currentVelocity.setDy(-currentVelocity.getDy());
            return currentVelocity;
        }
        // if the collision point is on the vertical lines of the block change the x
        if (collisionPoint.getY() > this.block.getUpperLeft().getY()
                && collisionPoint.getY() < this.block.getUpperLeft().getY() + this.block.getHeight()) {
            currentVelocity.setDx(-currentVelocity.getDx());
            return currentVelocity;
        }

        return currentVelocity;

    }

    /**
     * draw the blocks.
     * @param d - the surface to draw on him
     */

    @Override
    public void drawOn(DrawSurface d) {

        //draw the block
        if (this.colorsParserMap.containsKey(this.numberOfHits)) {
            this.colorsParserMap.get(this.numberOfHits).drawOnBlocks(d, this.block);
        } else {
            this.colorsParserMap.get(1).drawOnBlocks(d, this.block);
        }
        // if there is a stroke
        if (this.color != null) {
            if (this.block.getUpperLeft().getX() != 0 && this.block.getUpperLeft().getX() <= 766) {
            d.setColor(this.color);
            // draw a frame to the block.
            d.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                    (int) this.block.getWidth(), (int) this.block.getHeight());
            }
        }
    }

    @Override
    /**
     *
     */
    public void timePassed() {
        // TODO Auto-generated method stub

    }

    /**
     * add the block to the game as a sprite (object in the game) and as a
     * collidable.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * remove the block from the game as a sprite (object in the game) and as a
     * collidable.
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * remove block from sprite.
     * @param game the game
     */
    public void removeFromSprite(GameLevel game) {
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notify all listeners about a hit event.
     * @param hitter - the ball that collide the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
           hl.hitEvent(this, hitter);
        }

     }

}
