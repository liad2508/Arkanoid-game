import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//import org.w3c.dom.css.Counter;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class does some operation on game, mainly show the
 *        animation to screen.
 *
 */
public class GameLevel implements Animation {

    // field for the game - collection of sprites and collidables and the gui
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter numberOfLives;
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;


/*
    public GameLevel(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }
*/
    /**
     * Constructor for game level.
     * @param levelInformation - information about how the level should look
     * @param keyboard - keyboard of the game
     * @param runner - the animation to run
     * @param score - score of the game
     * @param numberOfLives - number of lives at start of the game
     */
    public GameLevel(LevelInformation levelInformation, biuoop.KeyboardSensor keyboard, AnimationRunner runner,
            Counter score, Counter numberOfLives) {
        this.levelInformation = levelInformation;
        this.keyboard = keyboard;
        this.runner = runner;
        this.score = score;
        this.numberOfLives = numberOfLives;

    }

    /**
     * add all the collidables to their list.
     * @param c - collidable to be added to the list
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add all the sprites to their list.
     * @param s - sprite to be added to the list
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
/*
    public void addSprite(LevelSpecificationReader s) {
        this.sprites.addSprite(s);
    }
*/
    /**
     * remove collidable from the list.
     * @param c - collidable to be removed from the list
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove sprite from the list.
     * @param s - sprite to be removed from the list
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {

        // create the gui
        //this.gui = new GUI("Arkanoid", 800, 600);
        this.addSprite(this.levelInformation.getBackground());









        //this.levelInformation.addToGame(this);
        //this.addSprite(this.levelInformation.getBackground());
        //this.score = new Counter(0);
        ScoreTrackingListener liveScore = new ScoreTrackingListener(this.score);
        this.remainingBalls = new Counter(0);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        //this.remainingBalls = new Counter(0);
        //BallAdder ballAdder = new BallAdder(this, this.remainingBalls, this.environment);
        //PrintingHitListener printHit = new PrintingHitListener();
        this.remainingBlocks = new Counter(0);


        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);


        Map<Integer, ColorsParser> m = new TreeMap<>();
        m.put(0, new ColorsParser("color(gray)"));
        Map<Integer, ColorsParser> m1 = new TreeMap<>();
        m1.put(0, new ColorsParser("color(white)"));
        // creates the blocks for the border of the screen (in order the ball not go outside
        Block b = new Block(new Rectangle(new Point(0, 25), 800, 31), Color.darkGray, 0, m);
        b.addToGame(this);
        Block b2 = new Block(new Rectangle(new Point(0, 0), 31, 600), Color.darkGray, 0, m);
        b2.addToGame(this);
        Block b3 = new Block(new Rectangle(new Point(0, 570), 800, 0), Color.darkGray, 0, m);
        b3.addToGame(this);
        //b3.addHitListener(ballRemover);
        Block b4 = new Block(new Rectangle(new Point(767, 0), 33, 600), Color.darkGray, 0, m);
        //b4.setNumHits(0);
        b4.addToGame(this);
        Block scoreBlock = new Block(new Rectangle(new Point(0, 0), 800, 25), Color.white, 0, m1);
        scoreBlock.addToGame(this);


        Block deathBlock = b3;
        deathBlock.addHitListener(ballRemover);

/*

        // creates the blocks for the border of the screen (in order the ball not go outside
        Block block;
        Point pBelow = new Point(0, 570);
        Block below = new Block(pBelow, 800, 0, Color.gray);
        //below.setNumHits(1);
        Point pUp = new Point(0, 25);
        Block up = new Block(pUp, 800, 31, Color.gray);
        //up.setNumHits(1);
        Point left = new Point(0, 0);
        Block leftB = new Block(left, 31, 600, Color.gray);

        //leftB.setNumHits(1);
        Point right = new Point(768, 0);
        Block rightB = new Block(right, 32, 600, Color.gray);
        //rightB.setNumHits(1);
        Point scorePoint = new Point(0, 0);
        Block scoreBlock = new Block(scorePoint, 800, 25, Color.white);
        //rightB.setNumHits(1);

        below.addToGame(this);
        leftB.addToGame(this);
        rightB.addToGame(this);
        up.addToGame(this);
        scoreBlock.addToGame(this);


        Block deathBlock = below;
        deathBlock.addHitListener(ballRemover);
*/


        for (int i = 0; i < this.levelInformation.blocks().size(); i++) {

            Block blocks =  this.levelInformation.blocks().get(i);
            blocks.addHitListener(blockRemover);
            blocks.addHitListener(liveScore);
            //blocks.setNumHits(this.levelInformation.getNumHits());
            blocks.addToGame(this);
        }

        this.remainingBlocks.increase(this.levelInformation.numberOfBlocksToRemove());

        LivesIndicator livesIndicator = new LivesIndicator(this.numberOfLives,
                this.remainingBalls, this.levelInformation);
        livesIndicator.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {

        this.createBallsOnTopOfPaddle();
        // use our runner to run the current animation -- which is one turn of the game.


        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);
        this.paddle.removeFromGame(this);

        if (this.remainingBalls.getValue() > 0) {
            this.score.increase(100);
        }
    }

    /**
     * run the game.
     */

    public void run() {

        this.numberOfLives = new Counter(4);
        LivesIndicator livesIndicator = new LivesIndicator(this.numberOfLives,
                this.remainingBalls, this.levelInformation);
        livesIndicator.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);

        playOneTurn();
        while (this.numberOfLives.getValue() > 1 && this.remainingBlocks.getValue() > 0) {
            this.numberOfLives.decrease(1);

            //initialize();
            playOneTurn();
        }
        //System.out.println(score.getValue());
        gui.close();
        return;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        //this.levelInformation.addToGame(this);



        this.sprites.drawAllOn(d);
        //d.setColor(this.levelInformation.getColor());
        //d.drawLine(32, 570, 767, 570);
        this.sprites.notifyAllTimePassed();
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
         }
         if (this.remainingBlocks.getValue() == 0) {
            this.running = false;
         }
         if (this.keyboard.isPressed("p")) {
             this.runner.run(new PauseScreen(this.keyboard));
         }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * create the balls and the paddle of each level according to gamelevel.
     */
    private void createBallsOnTopOfPaddle() {

        Ball[] balls = new Ball[this.levelInformation.numberOfBalls()];
        List<Velocity> velocities = this.levelInformation.initialBallVelocities();

        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {

            balls[i] = new Ball(new Point(400, 520), 5, Color.white);
            balls[i].setVelocity(velocities.get(i));
            balls[i].setBorder(0, 0, 800, 600);
            balls[i].setGame(this.environment);
            balls[i].addToGame(this);
           /*
           Ball b =  this.levelInformation.balls().get(i);
           b.setVelocity(this.levelInformation.initialBallVelocities().get(i));
           b.setBorder(0, 0, 800, 600);
           b.addToGame(this);
           b.setGame(environment);
           */

        }
        this.remainingBalls.increase(this.levelInformation.numberOfBalls());
        /*
        this.paddle = new Paddle(this.levelInformation.paddleLocation(),
                this.levelInformation.paddleWidth(), 15, keyboard,
                this.levelInformation.paddleSpeed());
                */


        this.paddle = new Paddle(new Point(400 - this.levelInformation.paddleWidth() / 2, 540),
                this.levelInformation.paddleWidth(), 15, keyboard,
                this.levelInformation.paddleSpeed());
        paddle.addToGame(this);

    }

    /**
     *
     * @return number of blocks that remain
     */
    public int getRemainingBlocks() {
        return this.remainingBlocks.getValue();
    }



}
