import 'labyrinth_cell.dart';
import 'maze_generation_algorithm.dart';

class Labyrinth {
  int playerX = 0;
  int playerY = 0;
  static int goalX = nRows - 1;
  static int goalY = nColumns - 1;
  static int nColumns = 12;
  static int nRows = 16;
  static int numberOfSquares = nColumns * nRows;

  List<List<LabyrinthCell>> barriers = List.generate(nRows,
          (i) => List.generate(nColumns, (j) => LabyrinthCell())
  );

  void init() {
    barriers = List.generate(nRows, (index) => List.generate(nColumns, (index) => LabyrinthCell()));
  }

  List<List<LabyrinthCell>> createLabyrinth() {
    playerX = 0;
    playerY = 0;

    var generator = new MazeGenerator();
    generator.generate(nRows, nColumns);

    return generator.result;
  }

  bool isAtFinish() {
    return playerX == goalX && playerY == goalY;
  }


  CellContent at(int index) {
    int row = index ~/ nColumns;
    int column = index % nColumns;

    if (row == playerX && column == playerY) {
      return CellContent.PLAYER;
    }

    if (row == goalX && column == goalY) {
      return CellContent.GOAL;
    }

    return CellContent.NONE;
  }

  LabyrinthCell cellAt(index) {
    int row = index ~/ nColumns;
    int column = index % nColumns;

    return barriers[row][column];
  }

  LabyrinthCell currentCell() {
    return barriers[playerX][playerY];
  }

  void move(Direction dir) {
    switch (dir) {
      case Direction.LEFT: {
        --playerY;
        break;
      }
      case Direction.TOP: {
        --playerX;
        break;
      }
      case Direction.RIGHT: {
        ++playerY;
        break;
      }
      case Direction.BOTTOM: {
        ++playerX;
        break;
      }
    }
  }

  bool allowsMove(Direction dir) {
    switch(dir) {
      case Direction.TOP: return playerX > 0;
      case Direction.BOTTOM: return playerX < nRows - 1;
      case Direction.LEFT: return playerY > 0;
      case Direction.RIGHT: return playerY < nColumns - 1;
    }

    return false; // should not happen
  }
}