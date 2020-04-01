import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;


/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-05-20 This class is the second level of the game
 * this class implements LevelInformation, Sprite - contain all the information that need to draw this
 * level and also the number of balls, shape of blocks, background etc.
 */

public class WideEasy implements LevelInformation, Sprite {

    private Velocity v;

    @Override
    public void drawOn(DrawSurface d) {
        this.getBackground().drawOn(d);

        for (int i = 0; i < 85; i++) {
            d.setColor(Color.yellow);
            d.drawLine(150, 150, 8 * i, 250);
        }
        for (int r = 70; r > 45; r -= 10) {
            if (r == 70) {
                d.setColor(new Color(254, 255, 199));
                d.fillCircle(150, 150, r);
            }
            if (r == 60) {
                d.setColor(new Color(251, 216, 117));
                d.fillCircle(150, 150, r);
            }
            if (r == 50) {
                d.setColor(Color.orange);
                d.fillCircle(150, 150, r);
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
        for (int i = 0; i < 10; i++) {

            if (i <= 4) {
                this.v = Velocity.fromAngleAndSpeed(-30, 7);
                ballVelocity.add(v);

            } else {
                this.v = Velocity.fromAngleAndSpeed(30, 7);
                ballVelocity.add(v);
            }

        }

        return ballVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public Point paddleLocation() {
      return new Point(200, 555);
    }

    @Override
    public String levelName() {
       return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Point p = new Point(0, 0);
        Sprite s = new Block(p, 800, 600, Color.white);
        return s;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int numOfBlocks = 15;
        Color colorBlock = null;
        for (int i = 0; i < numOfBlocks; i++) {
            if (i == 0 || i == 1) {
                colorBlock = Color.RED;
            }
            if (i == 2 || i == 3) {
                colorBlock = Color.orange;
            }
            if (i == 4 || i == 5) {
                colorBlock = Color.yellow;
            }
            if (i == 6 || i == 7 || i == 8) {
                colorBlock = Color.green;
            }
            if (i == 9 || i == 10) {
            colorBlock = Color.blue;
            }
            if (i == 11 || i == 12) {
                colorBlock = Color.pink;
            }
            if (i == 13 || i == 14) {
                colorBlock = Color.cyan;
            }

        Point p = new Point(32 + 49 * i, 250);
        Block b = new Block(p, 49, 20, colorBlock);

        blocks.add(b);
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
      int x = 0, y = 0;

        for (int i = 0; i < this.numberOfBalls(); i++) {

            if (i <= 4) {
              x = 250 + 25 * i;
              y = 400 - 25 * i;
            }
            if (i == 5) {
              x = 450;
              y = 300;
            }
            if (i > 5) {
              x = 450 + 25 * (i - 5);
              y = 300 + 25 * (i - 5);
            }

            Ball ball = new Ball(x , y, 5, Color.MAGENTA);
            balls.add(ball);
        }
        return balls;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public int getNumHits() {

        return 2;
    }

    @Override
    public Color getColor() {
        return Color.white;
    }

}
