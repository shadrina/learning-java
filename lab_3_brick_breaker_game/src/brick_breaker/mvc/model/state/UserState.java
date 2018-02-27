package brick_breaker.mvc.model.state;

import brick_breaker.mvc.model.ModelCommons;

public class UserState implements Cloneable, ModelCommons {
    private enum Mode { ON, OFF }
    private Mode playingMode = Mode.OFF;
    private int score = INIT_SCORE;
    private int livesNumber = INIT_LIVES_NUMBER;

    public void startPlaying() {
        this.playingMode = Mode.ON;
    }

    public void stopPlaying() {
        this.playingMode = Mode.OFF;
    }

    public void increaseScore(int reward) {
        score += reward;
    }

    public void nullifyScore() {
        score = INIT_SCORE;
    }

    public void restoreGameLives() {
        livesNumber = INIT_LIVES_NUMBER;
    }

    public void loseGameLife() {
        livesNumber--;
    }

    public boolean isPlaying() {
       return playingMode == Mode.ON;
    }

    public int getScore() {
        return score;
    }

    public int getLivesNumber() {
        return livesNumber;
    }

    @Override
    public UserState clone() {
        UserState clone;
        try {
            clone = (UserState) super.clone();
            return clone;

        } catch(CloneNotSupportedException exception) {
            System.err.println("Cloning not allowed.");
            return this;
        }
    }

}
