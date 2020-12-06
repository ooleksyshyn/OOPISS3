import 'package:flutter/cupertino.dart';
import 'dart:math';

import 'labyrinth_cell.dart';

class Point {
  int i;
  int j;
  Point({this.i, this.j});
}

class MazeGenerator {
  List<List<LabyrinthCell>> result;

  void eraseEdges(Point prev, Point next) {
    int verticalDiff = prev.i - next.i;
    int horizontalDiff = prev.j - next.j;

    if (horizontalDiff == 1) {
      result[prev.i][prev.j].hasLeftBorder = false;
      result[next.i][next.j].hasRightBorder = false;
    } else if (horizontalDiff == -1) {
      result[prev.i][prev.j].hasRightBorder = false;
      result[next.i][next.j].hasLeftBorder = false;
    }

    if (verticalDiff == 1) {
      result[prev.i][prev.j].hasTopBorder = false;
      result[next.i][next.j].hasBottomBorder = false;
    } else if (verticalDiff == -1) {
      result[prev.i][prev.j].hasBottomBorder = false;
      result[next.i][next.j].hasTopBorder = false;
    }
  }

  void generate(int nRows, int nColumns) {
    List<List<bool>> visited = List.generate(nRows,
            (i) => List.generate(nColumns, (j) => false)
    );

    result = List.generate(nRows,
            (i) => List.generate(nColumns, (j) => LabyrinthCell())
    );

    var stack = new List<Point>();

    visited[0][0] = true;
    stack.add(new Point(i: 0, j: 0));

    while (stack.isNotEmpty) {
      List<Point> possibleNextPoints = new List<Point>();

      Point last = stack.last;

      if (last.i > 0 && !visited[last.i - 1][last.j]) {
        possibleNextPoints.add(new Point(i: last.i - 1, j: last.j));
      }

      if (last.j > 0 && !visited[last.i][last.j - 1]) {
        possibleNextPoints.add(new Point(i: last.i, j: last.j - 1));
      }

      if (last.i < nRows - 1 && !visited[last.i + 1][last.j]) {
        possibleNextPoints.add(new Point(i: last.i + 1, j: last.j));
      }

      if (last.j < nColumns - 1 && !visited[last.i][last.j + 1]) {
        possibleNextPoints.add(new Point(i: last.i, j: last.j + 1));
      }

      if (possibleNextPoints.isEmpty) {
        stack.removeLast();
      } else {
        final random = new Random();

        int size = possibleNextPoints.length;

        Point nextPoint = possibleNextPoints[random.nextInt(size)];

        stack.add(nextPoint);
        visited[nextPoint.i][nextPoint.j] = true;

        eraseEdges(last, nextPoint);
      }
    }
  }
}