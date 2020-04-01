import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-05-20 This class is the first level of the game
 * this class implements LevelInformation, Sprite - contain all the information that need to draw this
 * level and also the number of balls, shape of blocks, background etc.
 */

public class DirectHit implements LevelInformation, Sprite {

    private Velocity v;

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
     List<Velocity> ballVelocity = new ArrayList<Velocity>();
     this.v = Velocity.fromAngleAndSpeed(1, 4);
     //Velocity v2 = Velocity.fromAngleAndSpeed(55, 7);
     ballVelocity.add(v);
     //ballVelocity.add(v2);
     return ballVelocity;

    }


    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }


    @Override
    public String levelName() {
        return "Direct Hit";

    }

    @Override
    public Sprite getBackground() {
        Point p = new Point(0, 0);
        Sprite s = new Block(p, 800, 600, Color.black);
        return s;


    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Point p = new Point(383, 203);
        Block b = new Block(p, 25, 25, Color.red);
        blocks.add(b);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.getBackground().drawOn(d);
        for (int r = 150; r > 100; r -= 20) {
            d.setColor(Color.BLUE);
            d.drawCircle(395, 205 , r);
        }
        d.drawLine(395, 200, 395, 0);
        d.drawLine(395, 210, 395, 370);
        d.drawLine(400, 215, 560, 215);
        d.drawLine(390, 215, 230, 215);


    }

    @Override
    public void timePassed() {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Ball> balls() {

        List<Ball> balls = new ArrayList<>();
        Ball ball = new Ball(390, 400, 5, Color.white);
        //Ball ball1 = new Ball(200, 400, 5, Color.red);
        //balls.add(ball1);
        balls.add(ball);
        return balls;
    }

    @Override
    public Point paddleLocation() {
        return new Point(375, 555);
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public int getNumHits() {

        return 1;
    }

    @Override
    public Color getColor() {
        return Color.black;
    }




}
