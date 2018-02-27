package brick_breaker.mvc.controller;

import brick_breaker.mvc.model.BrickBreakerModel;
import brick_breaker.mvc.model.state.BallState;
import brick_breaker.mvc.model.state.CellsState;
import brick_breaker.mvc.model.state.PlayerState;
import brick_breaker.mvc.model.state.UserState;
import brick_breaker.mvc.view.BrickBreakerView;

/**
 * BrickBreakerController
 */
public class BrickBreakerController implements ControllerCommons {

    private static BrickBreakerModel model = new BrickBreakerModel(
            new UserState(),
            new PlayerState(),
            new BallState(),
            new CellsState(MAX_STONES_NUMBER));
    private static BrickBreakerView view = new BrickBreakerView();

    public void start() {
        view.createUI(model.clone(), this);
    }

    public void restart() {
        UserState userState = model.getUserState();
        PlayerState playerState = model.getPlayerState();
        BallState ballState = model.getBallState();

        userState.startPlaying();
        playerState.moveToInitPosition();
        ballState.moveToInitPosition();
        userState.nullifyScore();
        userState.restoreGameLives();

        model.setState(userState);
        model.setState(playerState);
        model.setState(ballState);
        model.setState(new CellsState(MAX_STONES_NUMBER));
    }

    public void stopGame() {
        UserState userState = model.getUserState();
        BallState ballState = model.getBallState();

        userState.stopPlaying();
        userState.loseGameLife();
        ballState.changeDirection(0,0);

        model.setState(userState);
        model.setState(ballState);
    }

    public void startNewGameLife() {
        UserState userState = model.getUserState();
        PlayerState playerState = model.getPlayerState();
        BallState ballState = model.getBallState();

        userState.stopPlaying();
        userState.loseGameLife();
        playerState.moveToInitPosition();
        ballState.moveToInitPosition();

        model.setState(userState);
        model.setState(playerState);
        model.setState(ballState);
    }

    public void destroyBrickAndGetReward(int i, int j) {
        CellsState cellsState = model.getCellsState();
        UserState userState = model.getUserState();

        if (cellsState.isUsual(i, j)) {
            userState.increaseScore(REWARD_FOR_GREEN);
            cellsState.destroyCell(i, j);
        }
        if (cellsState.isStone(i, j)) userState.increaseScore(REWARD_FOR_STONE);

        model.setState(cellsState);
        model.setState(userState);

    }

    public void moveBall() {
        BallState ballState = model.getBallState();
        ballState.moveBall();
        model.setState(ballState);
    }

    public void repelBallX() {
        BallState ballState = model.getBallState();
        ballState.changeXDirection(ballState.getXDirection() * -1);
        model.setState(ballState);
    }

    public void repelBallY() {
        BallState ballState = model.getBallState();
        ballState.changeYDirection(ballState.getYDirection() * -1);
        model.setState(ballState);
    }

    public void changePlayerX(int x_) {
        PlayerState playerState = model.getPlayerState();
        playerState.setX(x_);
        model.setState(playerState);
    }

    public void movePlayer(String direction) {
        UserState userState = model.getUserState();
        PlayerState playerState = model.getPlayerState();

        userState.startPlaying();
        playerState.movePlayer(direction);

        model.setState(userState);
        model.setState(playerState);
    }

}
