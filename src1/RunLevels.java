import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;
/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class is in charge of run the diffrent levels as task.
 *
 */
public class RunLevels implements Task<Void> {

    private String[] args;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private GUI gui;
    private File file;
    private HighScoresTable table;
    private LevelSpecificationReader levelSpecificationReader;
    private  Menu<Task<Void>> menu;
    private SubMenu1 submenu;


    /**
     * constructor for this class.
     * @param keyboard - the keybord of the game
     * @param runner - the runner of the game, can run animation
     * @param gui - gui of the game
     * @param file - the file of the high scores
     * @param table - high score table
     * @param levelSpecificationReader - the levels after reading from the file
     * @param subMenu - sub menu with the level sets
     */
    public RunLevels(KeyboardSensor keyboard, AnimationRunner runner, GUI gui,
            File file, HighScoresTable table,
            LevelSpecificationReader levelSpecificationReader,  SubMenu1 subMenu) {
        this.keyboard = keyboard;
        this.runner = runner;
        this.gui = gui;
        this.file = file;
        this.table = table;
        this.levelSpecificationReader = levelSpecificationReader;
        this.submenu = subMenu;
    }

    @Override
    public Void run() {

        // set the number of live to game and initialize the score to zero
        Counter numberOfLives = new Counter(5);
        Counter score = new Counter(0);
        GameFlow gameFlow = new GameFlow(keyboard, runner, score, numberOfLives, gui, file, table);
        List<LevelInformation> levels = new ArrayList<>();

        try {
            // get the status of the sub menu, the level set to read
            Reader reader = new FileReader(this.submenu.getStatus());
            // run the appropriate level according to the level set (easy, mediun etc)
            levels = this.levelSpecificationReader.fromReader(reader);
            gameFlow.runLevels(levels);
            return null;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // run the levels with the draws from ass6
        // create all the levels
        /*
        LevelInformation levelInformation1 = new DirectHit();
        levels.add(levelInformation1);
        LevelInformation levelInformation2 = new WideEasy();
        levels.add(levelInformation2);
        LevelInformation levelInformation3 = new Green3();
        levels.add(levelInformation3);
        LevelInformation levelInformation4 = new FinalFour();
        levels.add(levelInformation4);

        // if there's no input start the game with four levels that run one after one
        List<LevelInformation> levelsArguments = new ArrayList<>();
        if (args.length == 0) {
            gameFlow.runLevels(levels);
        } else {

            for (int i = 0; i < args.length; i++) {

                // if (args[i].matches("-?\\d+(\\.\\d+)?")) {
                // check if the string is number and in range of the levels (1-4)
                if (args[i].matches("-?\\d+(\\d+)?")) {
                if (Integer.parseInt(args[i]) >= 1 && Integer.parseInt(args[i]) <= 4) {
                    levelsArguments.add(levels.get(Integer.parseInt(args[i]) - 1));
                }
                }
            }
            if (levelsArguments.isEmpty()) {
                gameFlow.runLevels(levels);

            } else {

            gameFlow.runLevels(levelsArguments);
            }
        }
*/

        return null;

    }

}
