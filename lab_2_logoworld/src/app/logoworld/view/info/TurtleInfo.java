package app.logoworld.view.info;

import app.logoworld.view.field.state.TurtleState;

import javax.swing.*;
import java.awt.*;

public class TurtleInfo extends JPanel {
    private static final int PANEL_WIDTH = 120;
    private static final int PANEL_HEIGHT = 90;
    private static final String ARROW_UP = "↑";
    private static final String ARROW_DOWN = "↓";

    JLabel xCoordinate = new JLabel("-1");
    JLabel yCoordinate = new JLabel("-1");
    ColorIcon currentColor = new ColorIcon();
    JLabel stepsMade = new JLabel("0");
    JLabel penState = new JLabel(ARROW_UP);

    public TurtleInfo() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // First column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;

        gc.gridy = 0;
        add(new JLabel("X: "), gc);
        gc.gridy = 1;
        add(new JLabel("Y: "), gc);
        gc.gridy = 2;
        add(new JLabel("Current color: "), gc);
        gc.gridy = 3;
        add(new JLabel("Steps made: "), gc);
        gc.gridy = 4;
        add(new JLabel("Pen state: "), gc);

        // Second column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;

        gc.gridy = 0;
        add(xCoordinate, gc);
        gc.gridy = 1;
        add(yCoordinate, gc);
        gc.gridy = 2;
        add(currentColor, gc);
        gc.gridy = 3;
        add(stepsMade, gc);
        gc.gridy = 4;
        add(penState, gc);
    }

    private void setInitData() {
        xCoordinate.setText("-");
        yCoordinate.setText("-");
        stepsMade.setText("0");
        penState.setText(ARROW_UP);
    }

    public void refresh(TurtleState ts) {
        if (ts == null) {
            setInitData();
            return;
        }
        if (Integer.parseInt(xCoordinate.getText()) != ts.getX()
                || Integer.parseInt(yCoordinate.getText()) != ts.getY()) {
            stepsMade.setText(String.valueOf(Integer.parseInt(stepsMade.getText()) + 1));
            xCoordinate.setText(String.valueOf(ts.getX()));
            yCoordinate.setText(String.valueOf(ts.getY()));
        }
        currentColor.setColor(ts.getColor());
        if (ts.isDrawing()) {
            penState.setText(ARROW_DOWN);
        } else {
            penState.setText(ARROW_UP);
        }

        repaint();
    }
}
