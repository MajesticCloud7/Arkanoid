package objects;

public class Vaus extends MovingObject {
  private final int size;

  public Vaus(Position position, int size) {
    super(position);
    this.size = size;
  }

  public Position getEndingPosition() {
    return super.getPosition().addToX(size - 1);
  }

  public int getSize() {
    return size;
  }
}
