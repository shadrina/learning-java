package app.logoworld.view.field;

import app.logoworld.view.field.state.TurtleState;

import java.util.EventObject;

public class FieldEvent extends EventObject {
    private TurtleState ts;

    public FieldEvent(Object source, TurtleState ts) {
        super(source);

        this.ts = ts;
    }

    public TurtleState getTurtleState() {
        return ts;
    }
}
