package game;

import objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Playfield playfield = new Playfield();
    List<Brick> bricks = new ArrayList<>();
    Vaus vaus = new Vaus(new Position(12, 29), 4);
    Ball ball = new Ball(new Position(14, 28));
    GameRound gameRound = new GameRound(playfield, bricks, vaus, ball);
    GameRules gameRules = new GameRules(gameRound);
    Renderer renderer = new Renderer(gameRound);
    Scanner scanner = new Scanner(System.in);

    gameRound.createBricks(5, 8);
    renderer.renderGame();

    while (!gameRules.isGameOver()) {
      int key = scanner.next().charAt(0);
      gameRules.processInput(key);
      gameRules.moveBall();
      gameRules.removeBricks();
      renderer.renderGame();
    }
    renderer.printResult();
  }
}
