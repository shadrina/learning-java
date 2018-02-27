package brick_breaker;

public class Coordinates implements Cloneable {
    private int x;
    private int y;

    public Coordinates(int x_, int y_) {
        this.x = x_;
        this.y = y_;
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

    @Override
    public Coordinates clone() {
        Coordinates clone;
        try {
            clone = (Coordinates) super.clone();
            return clone;
        } catch(CloneNotSupportedException exception) {
            System.err.println("Cloning not allowed.");
            return this;
        }
    }

}
