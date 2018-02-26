package brick_breaker.mvc.model.state;

import brick_breaker.mvc.model.ModelCommons;

// TODO JavaDoc

/**
 * Main user's characteristics
 */
public class UserState implements ModelCommons {
    private enum Mode { ON, OFF }
    private Mode playing_mode = Mode.OFF;
    private int score = INIT_SCORE;
    private int lives_number = INIT_LIVES_NUMBER;

    public static UserState newInstance(UserState userState) {
        return new UserState(userState.playing_mode, userState.score, userState.lives_number);
    }

    public UserState() { }

    UserState(Mode playing_mode_, int score_, int lives_number_) {
        playing_mode = playing_mode_;
        score = score_;
        lives_number = lives_number_;
    }

    public void startPlaying() {
        this.playing_mode = Mode.ON;
    }

    public void stopPlaying() {
        this.playing_mode = Mode.OFF;
    }

    public void increaseScore(int reward) {
        score += reward;
    }

    public void nullifyScore() {
        score = INIT_SCORE;
    }

    public void restoreGameLives() {
        lives_number = INIT_LIVES_NUMBER;
    }

    public void loseGameLife() {
        lives_number--;
    }

    public boolean isPlaying() {
       return playing_mode == Mode.ON;
    }

    public int getScore() {
        return score;
    }

    public int getLivesNumber() {
        return lives_number;
    }

}
