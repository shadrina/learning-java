package app.logoworld.state;

import app.logoworld.state.Cell;
import app.logoworld.state.TurtleState;

public class PlayingField {
    private Cell[] cells;
    private TurtleState current_state;

    public PlayingField(int width_, int height_, int x_, int y_) {
        cells = new Cell[width_ * height_];
        current_state = new TurtleState(x_, y_, width_, height_);
    }

    public TurtleState getTurtleState() {
        return current_state;
    }

    public int getCellsCount() {
        return cells.length;
    }


}
