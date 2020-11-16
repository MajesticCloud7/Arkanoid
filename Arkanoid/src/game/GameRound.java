package game;

import objects.*;

import java.util.List;

public class GameRound {
  private Playfield playfield;
  private List<Brick> bricks;
  private Vaus vaus;
  private Ball ball;
  private int score = 0;
  private String result = "*~*~*GAME~OVER*~*~*";

  public GameRound(Playfield playfield, List<Brick> bricks, Vaus vaus, Ball ball) {
    setPlayfield(playfield);
    setBricks(bricks);
    setVaus(vaus);
    setBall(ball);
  }

  public Playfield getPlayfield() {
    return playfield;
  }

  public void setPlayfield(Playfield playfield) {
    this.playfield = playfield;
  }

  public Vaus getVaus() {
    return vaus;
  }

  public void setVaus(Vaus vaus) {
    this.vaus = vaus;
  }

  public Ball getBall() {
    return ball;
  }

  public void setBall(Ball ball) {
    this.ball = ball;
  }

  public List<Brick> getBricks() {
    return bricks;
  }

  public void setBricks(List<Brick> bricks) {
    this.bricks = bricks;
  }

  public int getScore() {
    return score;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void addToScore(int points) {
    this.score += points;
  }

  public void createBricks(int startY, int endY) {
    for (int y = startY; y <= endY; y++)
      for (int x = 1; x < playfield.getWidth() - 2; x += 4)
        if (y % 2 == 0) {
          this.bricks.add(new Brick(new Position(x, y), 1));
          if (x + 2 < playfield.getWidth() - 2)
            this.bricks.add(new Brick(new Position(x + 2, y), 2));
        } else {
          this.bricks.add(new Brick(new Position(x, y), 2));
          if (x + 2 < playfield.getWidth() - 2)
            this.bricks.add(new Brick(new Position(x + 2, y), 1));
        }
  }

  public boolean isBrickAt(GameRound gameRound, int x, int y) {
    for (Brick brick : gameRound.getBricks())
      if (brick.isAt(x, y) || brick.isAt(x - 1, y)) return true;
    return false;
  }
}
