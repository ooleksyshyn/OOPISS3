import 'package:flutter/material.dart';

enum CellContent {
  NONE, BARRIER, PLAYER, GOAL
}

class Labyrinth {
  List<List<bool>> barriers;
  int playerX = 1;
  int playerY = 1;
  static int goalX = nRows - 2;
  static int goalY = nColumns - 2;
  static int nColumns = 12;
  static int nRows = 16;
  static int numberOfSquares = nColumns * nRows;

  void init() {
    barriers = List.generate(nRows,
        (row) => List.generate(nColumns,
            (column) => (row == 0 || column == 0 || row == nRows - 1 || column == nColumns - 1)
        )
    );
  }

  void createLabyrinth() {
    playerX = 1;
    playerY = 1;
    init();
  }

  bool isAtFinish() {
    return playerX == goalX && playerY == goalY;
  }

  bool isBarrier(int x, int y) {
    return barriers[x][y];
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

    if (barriers[row][column]) {
      return CellContent.BARRIER;
    }

    return CellContent.NONE;
  }
}