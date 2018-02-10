package app.logoworld;

enum PenState { UP, DOWN }

class Coordinates {
    private int x;
    private int y;
    private int width;
    private int height;

    /////////////////////////////////////////////
    //        ^                                //
    //        |                                //
    // height +                                //
    //        |                                //
    //      y + -  -  - o <- point             //
    //        |         |                      //
    //        |         |                      //
    //        |         |                      //
    //        ----------+---------------+----> //
    //                  x             width    //
    /////////////////////////////////////////////

    Coordinates(int x_, int y_, int width_, int height_) {
        x = x_;
        y = y_;
        width = width_;
        height = height_;
    }
    Coordinates(int width_, int height_) {
        // TODO how can I call the constructor above?
        x = 0;
        y = 0;
        width = width_;
        height = height_;
    }
    public void moveX(int shift) {
        if (shift > 0) x = (x + shift) % (width - 1);
        if (shift < 0) {
            // If we move with width-shift, we will return to the same point
            int real_shift = (-shift) % width;
            x = x - real_shift + width;
        }
    }
    public void moveY(int shift) {
        if (shift > 0) y = (y + shift) % (height - 1);
        if (shift < 0) {
            // If we move with height-shift, we will return to the same point
            int real_shift = (-shift) % height;
            y = y - real_shift + height;
        }
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int coordinate) {
        // TODO throw Exception;
        if (coordinate < width) x = coordinate;
    }
    public void setY(int coordinate) {
        // TODO throw Exception;
        if (coordinate < height) y = coordinate;
    }
    public void setXY(int x_, int y_) {
        // TODO throw Exception;
        if (x_ < width) x = x_;
        if (y_ < height) y = y_;
    }
}

public class TurtleState {
    private PenState pen_state;
    private Coordinates coordinates;

    TurtleState(int x_, int y_, int width_, int height_) {
        coordinates = new Coordinates(x_, y_, width_, height_);
        pen_state = PenState.UP;
    }

    public void putDownPen() {
        pen_state = PenState.DOWN;
    }

    public void putUpPen() {
        pen_state = PenState.UP;
    }

    public void move(char direction, int length) {
        if (direction == 'L') coordinates.moveX(-1 * length);
        if (direction == 'R') coordinates.moveX(length);
        if (direction == 'U') coordinates.moveY(length);
        if (direction == 'D') coordinates.moveY(-1 * length);
    }

    public void changeCoordinates(int x_, int y_) {
        coordinates.setXY(x_, y_);
    }
}

