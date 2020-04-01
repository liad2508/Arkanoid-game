

import java.awt.Color;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-06-10 This class in order to reading a block-definitions file
 * and returning a BlocksFromSymbolsFactory object.
 */
public class BlocksDefinitionReader {
    /**
     * allText.
     *
     * @param reader - reade the text.
     * @return - all the text in the file.
     */
    public static ArrayList<String> allText(BufferedReader reader) {
        ArrayList<String> list = new ArrayList<>();
        String s = " ";
        while (s != null) {
            try {
                s = reader.readLine();
                list.add(s);
            } catch (Exception e) {
                return list;
            }
        }
        return list;
    }

    /**
     * spacerWidths.
     *
     * @param allTextFile - the text in the file.
     * @return - a map of the space and widths.
     */
    public static Map<String, Integer> spacerWidths(ArrayList<String> allTextFile) {
        Map<String, Integer> spaceMap = new TreeMap<>();
        while (true) {
            try {
                LevelSpecificationReader l = new LevelSpecificationReader();
                int i = l.findIndex(allTextFile, "sdef symbol");
                String s = l.stringFind(allTextFile, "sdef symbol");
                String[] strings = s.split(" ");
                int width = Integer.parseInt(strings[1].substring("width:".length(), strings[1].length()));
                spaceMap.put(strings[0], width);

                allTextFile.remove(i);
                allTextFile.add(i, " ");

            } catch (Exception e) {
                return spaceMap;
            }
        }
    }

    /**
     * findNum.
     *
     * @param info        - info of one block.
     * @param findIt      - data to look for.
     * @param defaultInfo - the default info of the blocks.
     * @return - the number find.
     */
    public static int findNum(String[] info, String findIt, String[] defaultInfo) {
        for (int i = 0; i < info.length; i++) {
            if (info[i].contains(findIt)) {
                return Integer.parseInt(info[i].substring(findIt.length()));
            }
        }
        for (int i = 0; i < defaultInfo.length; i++) {
            if (defaultInfo[i].contains(findIt)) {
                return Integer.parseInt(defaultInfo[i].substring(findIt.length()));
            }
        }
        return 0;
    }

    /**
     * fillColors.
     *
     * @param info        - date.
     * @param defaultInfo - default info for the block.
     * @return - a map of the colors block.
     */
    public static Map<Integer, ColorsParser> fillColors(String[] info, String[] defaultInfo) {
        Map<Integer, ColorsParser> colorsMap = new TreeMap<>();
        for (int i = 0; i < info.length; i++) {
            if (info[i].contains("fill")) {
                if (info[i].contains("-")) {
                    String[] strings = info[i].split(":");
                    int index = Integer.parseInt(strings[0].substring("fill-".length()));
                    ColorsParser colorsParser = new ColorsParser(strings[1]);
                    colorsMap.put(index, colorsParser);
                } else {
                    String[] strings = info[i].split(":");
                    ColorsParser colorsParser = new ColorsParser(strings[1]);
                    colorsMap.put(1, colorsParser);
                }
            }
        }
        if (colorsMap.isEmpty()) {
            for (int i = 0; i < defaultInfo.length; i++) {
                if (defaultInfo[i].contains("fill")) {
                    if (defaultInfo[i].contains("-")) {
                        String[] strings = defaultInfo[i].split(":");
                        int index = Integer.parseInt(strings[0].substring("fill-".length()));
                        ColorsParser colorsParser = new ColorsParser(strings[1]);
                        colorsMap.put(index, colorsParser);
                    } else {
                        String[] strings = defaultInfo[i].split(":");
                        ColorsParser colorsParser = new ColorsParser(strings[1]);
                        colorsMap.put(1, colorsParser);
                    }
                }
            }
        }
        return colorsMap;
    }

    /**
     * findColorStroke - find the colors.
     *
     * @param info        - line info block
     * @param defaultInfo - line default Info block.
     * @return - the color in the stroke, or if there is'nt color return null.
     */
    public static Color findColorStroke(String[] info, String[] defaultInfo) {
        Color stroke = null;
        for (int i = 0; i < info.length; i++) {
            if (info[i].contains("stroke")) {
                String[] s = info[i].split(":");
                ColorsParser colorsParser = new ColorsParser(s[1]);
                stroke = colorsParser.getColor();
                return stroke;
            }
        }
        for (int i = 0; i < defaultInfo.length; i++) {
            if (defaultInfo[i].contains("stroke")) {
                String[] s = defaultInfo[i].split(":");
                ColorsParser colorsParser = new ColorsParser(s[1]);
                stroke = colorsParser.getColor();
                return stroke;
            }
        }
        return stroke;
    }

    /**
     * findDefaultInfo - Looking for the default row of information blocks.
     *
     * @param allTextFile - the original text.
     * @return - the line .
     */
    public static String[] findDefaultInfo(ArrayList<String> allTextFile) {
        for (int i = 0; i < allTextFile.size(); i++) {
            if (allTextFile.get(i) != null) {
                if (allTextFile.get(i).contains("default")) {
                    if (!allTextFile.get(i).contains("#")) {
                        return allTextFile.get(i).split(" ");
                    }
                }
            }
        }
        String[] s = new String[0];
        return s;
    }

    /**
     * blockCreators - create a map of the blocks and they key.
     *
     * @param allTextFile - all the info get.
     * @return - the map.
     */
    public static Map<String, BlockCreator> blockCreators(ArrayList<String> allTextFile) {
        LevelSpecificationReader l = new LevelSpecificationReader();
        Map<String, BlockCreator> blocksMap = new TreeMap<>();
        try {
            String[] defaultInfo = findDefaultInfo(allTextFile);
            while (true) {
                try {
                    int index = l.findIndex(allTextFile, "bdef symbol");
                    String info = l.stringFind(allTextFile, "bdef symbol");
                    String[] strings = info.split(" ");
                    //Check all the information needed to create a block.
                    int high = findNum(strings, "height:", defaultInfo);
                    int whith = findNum(strings, "width:", defaultInfo);
                    int hitPoint = findNum(strings, "hit_points:", defaultInfo);
                    Map<Integer, ColorsParser> colorsParserMap = fillColors(strings, defaultInfo);
                    Color stroke = findColorStroke(strings, defaultInfo);
                    //create block.
                    BlockCreator blockCreator = new BlockCreator() {
                        @Override
                        public Block create(int xpos, int ypos) {
                            Block block = new Block(new Rectangle(new Point(xpos, ypos), whith, high), stroke,
                                    hitPoint, colorsParserMap);
                            return block;
                        }
                    };
                    blocksMap.put(strings[0], blockCreator);
                    allTextFile.remove(index);
                    allTextFile.add(index, " ");
                } catch (Exception e) {
                    return blocksMap;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return blocksMap;
    }

    /**
     * fromReader - get a file name and returns BlocksFromSymbolsFactory objects.
     *
     * @param reader - the text.
     * @return - BlocksFromSymbolsFactory .
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        ArrayList<String> allTextFile = allText(new BufferedReader(reader));
        Map<String, Integer> spacerWidths = spacerWidths(allTextFile);
        Map<String, BlockCreator> blockCreators = blockCreators(allTextFile);
        BlocksFromSymbolsFactory factory = new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
        return factory;
    }
}
