package brick_breaker;

import brick_breaker.mvc.controller.BrickBreakerController;

public class Main {
    public static void main(String[] args) {
        BrickBreakerController controller = new BrickBreakerController();
        controller.start();
    }
}
