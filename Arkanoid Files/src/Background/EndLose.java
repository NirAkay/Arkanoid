package Background;
import biuoop.DrawSurface;

/**
 * @author Nir Akay 207387770
 * */
public class EndLose implements Animation {
    private boolean stop;
    private Counter score;

    /**
     * @param score the final score
     * constructor
     * */
    public EndLose(Counter score) {
        this.stop = false;
        this.score = score;
    }

    /**
     * @param d the screen we paint on him
     * do one frame forward
     * */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
    }

    /**
     * @return if the animation need to stop
     * */
    public boolean shouldStop() {
        return this.stop;
    }
}