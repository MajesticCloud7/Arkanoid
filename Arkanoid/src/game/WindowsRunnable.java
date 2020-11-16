package game;

import objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class WindowsRunnable extends JFrame implements ActionListener {
  private final GameRules gameRules;
  private final WindowsRenderer windowsRenderer;
  private final KeyAdapter keyAdapter;
  private final Timer timer;

  public WindowsRunnable() throws IOException {
    Playfield playfield = new Playfield();
    java.util.List<Brick> bricks = new ArrayList<>();
    Vaus vaus = new Vaus(new Position(12, 29), 4);
    Ball ball = new Ball(new Position(14, 28));
    GameRound gameRound = new GameRound(playfield, bricks, vaus, ball);
    gameRules = new GameRules(gameRound);
    windowsRenderer = new WindowsRenderer(gameRound);
    gameRound.createBricks(5, 8);

    setTitle("Arkanoid");
    setPreferredSize(new Dimension(464, 536));
    pack();
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    keyAdapter =
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            char key = e.getKeyChar();
            gameRules.processInput(key);
            repaint();
          }
        };
    addKeyListener(keyAdapter);

    timer = new Timer(200, this);
    timer.start();
  }

  public static void startGame() throws InvocationTargetException, InterruptedException {
    final Runnable runGame;
    runGame =
        () -> {
          try {
            new WindowsRunnable();
          } catch (IOException e) {
            e.printStackTrace();
          }
        };
    SwingUtilities.invokeAndWait(runGame);
  }

  public void endGame() {
    repaint();
    removeKeyListener(keyAdapter);
    timer.stop();
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    windowsRenderer.renderPlayfield(g);
    if (gameRules.isGameOver()) windowsRenderer.renderResult(g);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    gameRules.moveBall();
    gameRules.removeBricks();
    repaint();
    if (gameRules.isGameOver()) endGame();
  }
}
