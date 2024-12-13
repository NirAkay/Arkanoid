package Background;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;
/**
 * @author Nir Akay 207387770
 * */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private double clockSecond;
    private int countFrom;
    private int clockFrom;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * @param countFrom number to show
     * @param numOfSeconds second to count
     * @param gameScreen all the sprites in the level
     * constructor
     * */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.clockSecond = numOfSeconds;
        this.countFrom = countFrom;
        this.clockFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * @param d the screen we paint on him
     * do one frame forward
     * */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE.darker().darker());
        Sleeper sleeper = new Sleeper();
        d.drawText(390, 450, "" + this.countFrom--, 50);
        if (this.numOfSeconds == -1) {
            this.stop = true;
        }
        if (numOfSeconds != 2) {
            sleeper.sleepFor((long) (1000 * this.clockSecond / this.clockFrom));
        }
        numOfSeconds--;
    }

    /**
     * @return if the animation need to stop
     * */
    public boolean shouldStop() {
        return this.stop;
    }
}
