package brick_breaker.mvc.model.state;

import brick_breaker.mvc.model.ModelCommons;
import brick_breaker.Coordinates;
import javafx.print.PageLayout;

/**
 * Contains the current coordinates of the player, the ball and their characteristics
 */
public class PlayerState implements ModelCommons {
    private Coordinates player = new Coordinates(INIT_PLAYER_X, INIT_PLAYER_Y);
    private int speed = INIT_SPEED;

    public static PlayerState newInstance(PlayerState playerState) {
        return new PlayerState(Coordinates.newInstance(playerState.player), playerState.speed);
    }

    PlayerState(Coordinates player_, int speed_) {
        player = player_;
        speed = speed_;
    }

    public PlayerState() { }

    /**
     * Move the player (paddle)
     * @param direction direction
     */
    public void movePlayer(String direction) {
        if (direction.equals("L")) player.setX(player.getX() - speed);
        if (direction.equals("R")) player.setX(player.getX() + speed);
    }

    public void moveToInitPosition() {
        player.setXY(INIT_PLAYER_X, INIT_PLAYER_Y);
    }

    public void setX(int x_) {
        player.setX(x_);
    }

    public int getX() {
        return player.getX();
    }

    public int getY() {
        return player.getY();
    }

}
