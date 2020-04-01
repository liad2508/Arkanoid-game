
import java.util.Map;

/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-06-10 This class in order to create the desired blocks.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * create new BlocksFromSymbolsFactory .
     *
     * @param spacerWidths  - a map of the spaces.
     * @param blockCreators - a map of the blocks.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths,
                                    Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * isSpaceSymbol.
     *
     * @param s - the key.
     * @return -  returns true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * isBlockSymbol.
     *
     * @param s - the key.
     * @return - returns true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).

    /**
     * getBlock.
     *
     * @param s    - symbol block to create.
     * @param xpos - located at position x.
     * @param ypos - located at position y.
     * @return Return a block according to the definitions associated.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * getSpaceWidth.
     *
     * @param s - the key space.
     * @return - Returns the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        int w = this.spacerWidths.get(s);
        return w;
    }
}
