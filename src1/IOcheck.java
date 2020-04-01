import java.io.File;
import java.io.IOException;

public class IOcheck {

    public static void main(String[] args) {

        HighScoresTable table = new HighScoresTable(2);
        ScoreInfo score1 = new ScoreInfo("liad", 200);
        ScoreInfo score2 = new ScoreInfo("ron", 90);
        ScoreInfo score3 = new ScoreInfo("gal", 1080);
        ScoreInfo score4 = new ScoreInfo("mika", 1000);
        table.add(score1);
        table.add(score2);
        table.add(score3);
        table.add(score4);
        File file = new File("highscores.txt");
        try {
            table.save(file);
            table.load(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }






    }

}
