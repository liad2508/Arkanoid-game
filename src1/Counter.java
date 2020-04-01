/**
 * @author Liad Cohen <liad.cohen2508@gmail.com>
 * @version 1.6
 * @since 2019-03-07
 * Counter is a simple class that is used for counting things.
 *
 */
public class Counter {

    private int counter;

    /**
     * constructor for class counter.
     * @param counter1 - the number that we want to count.
     */
    public Counter(int counter1) {
        this.counter = counter1;
    }

    /**
     * add number to current count.
     * @param number - the number we want to increase the counter
     */
    void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     * @param number - the number we want to decrease the counter
     */
    void decrease(int number) {
        counter -= number;
    }

    /**
     * get current count.
     * @return the counter
     */
    int getValue() {
        return this.counter;
    }
    /**
     *
     * @param num - set the counter with given number
     */
    void setValue(int num) {
        this.counter = num;
    }
 }