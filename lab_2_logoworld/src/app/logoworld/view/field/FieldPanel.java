package app.logoworld.view.field;

import app.logoworld.api.CommandExecutor;
import app.logoworld.exception.InvalidArgumentException;
import app.logoworld.exception.InvalidCommandException;
import app.logoworld.exception.LogoWorldException;
import app.logoworld.view.field.state.CellPanel;
import app.logoworld.view.field.state.TurtleState;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FieldPanel extends JPanel implements FieldCommons {

    private static BufferedImage background = null;

    private TurtleState turtle = null;
    private ArrayList<CellPanel> cells = new ArrayList<>();
    private int cellsColumns;
    private int cellsRows;

    private EventListenerList listenerList = new EventListenerList();

    static {
        try {
            background = ImageIO.read(new File(RSC_PATH + "background.png"));
        } catch(IOException ex) {
            // ...
        }
    }

    public FieldPanel() {
        setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        setBorder(BorderFactory.createTitledBorder("Playing Field"));
    }

    public void reactOnCommand(String command, String[] args) throws LogoWorldException {
        CommandExecutor.executeCommand(command, args, this);
    }

    public void clear() {
        turtle = null;
        for (CellPanel cell: cells) {
            remove(cell);
        }
        cells.clear();

        repaint();
    }

    private void scatterArtifacts() {
        Random rand = new Random();
        int artifactsNumber = rand.nextInt(MAX_ARTIFACTS_NUMBER) + 1;

        for (int i = 0; i < artifactsNumber; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color color = new Color(r, g, b);

            int x = rand.nextInt(cellsColumns - 1);
            int y = rand.nextInt(cellsRows - 1);
            cells.get(y * cellsColumns + x).setArtifact(color);
        }
    }

    private void checkArtifact(@NotNull CellPanel cell) {
        if (cell.hasArtifact()) {
            turtle.setColor(cell.getArtifactColor());
            cell.removeArtifact();
        }
    }

    public void initCells(int columns, int rows) {
        setLayout(new GridLayout(rows, columns));
        this.cellsColumns = columns;
        this.cellsRows = rows;

        for (int i = 0; i < columns * rows; i++) {
            CellPanel cell = new CellPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            cell.setOpaque(false);
            cells.add(cell);
        }
        scatterArtifacts();
        for (CellPanel cell: cells) {
            add(cell);
        }
    }

    public void putTurtle(int x, int y) {
        x %= cellsColumns;
        y %= cellsRows;
        if (turtle == null) {
            turtle = new TurtleState(x, y);
        } else {
            cells.get(turtle.getY() * cellsColumns + turtle.getX()).removeTurtle();
            turtle.setXY(x, y);
        }
        CellPanel newCell = cells.get(y * cellsColumns + x);
        checkArtifact(newCell);
        newCell.renderTurtle(DEFAULT_DIRECTION);

        repaint();
    }

    public void moveTurtle(char direction) {
        int oldX = turtle.getX();
        int oldY = turtle.getY();
        cells.get(oldY * cellsColumns + oldX).removeTurtle();

        int newX = oldX;
        int newY = oldY;
        switch (direction) {
            case 'U':
                newY = (oldY == 0) ? cellsRows - 1 : oldY - 1;
                break;
            case 'D':
                newY = (oldY == cellsRows - 1) ? 0 : oldY + 1;
                break;
            case 'R':
                newX = (oldX == cellsColumns - 1) ? 0 : oldX + 1;
                break;
            case 'L':
                newX = (oldX == 0) ? cellsColumns - 1 : oldX - 1;
                break;
        }

        if (turtle.isDrawing()) {
            Color color = turtle.getColor();
            cells.get(oldY * cellsColumns + oldX).setColor(color);
        }

        CellPanel newCell = cells.get(newY * cellsColumns + newX);
        checkArtifact(newCell);
        newCell.renderTurtle(direction);
        turtle.setXY(newX, newY);

        repaint();
    }

    public TurtleState getTurtle() {
        return this.turtle;
    }

    public void fireFieldEvent(FieldEvent e) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i +=2) {
            if (listeners[i] == FieldListener.class) {
                ((FieldListener)listeners[i + 1]).fieldEventOccurred(e);
            }
        }
    }

    public void addFieldListener(FieldListener listener) {
        listenerList.add(FieldListener.class, listener);
    }

    public void removeFieldListener(FieldListener listener) {
        listenerList.remove(FieldListener.class, listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, BACKGROUND_XSHIFT, BACKGROUND_YSHIFT, this);
    }
}
