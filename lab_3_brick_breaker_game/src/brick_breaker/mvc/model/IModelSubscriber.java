package brick_breaker.mvc.model;

/**
 * The subscriber of BrickBreakerModel
 */
public interface IModelSubscriber {
    /**
     * Reaction for model changing event
     * @param model model object
     */
    void modelChanged(BrickBreakerModel model);
}
