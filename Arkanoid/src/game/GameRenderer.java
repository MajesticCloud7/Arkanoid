package game;

import com.sun.istack.internal.Nullable;

import java.awt.*;

public interface GameRenderer {
  void renderPlayfield(@Nullable Graphics g);
}
