/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-06-10 This class use to represent the high score in the table - name and score.
 */
public class ScoreInfo {

    private String name;
    private int score;

    /**
     * constructor for this class.
     * @param name - the name of the player
     * @param score - the score of the player
     */
   public ScoreInfo(String name, int score) {
       this.name = name;
       this.score = score;
   }

   /**
    * setter for the name field.
    * @param name1 - the name of the player
    */
   public void setName(String name1) {
    this.name = name1;
}

   /**
    * setter for the score field.
    * @param score1 - the score of the player
    */
   public void setScore(int score1) {
    this.score = score1;
}

   /**
    * getter for the name field.
    * @return the name of the player
    */
   public String getName() {
       return this.name;
   }
   /**
    * getter for the score field.
    * @return the score of the player
    */
   public int getScore() {
       return this.score;
   }
}