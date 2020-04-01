import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;


/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-05-20 This class is the third level of the game
 * this class implements LevelInformation, Sprite - contain all the information that need to draw this
 * level and also the number of balls, shape of blocks, background etc.
 */


public class Green3 implements LevelInformation, Sprite  {

    @Override
    public void drawOn(DrawSurface d) {
        this.getBackground().drawOn(d);
        d.setColor(Color.black);
        d.fillRectangle(100, 370, 120, 240);
        d.setColor(Color.white);
        d.fillRectangle(110, 380, 100, 180);
        for (int i = 0; i <= 4; i++) {
            d.setColor(Color.black);
            d.fillRectangle(100 + 22 * i, 380, 10, 180);
        }
        for (int j = 1; j <= 4; j++) {
            d.setColor(Color.black);
            d.fillRectangle(100, 374 + 37 * j, 120, 10);
        }
        d.setColor(new Color(75, 75, 75));
        d.fillRectangle(145, 320, 30, 50);
        d.setColor(new Color(49, 80, 64));
        d.fillRectangle(152, 190, 15, 130);

        for (int r = 20; r > 3; r -= 8) {
            if (r == 20) {
                d.setColor(Color.yellow);
            }
            if (r == 12) {
                d.setColor(Color.orange);
            }
            if (r == 4) {
                d.setColor(Color.white);
            }
            d.fillCircle(158, 170 , r);
        }
        d.setColor(Color.gray);
        d.fillRectangle(138, 570, 40, 70);
        d.setColor(Color.red);
        d.fillCircle(158, 585, 5);

    }

    @Override
    public void timePassed() {
        // TODO Auto-generated method stub

    }

    @Override
    public int numberOfBalls() {
      return this.initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocity = new ArrayList<Velocity>();
        Velocity v1 =  Velocity.fromAngleAndSpeed(-65, 7);
        Velocity v2 =  Velocity.fromAngleAndSpeed(65, 7);
        ballVelocity.add(v1);
        ballVelocity.add(v2);
        return ballVelocity;
    }

    @Override
    public int paddleSpeed() {

        return 8;
    }

    @Override
    public int paddleWidth() {
        // TODO Auto-generated method stub
        return 100;
    }

    @Override
    public Point paddleLocation() {
        return new Point(375, 555);
    }

    @Override
    public String levelName() {

        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Point p = new Point(0, 0);
        Sprite s = new Block(p, 800, 600, Color.green);
        return s;
    }

    @Override
    public List<Block> blocks() {


        List<Block> blocks = new ArrayList<>();
        // set the numbers of blocks in the first line and the number of lines for the blocks
        int numberBlocksFirstLine = 12;
        int numberOfLines = 6;
        Color colorBlock = null;


        // two loops that create the blocks in a nice pattern and make each row  of blocks
        // a different color
        for (int i = 0; i < numberOfLines; i++) {
            for (int j = i; j < numberBlocksFirstLine; j++) {

                if (i == 0) {
                    colorBlock = new Color(56, 65, 99);
                }
                if (i == 1) {
                    colorBlock = Color.blue;
                }
                if (i == 2) {
                    colorBlock = Color.CYAN;
                }
                if (i == 3) {
                    colorBlock = new Color(83, 119, 137);
                }
                if (i == 4) {
                    colorBlock = new Color(36, 66, 31);
                }
                if (i == 5) {
                    colorBlock = new Color(88, 57, 18);
                }

                // each iteration set the location of the block
                Point p = new Point(168 + 50 * j, 100 + 20 * i);
                Block block = new Block(p, 50, 20, colorBlock);
                blocks.add(block);
            }

        }
        return blocks;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        Ball b1 = new Ball(300, 400, 5, Color.white);
        Ball b2 = new Ball(500, 400, 5, Color.white);
        balls.add(b1);
        balls.add(b2);
        return balls;
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
        return Color.green;
    }

}
