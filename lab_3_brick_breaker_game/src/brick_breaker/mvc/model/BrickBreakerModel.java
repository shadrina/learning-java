package brick_breaker.mvc.model;

import brick_breaker.mvc.model.state.BallState;
import brick_breaker.mvc.model.state.CellsState;
import brick_breaker.mvc.model.state.PlayerState;
import brick_breaker.mvc.model.state.UserState;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class BrickBreakerModel implements Cloneable {

    private UserState userState;
    private PlayerState playerState;
    private BallState ballState;
    private CellsState cellsState;

    private final Collection<IModelSubscriber> subscribers = new CopyOnWriteArrayList<IModelSubscriber>();

    public BrickBreakerModel(UserState us, PlayerState ps, BallState bs, CellsState cs) {
        if (us == null || ps == null || bs == null || cs == null)
            throw new NullPointerException("No parameter");
        this.userState = us;
        this.playerState = ps;
        this.ballState = bs;
        this.cellsState = cs;
    }

    public UserState getUserState() {
        return userState.clone();
    }
    public PlayerState getPlayerState() {
        return playerState.clone();
    }
    public BallState getBallState() {
        return ballState.clone();
    }
    public CellsState getCellsState() {
        return cellsState.clone();
    }

    public void setState(UserState state) {
        if (state == null) throw new NullPointerException("No parameter");
        this.userState = state;
        notifySubscribers();
    }
    public void setState(PlayerState state) {
        if (state == null) throw new NullPointerException("No parameter");
        this.playerState = state;
        notifySubscribers();
    }
    public void setState(BallState state) {
        if (state == null) throw new NullPointerException("No parameter");
        this.ballState = state;
        notifySubscribers();
    }
    public void setState(CellsState state) {
        if (state == null) throw new NullPointerException("No parameter");
        this.cellsState = state;
        notifySubscribers();
    }

    protected void notifySubscribers() {
        for (final IModelSubscriber subscriber : subscribers)
            notifySubscriber(subscriber);
    }

    private void notifySubscriber(IModelSubscriber subscriber) {
        assert subscriber != null;
        subscriber.modelChanged(this.clone());
    }

    public void subscribe(IModelSubscriber subscriber) {
        if (subscriber == null) throw new NullPointerException("No parameter");
        if (subscribers.contains(subscriber)) throw new IllegalArgumentException("Resubmit: " + subscriber);
        subscribers.add(subscriber);
        notifySubscriber(subscriber);
    }

    public void unsubscribe(IModelSubscriber subscriber) {
        if (subscriber == null) throw new NullPointerException("No parameter");
        if (!subscribers.contains(subscriber)) throw new IllegalArgumentException("Unknown subscriber: " + subscriber);
        subscribers.remove(subscriber);
    }

    @Override
    public BrickBreakerModel clone()
    {
        BrickBreakerModel clone;
        try {
            clone = (BrickBreakerModel) super.clone();
            clone.userState = this.userState.clone();
            clone.playerState = this.playerState.clone();
            clone.ballState = this.ballState.clone();
            clone.cellsState = this.cellsState.clone();
            return clone;
        } catch (CloneNotSupportedException exception) {
            // TODO throw Exception();
            return this;
        }
    }

}