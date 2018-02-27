package brick_breaker.mvc.model.state;

import brick_breaker.mvc.model.ModelCommons;
import brick_breaker.Coordinates;

public class PlayerState implements Cloneable, ModelCommons {
    private Coordinates player = new Coordinates(INIT_PLAYER_X, INIT_PLAYER_Y);
    private int speed = INIT_SPEED;

    public PlayerState() { }

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

    @Override
    public PlayerState clone() {
        PlayerState clone;
        try {
            clone = (PlayerState) super.clone();
            clone.player = this.player.clone();
            return clone;

        } catch(CloneNotSupportedException exception) {
            System.err.println("Cloning not allowed.");
            return this;
        }
    }

}
