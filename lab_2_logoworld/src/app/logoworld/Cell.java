package app.logoworld;

enum ColorState { COLORED, EMPTY }

public class Cell {
    private ColorState color_state;

    public void paintCell() {
        color_state = ColorState.COLORED;
    }
    public void clearCell() {
        color_state = ColorState.EMPTY;
    }
    public ColorState getColorState() {
        return color_state;
    }

}
