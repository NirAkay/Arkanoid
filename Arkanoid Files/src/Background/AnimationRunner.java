package Background;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * @author Nir Akay 207387770
 * */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    static final int MILI_SEC = 1000;

    /**
     * @param gui a Gui
     * @param frames fps we want
     * constructor
     * */
    public AnimationRunner(GUI gui, int frames) {
        this.gui = gui;
        this.framesPerSecond = frames;
    }

    /**
     * @return this gui
     * */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * @param animation the Animation we want to show
     * activate the animation we want
     * */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = MILI_SEC / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
