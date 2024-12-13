import Background.AnimationRunner;
import Levels.GreenLevel;
import Levels.FinalFour;
import Levels.DirectHit;
import Levels.LevelInformation;
import Levels.WideEasy;
import Levels.GameFlow;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 *@author Nir Akay 207387770
 **/
public class Ass6Game {
    static final int DEFAULT_WIDTH = 800;
    static final int DEFAULT_HEIGHT = 600;
    static final int FRAMES_PER_SECOND = 60;

    /**
     * the first function that run in our program.
     * @param args arguments get from the user
     * */
    public static void main(String[] args) {
        GUI gui = new GUI("title", DEFAULT_WIDTH, DEFAULT_HEIGHT);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui, FRAMES_PER_SECOND);
        List<LevelInformation> levels = new ArrayList<>(), arrangeLevels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new GreenLevel());
        levels.add(new FinalFour());
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                arrangeLevels.add(new DirectHit());
            }
            if (args[i].equals("2")) {
                arrangeLevels.add(new WideEasy());
            }
            if (args[i].equals("3")) {
                arrangeLevels.add(new GreenLevel());
            }
            if (args[i].equals("4")) {
                arrangeLevels.add(new FinalFour());
            }
        }
        GameFlow gameFlow = new GameFlow(animationRunner, keyboard);
        if (arrangeLevels.size() == 0) {
            gameFlow.runLevels(levels);
        } else {
            gameFlow.runLevels(arrangeLevels);
        }
    }
}