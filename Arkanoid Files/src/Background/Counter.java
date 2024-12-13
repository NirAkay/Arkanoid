package Background;
/**
 *@author nir akay 207387770
 **/
public class Counter {
    private int val;

    /**
     * constructor.
     * @param number a number to initialize
     * */
    public Counter(int number) {
        val = number;
    }

    /**
     *  add number to current count.
     *  @param number we want to add to the counter
     *  */
    public void increase(int number) {
        this.val += number;
    }

    /**
     * subtract number from current count.
     * @param number we want to decrease from the val
     * */
    public void decrease(int number) {
        this.val -= number;
    }

    /**
     * @return get current count.
     * */
    public int getValue() {
        return this.val;
    }
}