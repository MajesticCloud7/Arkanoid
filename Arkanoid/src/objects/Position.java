package objects;

public class Position {
  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Position addToX(int dx) {
    return new Position(x + dx, y);
  }

  public Position addToY(int dy) {
    return new Position(x, y + dy);
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Position toLeft() {
    return new Position(x - 1, y);
  }

  public Position toRight() {
    return new Position(x + 1, y);
  }

  public Position above() {
    return new Position(x, y - 1);
  }

  public Position below() {
    return new Position(x, y + 1);
  }

  public boolean equals(Position position) {
    return this.x == position.getX() && this.y == position.getY();
  }
}
