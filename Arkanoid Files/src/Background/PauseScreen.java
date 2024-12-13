package Background;
import biuoop.DrawSurface;

/**
 * @author Nir Akay 207387770
 * */
public class PauseScreen implements Animation {
    private boolean stop;


    /**
     * constructor.
     * */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * @param d the screen we paint on him
     * do one frame forward
     * */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * @return if the animation need to stop
     * */
    public boolean shouldStop() {
        return this.stop;
    }
}