
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import biuoop.DrawSurface;
/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-06-10 This class use to read levels form file and return them as level information.
 */

public class LevelSpecificationReader  {
    /**
     * readLine.
     *
     * @param reader1 - the reader.
     * @return - text of one level.
     * @throws Exception - If there is nothing to read.
     */
    public ArrayList<String> readLine(BufferedReader reader1) throws Exception {
        ArrayList<String> levelStringInfo = new ArrayList<>();
        String e = " ";
        try {
            //from start level
            while (!e.equals("START_LEVEL")) {
                e = reader1.readLine();
            }
            // until end level.
            while (!e.equals("END_LEVEL")) {
                e = reader1.readLine();
                levelStringInfo.add(e);
            }
            return levelStringInfo;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * findIndex.
     *
     * @param levelStringInfo - all the text of one level.
     * @param toFind          - The specific information to be found.
     * @return - the index of these info.
     * @throws Exception - if don't found.
     */
    public int findIndex(ArrayList<String> levelStringInfo, String toFind) throws Exception {
        for (int i = 0; i < levelStringInfo.size(); i++) {
            if (levelStringInfo.get(i).contains(toFind)) {
                return i;
            }
        }
        throw new Exception("Missing data");
    }

    /**
     * stringFind.
     *
     * @param levelStringInfo - all the text.
     * @param toFind          - The specific information to be found.
     * @return - the info.
     * @throws Exception - if missing data.
     */
    public String stringFind(ArrayList<String> levelStringInfo, String toFind) throws Exception {
        try {
            int i = this.findIndex(levelStringInfo, toFind);
            return levelStringInfo.get(i).substring(toFind.length() + 1);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * numberFind.
     *
     * @param levelStringInfo - all the text.
     * @param toFind          - The specific information to be found.
     * @return - the number look for.
     * @throws Exception - if missing data.
     */
    public int numberFind(ArrayList<String> levelStringInfo, String toFind) throws Exception {
        try {
            int i = this.findIndex(levelStringInfo, toFind);
            String numString = levelStringInfo.get(i).substring(toFind.length() + 1);
            int numFind = Integer.parseInt(numString);
            return numFind;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * createVelocity.
     *
     * @param levelStringInfo - all the text.
     * @param toFind          - the velocity to find.
     * @return - list of velocity.
     * @throws Exception - if missing data.
     */
    public List<Velocity> createVelocity(ArrayList<String> levelStringInfo, String toFind) throws Exception {
        try {
            List<Velocity> velocityList = new LinkedList<>();
            int index = this.findIndex(levelStringInfo, toFind);
            String v = levelStringInfo.get(index).substring(toFind.length() + 1);
            String[] strings = v.split(" ");
            for (int i = 0; i < strings.length; i++) {
                String[] num = strings[i].split(",");
                velocityList.add(Velocity.fromAngleAndSpeed(Double.parseDouble(num[0]),
                        Double.parseDouble(num[1])));
            }
            return velocityList;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * findBackground.
     *
     * @param levelStringInfo - all the text.
     * @param toFind          - the Background.
     * @return sprite of the background.
     * @throws Exception - if missing data.
     */
    public Sprite findBackground(ArrayList<String> levelStringInfo, String toFind) throws Exception {
        try {
            String nameBack = stringFind(levelStringInfo, toFind);
            ColorsParser colorsParser = new ColorsParser(nameBack);
            Sprite back = new Sprite() {
                @Override
                public void drawOn(DrawSurface d) {
                    colorsParser.drawOnBlocks(d, new Rectangle(new Point(0, 0), 800, 600));
                }

                public void timePassed(double dt) {
                    return;
                }

                @Override
                public void timePassed() {
                    // TODO Auto-generated method stub

                }
            };
            return back;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    /**
     * createLevel.
     *
     * @param allLevelInfo - all the text level.
     * @return - new LevelInformation.
     */
    public LevelInformation createLevel(ArrayList<String> allLevelInfo) {
        try {
            // Looking for all the information to create new LevelInformation.
            String levelName = stringFind(allLevelInfo, "level_name");
            List<Velocity> velocities = createVelocity(allLevelInfo, "ball_velocities");
            Sprite background = findBackground(allLevelInfo, "background");
            int paddleSpeed = numberFind(allLevelInfo, "paddle_speed");
            int paddleWidth = numberFind(allLevelInfo, "paddle_width");
            String blockDefinitions = stringFind(allLevelInfo, "block_definitions");
            int blocksStartX = numberFind(allLevelInfo, "blocks_start_x");
            int blocksStartY = numberFind(allLevelInfo, "blocks_start_y");
            int rowHeight = numberFind(allLevelInfo, "row_height");
            int numBlocks = numberFind(allLevelInfo, "num_blocks");

            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(blockDefinitions);
            Reader read = new InputStreamReader(is);
            BlocksFromSymbolsFactory factory =
                    BlocksDefinitionReader.fromReader(read);
            LinkedList<Block> blocks = new LinkedList<>();

            int startBlock = findIndex(allLevelInfo, "START_BLOCKS");
            int endBlock = findIndex(allLevelInfo, "END_BLOCKS");

            int y = blocksStartY;
            for (int i = 1 + startBlock; i < endBlock; i++) {
                int x = blocksStartX;
                for (int j = 0; j < allLevelInfo.get(i).length(); j++) {
                    String c = allLevelInfo.get(i).substring(j, j + 1);
                    if (factory.isSpaceSymbol(c)) {
                        x += factory.getSpaceWidth(c);
                    } else if (factory.isBlockSymbol(c)) {
                        Block b = factory.getBlock(c, x, y);
                        blocks.add(b);
                        x += b.getCollisionRectangle().getWidth();
                    }
                }
                y += rowHeight;
            }
            // create new level.
            LevelInformation level = new LevelInformation() {
                @Override
                public int numberOfBalls() {
                    return velocities.size();
                }

                @Override
                public List<Velocity> initialBallVelocities() {
                    return velocities;
                }

                @Override
                public int paddleSpeed() {
                    return paddleSpeed;
                }

                @Override
                public int paddleWidth() {
                    return paddleWidth;
                }

                @Override
                public String levelName() {
                    return levelName;
                }

                @Override
                public Sprite getBackground() {
                    return background;
                }

                @Override
                public List<Block> blocks() {
                    return blocks;
                }

                @Override
                public int numberOfBlocksToRemove() {
                    return numBlocks;
                }

                @Override
                public Point paddleLocation() {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public List<Ball> balls() {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public void addToGame(GameLevel g) {



                }

                @Override
                public int getNumHits() {
                    // TODO Auto-generated method stub
                    return 0;
                }

                @Override
                public Color getColor() {
                    // TODO Auto-generated method stub
                    return null;
                }
            };
            return level;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * fromReader - get a file name and returns a list of LevelInformation objects.
     *
     * @param reader - the file.
     * @return - list of LevelInformation objects.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<LevelInformation> levelInformationList = new LinkedList<>();
        try {
            while (true) {
                ArrayList<String> allInfoLevel = readLine(bufferedReader);
                levelInformationList.add(createLevel(allInfoLevel));
            }
        } catch (Exception e) {
            return levelInformationList;
        } finally {
            return levelInformationList;
        }
    }

    /*
    @Override
    public void drawOn(DrawSurface d) {
        // TODO Auto-generated method stub

    }

    @Override
    public void timePassed() {
        // TODO Auto-generated method stub

    }
*/
    /*
    @Override
    public int numberOfBalls() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int paddleSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int paddleWidth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Point paddleLocation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String levelName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Sprite getBackground() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Block> blocks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Ball> balls() {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public int getNumHits() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Color getColor() {
        // TODO Auto-generated method stub
        return null;
    }
*/
}
