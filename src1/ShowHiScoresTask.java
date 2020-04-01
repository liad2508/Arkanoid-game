/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07 This class is in charge of run the high score animation as task.
 *
 */
public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * constructor for this class.
     * @param runner - run the animation
     * @param highScoresAnimation - the animation to run
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
      this.runner = runner;
      this.highScoresAnimation = highScoresAnimation;
   }
   @Override
public Void run() {
      this.runner.run(this.highScoresAnimation);
      return null;
   }
}