import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-06-10 This class use for keep tracking the high scores in the game.
 */
class HighScoresTable {

    private List<ScoreInfo> highScoresTable;
    private int maxSize;

   // Create an empty high-scores table with the specified size.
   // The size means that the table holds up to size top scores.
    /**
     * constructor for this class.
     * @param size - the size of the table we save
     */
   public HighScoresTable(int size) {

        this.highScoresTable = new ArrayList<ScoreInfo>();
        this.maxSize = size;
   }

   // Add a high-score.
   /**
    * add the score of the player.
    * @param score - the score of the player
    */
   public void add(ScoreInfo score) {
       // check if there is spacr available
       if (this.highScoresTable.size() < this.maxSize) {
       this.highScoresTable.add(score);
       // check if the score is in the top size of the highest scores
       } else {
           if (this.getRank(score.getScore()) < this.highScoresTable.size()) {
               this.highScoresTable.set(this.maxSize - 1, score);
           }

       }
       this.highScoresTable = this.getHighScores();

   }

   // Return table size.
   /**
    *
    * @return the size of the table
    */
   public int size() {
       return this.highScoresTable.size();
   }

   /**
    * Return the current high scores.
    * The list is sorted such that the highest
    * scores come first.
    * @return the current high scores
    */
   public List<ScoreInfo> getHighScores() {

       String tempName;
       int tempScore;

       for (int i = 0; i < this.size(); i++) {
           for (int j = i + 1; j < this.size(); j++) {

               if (this.highScoresTable.get(i).getScore() < this.highScoresTable.get(j).getScore()) {

                   tempName = this.highScoresTable.get(i).getName();
                   tempScore = this.highScoresTable.get(i).getScore();
                   this.highScoresTable.get(i).setName(this.highScoresTable.get(j).getName());
                   this.highScoresTable.get(i).setScore(this.highScoresTable.get(j).getScore());
                   this.highScoresTable.get(j).setName(tempName);
                   this.highScoresTable.get(j).setScore(tempScore);
               }
           }
       }
       return this.highScoresTable;
   }


   /**
    * return the rank of the current score: where will it
    * be on the list if added?
    * Rank 1 means the score will be highest on the list.
    * Rank `size` means the score will be lowest.
    * Rank > `size` means the score is too low and will not
    * be added to the list.
    * @param score - the score of the player
    * @return rank of the score
    */
   public int getRank(int score) {

       int rank = 0;

       for (int i = 0; i < this.size(); i++) {

           if (score > this.highScoresTable.get(i).getScore()) {

               return rank;
           }
           rank++;
       }

       return rank;

   }

   /**
    * clears the table.
    */
   public void clear() {
       this.highScoresTable.clear();
   }

   /**
    * Load table data from file.
    * Current table data is cleared.
    * @param filename a file
    * @throws IOException throw exception if there is io exception
    */
   public void load(File filename) throws IOException {

       BufferedReader buffer = null;
       String[] words;
       try {
           buffer = new BufferedReader(new FileReader("highscores.txt"));
           String line;

           while ((line = buffer.readLine()) != null) {
               //System.out.println(line);
               words = line.split("\\s+");
               int index = line.lastIndexOf(" ");
               String firstWords = line.substring(0, index);
               String lastWord = line.substring(index + 1);
               String name = firstWords;
               int score = Integer.parseInt(lastWord);
               ScoreInfo sI = new ScoreInfo(name, score);
               this.add(sI);

           }

       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           buffer.close();
       }

   }

   /**
    * Save table data to the specified file.
    * @param filename - a file
    * @throws IOException throw exception if there is io exception
    */
   public void save(File filename) throws IOException {
       BufferedWriter buffer = null;
       try {
           buffer = new BufferedWriter(new FileWriter("highscores.txt"));
           String line;
           int i = 0;

           while (i < this.size()) {

               buffer.write(this.getHighScores().get(i).getName() + "       "
               + Integer.toString(this.highScoresTable.get(i).getScore()) + "\r\n");
               i++;
           }

       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           buffer.close();
       }

   }

   /**
    *
    * Read a table from file and return it.
    * If the file does not exist, or there is a problem with
    * reading it, an empty table is returned.
    * @param filename - a file
    * @return the high score table
    */
   public static HighScoresTable loadFromFile(File filename) {
       BufferedReader buffer = null;
       String[] words;
       try {
           buffer = new BufferedReader(new FileReader("highscores.txt"));
           String line;

           while ((line = buffer.readLine()) != null) {
               //System.out.println(line);
               words = line.split("\\s+");
               String name = words[0];
               int score = Integer.parseInt(words[1]);
               ScoreInfo sI = new ScoreInfo(name, score);


           }

       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           try {
            buffer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       }
       return new HighScoresTable(0);
   }

   /**
    *
    * @return the max size of the table = how much scores the table keep
    */
   public int getMaxSize() {
       return this.maxSize;
   }
}