package brick_breaker.mvc.model.state;

import brick_breaker.mvc.model.ModelCommons;

import java.util.Random;

public class CellsState implements Cloneable, ModelCommons {
    private enum CellType {
        EMPTY, // Empty area
        USUAL, // Cell that can be destructed by the touch of the ball
        STONE  // Cell that can't be destructed
    }
    private CellType cells[][];
    private int usual_cells_number;

    public CellsState(int max_stones_number) {
        cells = new CellType[CELL_ROWS][CELL_COLUMNS];
        for (int i = 0; i < CELL_ROWS; i++)
            for (int j = 0; j < CELL_COLUMNS; j++)
                cells[i][j] = CellType.USUAL;

        Random rand = new Random();
        int stone_cells_number = rand.nextInt(max_stones_number);
        generateStoneCells(stone_cells_number);
        usual_cells_number = CELL_ROWS * CELL_COLUMNS - stone_cells_number;
    }

    private void generateStoneCells(int stone_cells_number) {
        Random rand = new Random();
        while (stone_cells_number != 0) {
            int i = rand.nextInt(CELL_ROWS);
            int j = rand.nextInt(CELL_COLUMNS);
            cells[i][j] = CellType.STONE;
            stone_cells_number--;
        }
    }

    public boolean isEmpty(int i, int j) {
        return cells[i][j] == CellType.EMPTY;
    }

    public boolean isUsual(int i, int j) {
        return cells[i][j] == CellType.USUAL;
    }

    public boolean isStone(int i, int j) {
        return cells[i][j] == CellType.STONE;
    }

    public int getUsualCellsNumber() {
        return usual_cells_number;
    }

    public void destroyCell(int i, int j) {
        cells[i][j] = CellType.EMPTY;
    }

    public int getRowsNumber() {
        return CELL_ROWS;
    }

    public int getColumnNumber() {
        return CELL_COLUMNS;
    }

    @Override
    public CellsState clone() {
        CellsState clone;
        try {
            clone = (CellsState) super.clone();
            clone.cells = this.cells.clone();
            return clone;

        } catch(CloneNotSupportedException exception) {
            System.err.println("Cloning not allowed.");
            return this;
        }
    }

}
