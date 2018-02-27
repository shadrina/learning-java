package brick_breaker.mvc.view;

import brick_breaker.Coordinates;

public interface ViewCommons {

    /**
     * Initial delay
     */
    public static final int INIT_DELAY = 8;

    /**
     * Size parameters of main elements:
     * player (paddle),
     * ball,
     * cell,
     * heart
     */
    public static final int PLAYER_WIDTH = 110;
    public static final int PLAYER_HEIGHT = 28;
    public static final int BALL_WIDTH = 30;
    public static final int BALL_HEIGHT = 30;
    public static final int CELL_WIDTH = 40;
    public static final int CELL_HEIGHT = 40;
    public static final int HEART_WIDTH = 30;
    public static final int HEART_HEIGHT = 30;

    /**
     * Frame borders for limitation of player's and ball's movements
     */
    public static final int RIGHT_BORDER = 685;
    public static final int LEFT_BORDER = 5;
    public static final int BOTTOM_BORDER = 600;

    /**
     * Frame borders for limitation of cells's area
     */
    public static final int LEFT_SHIFT = 80;
    public static final int TOP_SHIFT = 50;

    /**
     * Default resource path
     */
    public static final String RESOURCE_PATH = "resources/";

    /**
     * Text characteristics
     */
    public static final String FONT = "Courier New";
    public static final int SCORE_FONT_SIZE = 35;
    public static final int GAME_OVER_FONT_SIZE = 35;

    /**
     * Coordinates of main elements
     */
    public static final Coordinates APP_WINDOW_COORDINATES = new Coordinates(10, 10);
    public static final int APP_WINDOW_WIDTH = 710;
    public static final int APP_WINDOW_HEIGTH = 620;
    public static final Coordinates SCORE_COORDINATES = new Coordinates(625, 35);
    public static final Coordinates GAME_OVER_COORDINATES = new Coordinates(130, 260);
    public static final Coordinates PRESS_ENTER_TO_RESTART_COORDINATES = new Coordinates(180, 290);

}
