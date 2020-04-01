import java.io.File;
import java.io.IOException;
/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class is in charge of exit the game (as task).
 *
 */
public class Exit implements Task<Void> {

    private HighScoresTable table;
    private File file;

    /**
     * constructor for this class.
     * @param table - the table with the high score
     * @param file - the file to save into
     */
    public Exit(HighScoresTable table, File file) {
        this.table = table;
        this.file = file;
    }

    @Override
    public Void run() {

        try {
            // save the table before finish the program
            this.table.save(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.exit(0);
        return null;
    }

}
