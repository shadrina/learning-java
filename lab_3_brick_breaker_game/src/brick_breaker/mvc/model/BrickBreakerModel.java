package brick_breaker.mvc.model;

import brick_breaker.mvc.model.state.BallState;
import brick_breaker.mvc.model.state.CellsState;
import brick_breaker.mvc.model.state.PlayerState;
import brick_breaker.mvc.model.state.UserState;
import javafx.scene.control.Cell;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * BrickBreakerModel
 */
public class BrickBreakerModel {
    private UserState user_state;
    private PlayerState player_state;
    private BallState ball_state;
    private CellsState cells_state;

    private final Collection<IModelSubscriber> subscribers = new CopyOnWriteArrayList<IModelSubscriber>();

    /**
     * Constructor
     * @param us the state of user
     * @param ps the state of player (paddle)
     * @param bs the state of ball
     * @param cs the state of cells array
     */
    public BrickBreakerModel(UserState us, PlayerState ps, BallState bs, CellsState cs) {
        if (us == null || ps == null || bs == null || cs == null)
            throw new NullPointerException("No parameter");
        this.user_state = us;
        this.player_state = ps;
        this.ball_state = bs;
        this.cells_state = cs;
    }

    /**
     * Get the states of a model
     * @return the concrete state of model
     */
    public UserState getUserState() {
        return UserState.newInstance(user_state);
    }
    public PlayerState getPlayerState() {
        return PlayerState.newInstance(player_state);
    }
    public BallState getBallState() {
        return BallState.newInstance(ball_state);
    }
    public CellsState getCellsState() {
        return CellsState.newInstance(cells_state);
    }

    /**
     * Set the state of a model
     * @param state the state of model
     */
    public void setState(UserState state) {
        if (state == null) throw new NullPointerException("No parameter");
        this.user_state = state;
        notifySubscribers();
    }
    public void setState(PlayerState state) {
        if (state == null) throw new NullPointerException("No parameter");
        this.player_state = state;
        notifySubscribers();
    }
    public void setState(BallState state) {
        if (state == null) throw new NullPointerException("No parameter");
        this.ball_state = state;
        notifySubscribers();
    }
    public void setState(CellsState state) {
        if (state == null) throw new NullPointerException("No parameter");
        this.cells_state = state;
        notifySubscribers();
    }

    /**
     * Notify subscribers about state changes
     */
    protected void notifySubscribers() {
        for (final IModelSubscriber subscriber : subscribers)
            notifySubscriber(subscriber);
    }

    /**
     * Notify subscriber
     * @param subscriber model subscriber
     */
    private void notifySubscriber(IModelSubscriber subscriber) {
        assert subscriber != null;
        subscriber.modelChanged(this);
    }

    /**
     * Subscribe
     * @param subscriber model subscriber
     */
    public void subscribe(IModelSubscriber subscriber) {
        if (subscriber == null) throw new NullPointerException("No parameter");
        if (subscribers.contains(subscriber)) throw new IllegalArgumentException("Resubmit: " + subscriber);
        subscribers.add(subscriber);
        // Notify new subscriber about current state
        notifySubscriber(subscriber);
    }

    /**
     * Unsubscribe
     * @param subscriber model subscriber
     */
    public void unsubscribe(IModelSubscriber subscriber) {
        if (subscriber == null) throw new NullPointerException("No parameter");
        if (!subscribers.contains(subscriber)) throw new IllegalArgumentException("Unknown subscriber: " + subscriber);
        subscribers.remove(subscriber);
    }

}