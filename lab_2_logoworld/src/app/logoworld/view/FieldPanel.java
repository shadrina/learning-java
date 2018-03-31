package app.logoworld.view;

import app.logoworld.state.PlayingField;

import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {
    private static final int FIELD_WIDTH = 350;
    private static final int CELLS_COLUMNS = 13;
    private static final int CELLS_ROWS = 16;

    PlayingField pf = new PlayingField(CELLS_COLUMNS, CELLS_ROWS, 0 , 0);

    public FieldPanel() {
        Dimension size = getPreferredSize();
        size.width = FIELD_WIDTH;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Playing Field"));

        setLayout(new GridLayout(CELLS_ROWS, CELLS_COLUMNS));

        for (int i = 0; i < pf.getCellsCount(); i++) {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createEtchedBorder());
            add(cell);
        }
    }
}
