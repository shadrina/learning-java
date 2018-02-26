package brick_breaker.mvc.view;

import brick_breaker.mvc.controller.BrickBreakerController;
import brick_breaker.mvc.model.BrickBreakerModel;
import brick_breaker.mvc.model.IModelSubscriber;
import brick_breaker.mvc.model.state.BallState;
import brick_breaker.mvc.model.state.CellsState;
import brick_breaker.mvc.model.state.PlayerState;
import brick_breaker.mvc.model.state.UserState;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel implements IModelSubscriber, ActionListener, KeyListener, ViewCommons {

    private BrickBreakerModel model;
    private BrickBreakerController controller;

    private BufferedImage bg_img = null;
    private BufferedImage heart_img = null;
    private BufferedImage paddle_img = null;
    private BufferedImage ball_img = null;
    private BufferedImage cell_img = null;
    private BufferedImage stone_cell_img = null;

    private Timer timer;

    MainPanel(BrickBreakerModel model_, BrickBreakerController controller_) {
        try {
            bg_img = ImageIO.read(new File(RESOURCE_PATH + "dark.jpg"));
            heart_img = ImageIO.read(new File(RESOURCE_PATH + "heart.png"));
            paddle_img = ImageIO.read(new File(RESOURCE_PATH + "paddle.png"));
            ball_img = ImageIO.read(new File(RESOURCE_PATH + "ball.png"));
            cell_img = ImageIO.read(new File(RESOURCE_PATH + "cell.png"));
            stone_cell_img = ImageIO.read(new File(RESOURCE_PATH + "stone-cell.png"));
        }
        catch (IOException exc) {
            //TODO: Handle exception.
        }
        finally {
            model = model_;
            model.subscribe(this);
            controller = controller_;
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            timer = new Timer(INIT_DELAY, this);
            timer.start();
        }
    }

    public void paint(Graphics g) {
        BallState ballState = model.getBallState();
        CellsState cellsState = model.getCellsState();
        PlayerState playerState = model.getPlayerState();
        UserState userState = model.getUserState();

        // background
        g.drawImage(bg_img, 0, 0, this);

        // scores
        g.setColor(Color.white);
        g.setFont(new Font(FONT, Font.BOLD, SCORE_FONT_SIZE));
        g.drawString("" + userState.getScore(), 625, 35);

        // lives
        for (int i = 0; i < userState.getLivesNumber(); i++)
            g.drawImage(heart_img, i * HEART_WIDTH + 10, 10, this);

        // drawing map
        for (int i = 0; i < cellsState.getRowsNumber(); i++)
            for (int j = 0; j < cellsState.getColumnNumber(); j++) {
                if (cellsState.isUsual(i, j))
                    g.drawImage(cell_img, j * CELL_WIDTH + LEFT_SHIFT, i * CELL_HEIGHT + TOP_SHIFT, this);
                if (cellsState.isStone(i, j))
                    g.drawImage(stone_cell_img, j * CELL_WIDTH + LEFT_SHIFT, i * CELL_HEIGHT + TOP_SHIFT, this);
            }

        // the player
        g.drawImage(paddle_img, playerState.getX(), playerState.getY(), this);

        // the ball
        g.drawImage(ball_img, ballState.getX(), ballState.getY(), this);
        if (ballState.getY() > BOTTOM_BORDER && userState.getLivesNumber() <= 1) {
            controller.stopGame();
            g.setColor(Color.white);
            g.setFont(new Font(FONT, Font.BOLD, GAME_OVER_FONT_SIZE));
            g.drawString("Game over, score: " + userState.getScore(), 130, 260);
            g.drawString("Press Enter to restart", 180, 290);
        }
        else if (ballState.getY() > BOTTOM_BORDER) controller.startNewGameLife();

        g.dispose();
    }

    @Override
    public void modelChanged(BrickBreakerModel model_) {
        model = model_;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BallState ballState = model.getBallState();
        CellsState cellsState = model.getCellsState();
        PlayerState playerState = model.getPlayerState();
        UserState userState = model.getUserState();

        if (userState.isPlaying()) {
            if (new Rectangle(ballState.getX(), ballState.getY(), BALL_WIDTH, BALL_HEIGHT)
                    .intersects(new Rectangle(playerState.getX(), playerState.getY(), PLAYER_WIDTH, PLAYER_HEIGHT)))
                controller.repelBallY();

            BricksHandling: for (int i = 0; i < cellsState.getRowsNumber(); i++) {
                for (int j = 0; j < cellsState.getColumnNumber(); j++) {
                    if (!cellsState.isEmpty(i, j)) {
                        Rectangle brick_rect = new Rectangle(
                                j * CELL_WIDTH + LEFT_SHIFT,
                                i * CELL_HEIGHT + TOP_SHIFT,
                                CELL_WIDTH,
                                CELL_HEIGHT);
                        Rectangle ball_rect = new Rectangle(
                                ballState.getX(),
                                ballState.getY(),
                                BALL_WIDTH,
                                BALL_HEIGHT);
                        if (ball_rect.intersects(brick_rect)) {
                            controller.destroyBrickAndGetReward(i, j);
                            if (ballState.getX() + BALL_WIDTH - 1 <= brick_rect.x
                                    || ballState.getX() + 1 >= brick_rect.x + brick_rect.width)
                                controller.repelBallX();
                            else controller.repelBallY();
                            break BricksHandling;
                        }
                    }
                }
            }
            controller.moveBall();
            if (model.getBallState().getX() < 0) controller.repelBallX();
            if (model.getBallState().getY() < 0) controller.repelBallY();
            if (model.getBallState().getX() > RIGHT_BORDER - BALL_WIDTH)
                controller.repelBallX();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        PlayerState playerState = model.getPlayerState();
        UserState userState = model.getUserState();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            if (playerState.getX() >= RIGHT_BORDER - PLAYER_WIDTH) controller.changePlayerX(RIGHT_BORDER - PLAYER_WIDTH);
            else controller.movePlayer("R");
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            if (playerState.getX() <= LEFT_BORDER) controller.changePlayerX(LEFT_BORDER);
            else controller.movePlayer("L");
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !userState.isPlaying()) {
            controller.restart();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
