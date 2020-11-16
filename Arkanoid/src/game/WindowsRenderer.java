package game;

import objects.Brick;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class WindowsRenderer implements GameRenderer {
  private final GameRound gameRound;
  private final BufferedImage backgroundImage;
  private final BufferedImage wallImage;
  private final BufferedImage vausImage;
  private final BufferedImage ballImage;
  private final BufferedImage silverBrickImage;
  private final BufferedImage whiteBrickImage;

  public WindowsRenderer(GameRound gameRound) throws IOException {
    this.gameRound = gameRound;
    backgroundImage = ImageIO.read(new FileInputStream("img/background.png"));
    wallImage = ImageIO.read(new FileInputStream("img/wall.png"));
    vausImage = ImageIO.read(new FileInputStream("img/vaus.png"));
    ballImage = ImageIO.read(new FileInputStream("img/ball.png"));
    silverBrickImage = ImageIO.read(new FileInputStream("img/silver_brick.png"));
    whiteBrickImage = ImageIO.read(new FileInputStream("img/white_brick.png"));
  }

  public void renderPlayfield(Graphics g) {
    drawScore(g);
    drawBackground(g);
    drawVaus(g);
    drawBall(g);
    drawBricks(g);
    drawWalls(g);
  }

  public void renderResult(Graphics g) {
    Font font = new Font("Monospaced", Font.BOLD, 32);
    g.setFont(font);
    FontMetrics fontMetrics = g.getFontMetrics(font);

    drawResultBackground(g, fontMetrics);
    drawResultString(g, fontMetrics);
  }

  public void drawResultBackground(Graphics g, FontMetrics fontMetrics) {
    g.setColor(Color.BLACK);
    g.fillRect(
        (gameRound.getPlayfield().getWidth() * 16 - fontMetrics.stringWidth(gameRound.getResult()))
                / 2
            + 8,
        (gameRound.getPlayfield().getWidth() * 16 - fontMetrics.getHeight()) / 2 + 44,
        fontMetrics.stringWidth(gameRound.getResult()),
        32);
  }

  public void drawResultString(Graphics g, FontMetrics fontMetrics) {
    g.setColor(Color.YELLOW);
    g.drawString(
        gameRound.getResult(),
        (gameRound.getPlayfield().getWidth() * 16 - fontMetrics.stringWidth(gameRound.getResult()))
                / 2
            + 8,
        gameRound.getPlayfield().getWidth() * 8 + 48);
  }

  public void drawScore(Graphics g) {
    g.fillRect(8, 32, 464, 16);
    g.setFont(new Font("Monospaced", Font.BOLD, 16));
    g.setColor(Color.YELLOW);
    g.drawString("SCORE: " + gameRound.getScore(), 24, 45);
  }

  public void drawBackground(Graphics g) {
    g.drawImage(backgroundImage, 24, 64, 416, 464, null);
  }

  public void drawVaus(Graphics g) {
    g.drawImage(
        vausImage,
        gameRound.getVaus().getPosition().getX() * 16 + 8,
        gameRound.getVaus().getPosition().getY() * 16 + 48,
        64,
        16,
        null);
  }

  public void drawBall(Graphics g) {
    g.drawImage(
        ballImage,
        gameRound.getBall().getPosition().getX() * 16 + 8,
        gameRound.getBall().getPosition().getY() * 16 + 48,
        16,
        16,
        null);
  }

  public void drawBricks(Graphics g) {
    for (Brick brick : gameRound.getBricks())
      if (brick.isSilver())
        g.drawImage(
            silverBrickImage,
            brick.getStartingPosition().getX() * 16 + 8,
            brick.getStartingPosition().getY() * 16 + 48,
            32,
            16,
            null);
      else
        g.drawImage(
            whiteBrickImage,
            brick.getStartingPosition().getX() * 16 + 8,
            brick.getStartingPosition().getY() * 16 + 48,
            32,
            16,
            null);
  }

  public void drawWalls(Graphics g) {
    for (int y = 0; y < gameRound.getPlayfield().getHeight(); y++)
      for (int x = 0; x < gameRound.getPlayfield().getWidth(); x++)
        if (gameRound.getPlayfield().isWall(x, y))
          g.drawImage(wallImage, x * 16 + 8, y * 16 + 48, 16, 16, null);
  }
}
