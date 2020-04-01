/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @param <T> the tasks that we can run and return a value
 * @since 2019-05-20
 * This interface is for tasks, something that can run.
 */
public interface Task<T> {
    /**
     *
     * @return T - task
     */
   T run();
}
