package objects;

public abstract class MovingObject {
  private final Position position;

  public MovingObject(Position position) {
    this.position = position;
  }

  public Position getPosition() {
    return position;
  }

  public boolean isAt(int x, int y) {
    return position.getX() == x && position.getY() == y;
  }

  public void moveLeft() {
    position.setX(position.getX() - 1);
  }

  public void moveRight() {
    position.setX(position.getX() + 1);
  }
}
