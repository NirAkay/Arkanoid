package Background;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Nir Akay 207387770
 * */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;

    /**
     * @param animation the type of animation we want to activate
     * @param key the key we pressed on the keyboard
     * @param sensor Keyboard sensor
     * constructor
     * */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.stop = false;
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    /**
     * @param d the screen we paint on him
     * do one frame forward
     * */
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * @return if the animation need to stop
     * */
    public boolean shouldStop() {
        return this.stop;
    }
}
