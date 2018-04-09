package app.logoworld.view.info;


import app.logoworld.view.field.state.TurtleState;

import javax.swing.*;
import java.awt.*;

public class TurtleInfo extends JPanel implements InfoCommons {

    private JLabel xCoordinate = new JLabel(NO_COORDINATE_LABEL);
    private JLabel yCoordinate = new JLabel(NO_COORDINATE_LABEL);
    private ColorIcon currentColor = new ColorIcon();
    private JLabel stepsMade = new JLabel(NO_STEPS_LABEL);
    private JLabel penState = new JLabel(ARROW_UP);

    TurtleInfo() {
        setPreferredSize(new Dimension(TURTLEINFO_PANEL_WIDTH, TURTLEINFO_PANEL_HEIGHT));
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
        add(new JLabel("Steps made: "), gc);
        gc.gridy = 3;
        add(new JLabel("Pen color: "), gc);
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
        add(stepsMade, gc);
        gc.gridy = 3;
        add(currentColor, gc);
        gc.gridy = 4;
        add(penState, gc);
    }

    private void setInitData() {
        xCoordinate.setText(NO_COORDINATE_LABEL);
        yCoordinate.setText(NO_COORDINATE_LABEL);
        stepsMade.setText(NO_STEPS_LABEL);
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
