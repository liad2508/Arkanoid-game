import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;


/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-05-20 This class is the fourth level of the game
 * this class implements LevelInformation, Sprite - contain all the information that need to draw this
 * level and also the number of balls, shape of blocks, background etc.
 */

public class FinalFour implements LevelInformation, Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        this.getBackground().drawOn(d);

        for (int i = 0; i < 12; i++) {
            d.setColor(Color.white);
            d.drawLine(150 + 8 * i, 400, 130 + 8 * i, 600);
            d.setColor(new Color(255, 254, 0));
            d.drawLine(560 + 8 * i, 400, 520 + 8 * i, 600);

        }

        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                d.setColor(Color.white);
                d.fillCircle(170, 380, 25);
                d.fillCircle(160, 410, 15);

            }
            if (i == 1) {
                d.setColor(new Color(196, 196, 196));
                d.fillCircle(195, 415, 20);
                d.fillCircle(210, 400, 30);
            }
            if (i == 2) {
                d.setColor(new Color(146, 146, 146));
                d.fillCircle(150, 400, 20);
                d.fillCircle(160, 425, 30);

            }

        }
        for (int r = 50; r > 25; r -= 10) {
            if (r == 50) {
                d.setColor(new Color(254, 255, 199));
                d.fillCircle(600, 400, r);
            }
            if (r == 40) {
                d.setColor(new Color(251, 216, 117));
                d.fillCircle(600, 400, r);
            }
            if (r == 30) {
                d.setColor(Color.orange);
                d.fillCircle(600, 400, r);
            }
        }


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
        Velocity v2 =  Velocity.fromAngleAndSpeed(30, 7);
        Velocity v3 =  Velocity.fromAngleAndSpeed(65, 7);
        ballVelocity.add(v1);
        ballVelocity.add(v2);
        ballVelocity.add(v3);
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
      return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Point p = new Point(0, 0);
        Sprite s = new Block(p, 800, 600, new Color(87, 178, 223));
        return s;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // set the numbers of blocks in the first line and the number of lines for the blocks
        int numberBlocksFirstLine = 10;
        int numberOfLines = 7;
        Color colorBlock = null;


        for (int i = 0; i < numberOfLines; i++) {
            for (int j = 0; j < numberBlocksFirstLine; j++) {

                if (i == 0) {
                    colorBlock = new Color(255 , 0, 0);
                }
                if (i == 1) {
                    colorBlock = new Color(255, 250, 0);
                }
                if (i == 2) {
                    colorBlock = new Color(106, 255, 0);
                }
                if (i == 3) {
                    colorBlock = new Color(70, 6, 132);
                }
                if (i == 4) {
                    colorBlock = new Color(168, 0, 255);
                }
                if (i == 5) {
                    colorBlock = new Color(255, 123, 90);
                }
                if (i == 6) {
                    colorBlock = new Color(255, 182, 90);
                }


                // each iteration set the location of the block
                Point p = new Point(30 + 74 * j, 100 + 30 * i);
                Block block = new Block(p, 74, 30, colorBlock);
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
        Ball b2 = new Ball(400, 330, 5, Color.white);
        Ball b3 = new Ball(500, 400, 5, Color.white);
        balls.add(b1);
        balls.add(b2);
        balls.add(b3);
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
        return new Color(87, 178, 223);
    }

}
