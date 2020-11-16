package game;

import objects.Brick;

import java.util.ArrayList;
import java.util.List;

public class GameRules {
  private final GameRound gameRound;
  private final BallMovingRules ballMovingRules;
  private boolean gameOver = false;

  public GameRules(GameRound gameRound) {
    this.gameRound = gameRound;
    this.ballMovingRules = new BallMovingRules(gameRound);
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  public void processInput(int key) {
    switch (key) {
      case 'a':
        if (gameRound.getPlayfield().isEmpty(gameRound.getVaus().getPosition().toLeft())) {
          gameRound.getVaus().moveLeft();
          if (!gameRound.getBall().isReleased()) gameRound.getBall().moveLeft();
        }
        break;
      case 'd':
        if (gameRound.getPlayfield().isEmpty(gameRound.getVaus().getEndingPosition().toRight())) {
          gameRound.getVaus().moveRight();
          if (!gameRound.getBall().isReleased()) gameRound.getBall().moveRight();
        }
        break;
      case 'w':
        if (!gameRound.getBall().isReleased()) gameRound.getBall().setReleased(true);
        break;
      case 'x':
        setGameOver(true);
        break;
    }
  }

  public void moveBall() {
    if (gameRound.getBall().isReleased()) {
      gameRound.getBall().move();
      if (ballMovingRules.ballHitBrickTopOrBottom()) gameRound.getBall().invertDy();
      else if (ballMovingRules.ballHitBrickSide()) gameRound.getBall().invertDx();
      else if (ballMovingRules.ballHitBrickCorner()) {
        gameRound.getBall().invertDx();
        gameRound.getBall().invertDy();
      }
    }
    if (ballMovingRules.ballHitVaus()) {
      gameRound.getBall().invertDy();
      if (ballMovingRules.ballHitVausEdge()) gameRound.getBall().invertDx();
    }
    if (ballMovingRules.ballHitSideWall()) gameRound.getBall().invertDx();
    if (ballMovingRules.ballHitTopWall()) gameRound.getBall().invertDy();
    if (isBallOutOfPlayfield()) setGameOver(true);
  }

  public boolean isBallOutOfPlayfield() {
    return gameRound.getBall().isOutOfPlayfield(gameRound.getPlayfield());
  }

  public void removeBricks() {
    List<Brick> toRemove = destroyedBricks();
    for (Brick brick : toRemove) gameRound.addToScore(brick.getPoints());
    gameRound.getBricks().removeAll(toRemove);
    if (gameRound.getBricks().isEmpty()) {
      setGameOver(true);
      gameRound.setResult("*~*~*VICTORY*~*~*");
    }
  }

  public List<Brick> destroyedBricks() {
    List<Brick> destroyedBricks = new ArrayList<>();
    for (Brick brick : gameRound.getBricks()) if (brick.isDestroyed()) destroyedBricks.add(brick);
    return destroyedBricks;
  }
}
