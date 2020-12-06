import 'package:flutter_test/flutter_test.dart';
import 'package:lab3/labyrinth.dart';
import 'package:lab3/main.dart';

void main() {
  test('Test labyrinth creation and initialization',() {

    // static fields
    expect(Labyrinth.nRows, 16);
    expect(Labyrinth.nColumns, 12);
    expect(Labyrinth.goalX, Labyrinth.nRows - 1);
    expect(Labyrinth.goalY, Labyrinth.nColumns - 1);
    expect(Labyrinth.numberOfSquares, Labyrinth.nColumns * Labyrinth.nRows);

    var labyrinth = new Labyrinth();
    expect(labyrinth.playerY, 0);
    expect(labyrinth.playerX, 0);

    expect(labyrinth.barriers.length, Labyrinth.nRows);
    expect(labyrinth.barriers[0].length, Labyrinth.nColumns);

    for (int i = 0; i < Labyrinth.nRows; ++i) {
      for (int j = 0; j < Labyrinth.nColumns; ++j) {
        expect(labyrinth.barriers[i][j].hasBottomBorder, true);
        expect(labyrinth.barriers[i][j].hasLeftBorder, true);
        expect(labyrinth.barriers[i][j].hasRightBorder, true);
        expect(labyrinth.barriers[i][j].hasTopBorder, true);
      }
    }
  });
}