package brick_breaker;

/**
 * Simple class for storing coordinates
 */
public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x_, int y_) {
        this.x = x_;
        this.y = y_;
    }

    public static Coordinates newInstance(Coordinates coordinates) {
        return new Coordinates(coordinates.getX(), coordinates.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x_, int y_) {
        this.x = x_;
        this.y = y_;
    }
}
