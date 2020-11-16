package objects;

public class Brick {
  private final Position position;
  private int lives;
  private int points = 50;
  private final int size = 2;

  public Brick(Position position, int lives) {
    this.position = position;
    this.lives = lives;
    this.points *= lives;
  }

  public Position getStartingPosition() {
    return position;
  }

  public Position getEndingPosition() {
    return position.addToX(size - 1);
  }

  public int getSize() {
    return size;
  }

  public int getPoints() {
    return points;
  }

  public boolean isSilver() {
    return points == 100;
  }

  public void decreaseLives() {
    this.lives--;
  }

  public boolean isAt(int x, int y) {
    return position.getX() == x && position.getY() == y;
  }

  public boolean isDestroyed() {
    return lives == 0;
  }
}
