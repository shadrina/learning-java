package brick_breaker.mvc.view;

import brick_breaker.mvc.controller.BrickBreakerController;
import brick_breaker.mvc.model.BrickBreakerModel;

import javax.swing.JFrame;

public class BrickBreakerView implements ViewCommons {
    public void createUI(BrickBreakerModel model_, BrickBreakerController controller_) {
        JFrame obj = new JFrame();
        MainPanel mainPanel = new MainPanel(model_, controller_);
        obj.setBounds(APP_WINDOW_COORDINATES.getX(), APP_WINDOW_COORDINATES.getY(), APP_WINDOW_WIDTH, APP_WINDOW_HEIGTH);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(mainPanel);
    }
}
