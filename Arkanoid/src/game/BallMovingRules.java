package game;

import objects.Brick;

public class BallMovingRules {
  private GameRound gameRound;

  public BallMovingRules(GameRound gameRound) {
    this.gameRound = gameRound;
  }

  public boolean ballHitBrickTopOrBottom() {
    for (Brick brick : gameRound.getBricks())
      for (int i = 0; i < brick.getSize(); i++)
        if (gameRound.getBall().isGoingDown()
                && gameRound
                    .getBall()
                    .getPosition()
                    .below()
                    .equals(brick.getStartingPosition().addToX(i))
            || !gameRound.getBall().isGoingDown()
                && gameRound
                    .getBall()
                    .getPosition()
                    .above()
                    .equals(brick.getStartingPosition().addToX(i))) {
          brick.decreaseLives();
          return true;
        }
    return false;
  }

  public boolean ballHitBrickSide() {
    for (Brick brick : gameRound.getBricks())
      for (int i = 0; i < brick.getSize(); i++)
        if (gameRound.getBall().getPosition().toLeft().equals(brick.getStartingPosition().addToX(i))
            || gameRound
                .getBall()
                .getPosition()
                .toRight()
                .equals(brick.getStartingPosition().addToX(i))) {
          brick.decreaseLives();
          return true;
        }
    return false;
  }

  public boolean ballHitBrickCorner() {
    for (Brick brick : gameRound.getBricks())
      if (ballHitBottomLeftBrickCorner(brick)
          || ballHitBottomRightBrickCorner(brick)
          || ballHitTopLeftBrickCorner(brick)
          || ballHitTopRightBrickCorner(brick)) {
        brick.decreaseLives();
        return true;
      }
    return false;
  }

  public boolean ballHitBottomLeftBrickCorner(Brick brick) {
    return gameRound.getBall().getPosition().toRight().above().equals(brick.getStartingPosition())
        && gameRound.getBall().getNextPosition().equals(brick.getStartingPosition());
  }

  public boolean ballHitBottomRightBrickCorner(Brick brick) {
    return gameRound.getBall().getPosition().toLeft().above().equals(brick.getEndingPosition())
        && gameRound.getBall().getNextPosition().equals(brick.getEndingPosition());
  }

  public boolean ballHitTopLeftBrickCorner(Brick brick) {
    return gameRound.getBall().getPosition().toRight().below().equals(brick.getStartingPosition())
        && gameRound.getBall().getNextPosition().equals(brick.getStartingPosition());
  }

  public boolean ballHitTopRightBrickCorner(Brick brick) {
    return gameRound.getBall().getPosition().toLeft().below().equals(brick.getEndingPosition())
        && gameRound.getBall().getNextPosition().equals(brick.getEndingPosition());
  }

  public boolean ballHitVaus() {
    if (gameRound.getBall().isGoingDown())
      for (int i = 0; i < gameRound.getVaus().getSize(); i++) {
        if (gameRound
            .getBall()
            .getPosition()
            .below()
            .equals(gameRound.getVaus().getPosition().addToX(i))) return true;
      }
    return false;
  }

  public boolean ballHitVausEdge() {
    return gameRound.getBall().getPosition().below().equals(gameRound.getVaus().getPosition())
            && !gameRound.getBall().isGoingLeft()
        || gameRound.getBall().getPosition().below().equals(gameRound.getVaus().getEndingPosition())
            && gameRound.getBall().isGoingLeft();
  }

  public boolean ballHitSideWall() {
    return gameRound.getPlayfield().isWall(gameRound.getBall().getPosition().toLeft())
        || gameRound.getPlayfield().isWall(gameRound.getBall().getPosition().toRight());
  }

  public boolean ballHitTopWall() {
    return gameRound.getPlayfield().isWall(gameRound.getBall().getPosition().above());
  }
}
