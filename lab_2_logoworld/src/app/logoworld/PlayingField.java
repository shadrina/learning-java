package app.logoworld;

public class PlayingField {
    private Cell[] cells;
    private TurtleState current_state;

    PlayingField(int width_, int height_, int x_, int y_) {
        cells = new Cell[width_ * height_];
        current_state = new TurtleState(x_, y_, width_, height_);
    }

    public TurtleState getTurtleState() {
        return current_state;
    }


}
