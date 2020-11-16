package objects;

public class Ball extends MovingObject {
  private int dx = 1;
  private int dy = -1;
  private boolean isReleased = false;

  public Ball(Position position) {
    super(position);
  }

  public boolean isReleased() {
    return isReleased;
  }

  public boolean isOutOfPlayfield(Playfield playfield) {
    return playfield.isBottom(super.getPosition().getY());
  }

  public void setReleased(boolean released) {
    this.isReleased = released;
  }

  public Position getNextPosition() {
    return super.getPosition().addToX(dx).addToY(dy);
  }

  public boolean isGoingDown() {
    return dy == 1;
  }

  public boolean isGoingLeft() {
    return dx == -1;
  }

  public void move() {
    super.getPosition().setX(super.getPosition().getX() + dx);
    super.getPosition().setY(super.getPosition().getY() + dy);
  }

  public void invertDx() {
    dx *= -1;
  }

  public void invertDy() {
    dy *= -1;
  }
}
