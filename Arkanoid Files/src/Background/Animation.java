package Background;
import biuoop.DrawSurface;
/**
 * @author Nir Akay 207387770
 * */
public interface Animation {
    /**
     * @param d the screen we paint on him
     * do one frame forward
     * */
    void doOneFrame(DrawSurface d);

    /**
     * @return if the animation need to stop
     * */
    boolean shouldStop();
}
