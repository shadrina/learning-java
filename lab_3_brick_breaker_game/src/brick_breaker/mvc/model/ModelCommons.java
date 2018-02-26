package brick_breaker.mvc.model;

/**
 * Keys
 */
public interface ModelCommons {

    /**
     * Initial state of main parameters:
     * player (paddle) location,
     * player speed,
     * ball location,
     * ball direction,
     */
    public static final int INIT_PLAYER_X = 290;
    public static final int INIT_PLAYER_Y = 550;
    public static final int INIT_SPEED = 20;
    public static final int INIT_BALL_X = 330;
    public static final int INIT_BALL_Y = 520;
    public static final int INIT_BALL_DIR_X = -1;
    public static final int INIT_BALL_DIR_Y = -2;

    /**
     * Number of rows and columns of array created from cells
     */
    public static final int CELL_ROWS = 3;
    public static final int CELL_COLUMNS = 13;

    /**
     * Initial user resources
     */
    public static final int INIT_LIVES_NUMBER = 3;
    public static final int INIT_SCORE = 0;

    /**
     * Default resource path
     */
    public static final String RESOURCE_PATH = "resources/";

}
