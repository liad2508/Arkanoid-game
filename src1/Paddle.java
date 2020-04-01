import java.awt.Color;

import biuoop.DrawSurface;
//import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class does some operation on paddle.
 */

public class Paddle implements Sprite, Collidable {
    // field for the paddle
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private int speed;

    /**
     * Constructor for class paddle.
     * @param upperLeft - the uuper left point of the paddle
     * @param width - the wide of the paddle
     * @param height - the height of the paddle
     *
     * @param keyboard1 - keyboard to know how to move the paddle
     * @param speed - the speed of the paddle (the distance he can move at each press on the keyboard
     */
    public Paddle(Point upperLeft, double width, double height, biuoop.KeyboardSensor keyboard1, int speed) {
        this.paddle = new Rectangle(upperLeft, width, height);
        this.keyboard = keyboard1;
        this.speed = speed;

    }

    /**
     * move the paddle to left if the user press the left key.
     */
    public void moveLeft() {
        this.paddle.moveToLeft(this.speed);
    }

    /**
     * move the paddle to right if the user press the right key.
     */
    public void moveRight() {
        this.paddle.moveToRight(this.speed);
    }

    /**
     * check if the "left" or "right" keys are pressed, and if so move it accordingly.
     */
    @Override
    public void timePassed() {
        // check also the range the paddle can move
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && this.paddle.getUpperLeft().getX() >= 35) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && this.paddle.getUpperLeft().getX() + this.paddle.getWidth() <= 765) {
            moveRight();
        }
    }

    /**
     * draw thw paddle to the screen.
     * @param d - the surface to draw on him
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * @return Rectangle - the paddle (the shape is rectangle).
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * change the behavior of the ball's bounce according to where it hits the paddle.
     * @param collisionPoint the point the hit occur
     * @param currentVelocity the current velocity of the ball
     * @param hitter - the hitter ball
     * @return Velocity of the ball after the hit with the paddle
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        // thw wide of each region of the paddle
        double regionPaddle = this.paddle.getWidth() / 5;
        // the left x point of the paddle
        double currentPlace = this.paddle.getUpperLeft().getX();
        double ballSpeed = Math.pow(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2), 0.5);
        // If the ball hits the region 1
        if (collisionPoint.getX() <= currentPlace + regionPaddle) {
            return Velocity.fromAngleAndSpeed(300, ballSpeed);
        // If the ball hits the region 2
        } else if (collisionPoint.getX() >= currentPlace + regionPaddle
                && collisionPoint.getX() <= currentPlace + 2 * regionPaddle) {
            return Velocity.fromAngleAndSpeed(330, ballSpeed);
        // If the ball hits the middle region (3)
        } else if (collisionPoint.getX() >= currentPlace + 2 * regionPaddle
                && collisionPoint.getX() <= currentPlace + 3 * regionPaddle) {
            currentVelocity.setDy(-currentVelocity.getDy());
            return currentVelocity;
        // If the ball hits the region 4
        } else if (collisionPoint.getX() >= currentPlace + 3 * regionPaddle
                && collisionPoint.getX() <= currentPlace + 4 * regionPaddle) {
            return Velocity.fromAngleAndSpeed(30, ballSpeed);
        // If the ball hits the region 5
        } else {

            return Velocity.fromAngleAndSpeed(60, ballSpeed);
        }
    }

    // Add this paddle to the game.
    /**
     * add the paddle to the game as a sprite and also as a collidable.
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * remove the paddle from the game as a sprite and also as a collidable.
     * @param game - the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}