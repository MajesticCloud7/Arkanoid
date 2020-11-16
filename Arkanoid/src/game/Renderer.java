package game;

import objects.Brick;

import java.awt.*;

public class Renderer implements GameRenderer{
  private final GameRound gameRound;

  public Renderer(GameRound gameRound) {
    this.gameRound = gameRound;
  }

  public void renderGame() {
    printScore();
    renderPlayfield(null);
  }

  public void renderPlayfield(Graphics g) {
    for (int y = 0; y < gameRound.getPlayfield().getHeight(); y++) {
      for (int x = 0; x < gameRound.getPlayfield().getWidth(); x++) {
        if (gameRound.getVaus().isAt(x, y)) {
          renderVaus(x);
          x = increasedX(x, gameRound.getVaus().getSize());
          continue;
        }
        if (!gameRound.getBricks().isEmpty())
          for (Brick brick : gameRound.getBricks())
            if (brick.isAt(x, y)) {
              renderBrick(brick, x);
              x = increasedX(x, brick.getSize());
              break;
            }
        if (gameRound.getBall().isAt(x, y)) {
          System.out.print("o  ");
          continue;
        }
        if (gameRound.getBricks().isEmpty()) {
          if (gameRound.getPlayfield().isEmpty(x, y)) System.out.print("   ");
          if (gameRound.getPlayfield().isWall(x, y)) System.out.print("*  ");
        } else if (!gameRound.isBrickAt(gameRound, x, y)) {
          if (gameRound.getPlayfield().isEmpty(x, y)) System.out.print("   ");
          if (gameRound.getPlayfield().isWall(x, y)) System.out.print("*  ");
        }
      }
      System.out.println();
    }
  }

  public void printScore() {
    System.out.println("SCORE: " + gameRound.getScore());
  }

  public void printResult() {
    System.out.println("\n" + gameRound.getResult());
  }

  public void renderVaus(int x) {
    for (int i = x; i < x + gameRound.getVaus().getSize(); i++) System.out.print("T  ");
  }

  public void renderBrick(Brick brick, int x) {
    if (brick.isSilver()) for (int i = x; i < x + brick.getSize(); i++) System.out.print("@  ");
    else for (int i = x; i < x + brick.getSize(); i++) System.out.print("#  ");
  }

  public int increasedX(int x, int objectSize) {
    return x + objectSize - 1;
  }
}
