//import org.w3c.dom.css.Counter;
/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07
 *  a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 */

public class BlockRemover implements HitListener {
private GameLevel game;
private Counter remainingBlocks;

/**
 * constructor for class block remover.
 * @param game - the game to play
 * @param removedBlocks - number of remaining blocks
 */
public BlockRemover(GameLevel game, Counter removedBlocks) {
    this.game = game;
    this.remainingBlocks = removedBlocks;
}

// Blocks that are hit and reach 0 hit-points should be removed
// from the game. Remember to remove this listener from the block
// that is being removed from the game.
@Override
public void hitEvent(Block beingHit, Ball hitter) {
    if (beingHit.getNumHits() == 0) {
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
    beingHit.removeHitListener(hitter);


}

/**
 *
 * @return COunter - the remaining blocks in the game
 */
public Counter getCounter() {
    return this.remainingBlocks;
}
/**
 *
 * @param num - the number of blocks
 */
public void setValue(int num) {
    this.remainingBlocks.setValue(num);
}
}