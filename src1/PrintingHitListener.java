
public class PrintingHitListener implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       System.out.println("A Block with " + beingHit.getNumHits() + " points was hit.");
    }
 }