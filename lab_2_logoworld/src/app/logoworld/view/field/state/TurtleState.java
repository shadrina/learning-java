package app.logoworld.view.field.state;

import java.awt.Color;

public class TurtleState {

    private int x;
    private int y;
    private boolean penIsUp = true;
    private Color color = Color.PINK;

    public TurtleState(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void putDownPen() {
        penIsUp = false;
    }

    public void putUpPen() {
        penIsUp = true;
    }

    public boolean isDrawing() {
        return !penIsUp;
    }
}
