package brick_breaker.mvc.view;

import brick_breaker.mvc.controller.BrickBreakerController;
import brick_breaker.mvc.model.BrickBreakerModel;

import javax.swing.JFrame;

public class BrickBreakerView {
    public void createUI(BrickBreakerModel model_, BrickBreakerController controller_) {
        JFrame obj = new JFrame();
        MainPanel main_panel = new MainPanel(model_, controller_);
        obj.setBounds(10, 10, 710, 620);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(main_panel);
    }
}
