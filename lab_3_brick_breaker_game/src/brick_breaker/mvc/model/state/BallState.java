package brick_breaker.mvc.model.state;

import brick_breaker.Coordinates;
import brick_breaker.mvc.model.ModelCommons;

// TODO JavaDoc

public class BallState implements ModelCommons {
    private Coordinates ball = new Coordinates(INIT_BALL_X, INIT_BALL_Y);
    private int ball_x_direction = INIT_BALL_DIR_X;
    private int ball_y_direction = INIT_BALL_DIR_Y;

    public static BallState newInstance(BallState ballState) {
        return new BallState(Coordinates.newInstance(ballState.ball), ballState.ball_x_direction, ballState.ball_y_direction);
    }

    public BallState() { }

    BallState(Coordinates ball_, int ball_x_direction_, int ball_y_direction_) {
        ball = ball_;
        ball_x_direction = ball_x_direction_;
        ball_y_direction = ball_y_direction_;
    }

    public void moveBall() {
       ball.setX(ball.getX() + ball_x_direction);
       ball.setY(ball.getY() + ball_y_direction);
    }

    public void moveToInitPosition() {
        ball.setXY(INIT_BALL_X, INIT_BALL_Y);
        ball_x_direction = INIT_BALL_DIR_X;
        ball_y_direction = INIT_BALL_DIR_Y;
    }

    public void changeDirection(int new_x_direction, int new_y_direction) {
        ball_x_direction = new_x_direction;
        ball_y_direction = new_y_direction;
    }

    public void changeXDirection(int new_x_direction) {
        ball_x_direction = new_x_direction;
    }

    public void changeYDirection(int new_y_direction) {
        ball_y_direction = new_y_direction;
    }

    public int getXDirection() {
        return ball_x_direction;
    }

    public int getYDirection() {
        return ball_y_direction;
    }

    public int getX() {
        return ball.getX();
    }

    public int getY() {
        return ball.getY();
    }

}
