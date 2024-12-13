package Levels;
import Background.AnimationRunner;
import Background.Counter;
import Background.EndWin;
import Background.EndLose;
import Background.KeyPressStoppableAnimation;
import Background.GameLevel;
import biuoop.KeyboardSensor;

import java.util.List;
/**
 * @author Nir Akay 207387770
 * */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;

    /**
     * @param ks Keyboard sensor
     * @param ar the Animation that run the game
     * constructor
     * */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * @param levels List of levels
     * run all the levels in levels
     * */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter(0);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score);
            level.initialize();
            while (level.getBlocksCount() != 0 && level.getBallsCount() != 0) {
                level.run();
            }
            if (level.getBlocksCount() == 0 && levels.size() == levels.indexOf(levelInfo) + 1) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        "space", new EndWin(score)));
                this.animationRunner.getGui().close();
            }
            if (level.getBallsCount() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        "space", new EndLose(score)));
                this.animationRunner.getGui().close();
            }
        }
    }
}

