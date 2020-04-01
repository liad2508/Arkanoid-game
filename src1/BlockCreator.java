/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-06-10 an interface of a factory-object that is used for creating blocks:
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     * @param xpos location in x.
     * @param ypos - location in x.
     * @return - new block.
     */
    Block create(int xpos, int ypos);
}
