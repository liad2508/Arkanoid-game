import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class run the game.
 */
public class Ass7Game {


    /**
     * the main that run the game.
     * @param args - can be input from the user
     */
    public static void main(String[] args) {

        // create the gui, keyboard number of life score and list of levels
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(60, gui, sleeper);
        File file = null;
        // table with size 5
        HighScoresTable table = new HighScoresTable(5);




        try {

            File f = new File("highscores.txt");
            if (f.exists()) {
            table.load(f);
            }
        } catch (IOException e) {
            file = new File("highscores.txt");
        }
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();

      // create the main menu for the game and the sub menu
      Menu<Task<Void>> menu1 = new MenuAnimation<Task<Void>>("Arkanoid", runner, keyboard);
      List<String> subMenuKeys = new ArrayList<>();
      List<String> subMenuMessages = new ArrayList<>();
      List<String> subMenuLevel = new ArrayList<>();
      SubMenu1 subMenu1 = new SubMenu1(subMenuLevel, subMenuKeys, subMenuMessages, keyboard);
      Animation highScoresAnimation = new HighScoresAnimation(keyboard, table);


      // read level set if there is argument to the main or not (so read default level set)

      LineNumberReader linesReader = null;
      String line;
      List<String> details = new ArrayList<>();
      String[] parts = new String[2];
      int i = 0;

      try {
          // if there is no argument read the default level set
          if (args.length == 0) {
              linesReader = new LineNumberReader(new FileReader("resources/level_sets.txt"));
          // else, take the path to the level set
          } else {
          linesReader = new LineNumberReader(new FileReader(args[0]));
          }

          // save all the files in list of strings
          while ((line = linesReader.readLine()) != null) {
              details.add(i, line);
              i++;
          }
          // create the sub menu according to the level set
          for (int j = 0; j < details.size(); j += 2) {
              String key, levelSet;
              parts = details.get(j).split(":");
              key = parts[0];
              levelSet = parts[1];
              subMenu1.addSubMenu(key, levelSet, "resources/" + details.get(j + 1));
          }

      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          try {
            linesReader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      }
      // end of reading level set

      // add the tasks to the main menu
      menu1.addSelection("s", "Start Game", new RunLevels(keyboard, runner, gui, file, table,
              levelSpecificationReader, subMenu1));
      menu1.addSelection("h", "High scores", new ShowHiScoresTask(runner, highScoresAnimation));
      menu1.addSelection("q", "Quit", new Exit(table, file));
      // option to return from the sub menu to the main menu
      subMenu1.addSubMenu("R", "return", "r");

      // loop that run the menus
      while (true) {
      // wait for user selection
      runner.run(menu1);
      // if the player choose start game he goes to the sub menu
      if (keyboard.isPressed("s")) {
          runner.run(subMenu1);
      }
      // if the player choose return, he return to the main menu
      if (!subMenu1.getStatus().equals("r")) {
      Task<Void> task = menu1.getStatus();
      task.run();
      }
      // initialize again all the animation (and delete the previous selections)
      menu1.setStatus(null);
      subMenu1.setStatus("reset");
      subMenu1.setStop(false);
      ((HighScoresAnimation) highScoresAnimation).getStoppable().setStop(false);
      }
    }
}
