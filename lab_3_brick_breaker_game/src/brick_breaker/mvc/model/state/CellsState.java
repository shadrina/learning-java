package brick_breaker.mvc.model.state;

import brick_breaker.mvc.model.ModelCommons;
import javafx.scene.control.Cell;

import java.util.Random;

/**
 * Contains cells with their characteristics
 */
public class CellsState implements ModelCommons {
    private enum CellType {
        EMPTY, // Empty area
        USUAL, // Cell that can be destructed by the touch of the ball
        STONE  // Cell that can't be destructed
    }
    private CellType cells[][];
    private int usual_cells_number;

    public static CellsState newInstance(CellsState cellsState) {
        return new CellsState(cellsState.cells.clone(), cellsState.getUsualCellsNumber());
    }

    /**
     * Constructor
     * Fills the array with usual cells and generates the number of stone cells
     */
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

    public CellsState(CellType[][] cells_, int usual_cells_number_) {
        cells = cells_;
        usual_cells_number = usual_cells_number_;
    }

    /**
     * Randomly make some of cells stony
     * @param stone_cells_number the number of stone cells
     */
    private void generateStoneCells(int stone_cells_number) {
        Random rand = new Random();
        while (stone_cells_number != 0) {
            int i = rand.nextInt(CELL_ROWS);
            int j = rand.nextInt(CELL_COLUMNS);
            cells[i][j] = CellType.STONE;
            stone_cells_number--;
        }
    }

    /**
     * Get the type of the concrete cell
     * @param i row number of the cell
     * @param j column number of the cell
     * @return the type of the cell under the number [i][j]
     */
    public CellType getCellType(int i, int j) {
        return cells[i][j];
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

    /**
     * Get the number of usual cells
     * @return usual cells number
     */
    public int getUsualCellsNumber() {
        return usual_cells_number;
    }

    /**
     * Make the concrete cell empty
     * @param i row number of the cell
     * @param j column number of the cell
     */
    public void destroyCell(int i, int j) {
        cells[i][j] = CellType.EMPTY;
    }

    public int getRowsNumber() {
        return CELL_ROWS;
    }

    public int getColumnNumber() {
        return CELL_COLUMNS;
    }
}
