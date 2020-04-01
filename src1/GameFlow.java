import java.io.File;
import java.io.IOException;
import java.util.List;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class is in charge of moving from one level to the next.
 *
 */

public class GameFlow {

    private biuoop.KeyboardSensor keyboard;
    private AnimationRunner runner;
    private Counter score;
    private Counter numberOfLives;
    private GUI gui;
    private boolean stop = false;
    private HighScoresTable table;
    private File file;



/**
 * Constructor for the game flow.
 * @param ks - the keyboard
 * @param ar - the animation
 * @param score - the score of the game
 * @param numberOfLives - number of lives
 * @param file - the file to save the high score
 * @param table - high score table
 * @param gui - the gui of the game
 */

public GameFlow(KeyboardSensor ks, AnimationRunner ar, Counter score, Counter numberOfLives, GUI gui,
        File file , HighScoresTable table) {
    this.keyboard = ks;
    this.runner = ar;
    this.score = score;
    this.numberOfLives = numberOfLives;
    this.gui = gui;
    this.file = file;
    this.table = table;
 }

/**
 * loop that moving from each level to the next.
 * @param levels - List that contain all the levels of the specific game
 */
   public void runLevels(List<LevelInformation> levels) {

       // loop that run all the levels
      for (LevelInformation levelInfo : levels) {

          // creating the level we play now
         GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.score, this.numberOfLives);

         level.initialize();

         // loop that run the game while there is lives and blocks in the level
         while (level.getRemainingBlocks() > 0 && this.numberOfLives.getValue() > 0) {
            level.playOneTurn();
            if (level.getRemainingBlocks() > 0) {
                this.numberOfLives.decrease(1);
            }
         }

         // if number of lives is 0, exit from the loops that run the levels
         if (this.numberOfLives.getValue() == 0) {
            break;
         }
      }

      // run the animation of end screen (if the user win or lose)
      this.runner.run(new GameOver(this.keyboard, this.numberOfLives, this.score));

      if (this.table.getHighScores().size() < this.table.getMaxSize()
              || this.table.getHighScores().size() >= this.table.getMaxSize()
                      && this.table.getRank(this.score.getValue()) < this.table.getHighScores().size()) {

          DialogManager dialog = gui.getDialogManager();
          String name = dialog.showQuestionDialog("Name", "What is your name?", "");
          this.table.add(new ScoreInfo(name, this.score.getValue()));
          try {
            table.save(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      }


      this.runner.run(new HighScoresAnimation(this.keyboard, this.table));



/*
      // run the animation of end screen (if the user win or lose)
      this.runner.run(new GameOver(this.keyboard, this.numberOfLives, this.score));
      */
      // close the gui and finish the game

      //gui.close();

   }
}