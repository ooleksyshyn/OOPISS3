enum CellContent {
  NONE, PLAYER, GOAL
}

enum Direction {
  LEFT, TOP, RIGHT, BOTTOM
}


class LabyrinthCell {
  bool hasLeftBorder = true;
  bool hasTopBorder = true;
  bool hasRightBorder = true;
  bool hasBottomBorder = true;

  bool allowsMove(Direction direction) {
    switch (direction) {
      case Direction.LEFT: {
        return !hasLeftBorder;
      }
      case Direction.RIGHT: {
        return !hasRightBorder;
      }
      case Direction.BOTTOM: {
        return !hasBottomBorder;
      }
      case Direction.TOP: {
        return !hasTopBorder;
      }
    }

    return true;
  }
}