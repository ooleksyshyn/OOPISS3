import 'package:flutter_test/flutter_test.dart';
import 'package:lab3/labyrinth.dart';
import 'package:lab3/labyrinth_cell.dart';
import 'package:lab3/main.dart';

void main() {
  test('Test LabyrinthCell class',() {

    var cell1 = new LabyrinthCell();

    expect(cell1.hasBottomBorder, true);
    expect(cell1.hasTopBorder, true);
    expect(cell1.hasLeftBorder, true);
    expect(cell1.hasRightBorder, true);
    expect(cell1.allowsMove(Direction.BOTTOM), false);
    expect(cell1.allowsMove(Direction.TOP), false);
    expect(cell1.allowsMove(Direction.LEFT), false);
    expect(cell1.allowsMove(Direction.RIGHT), false);

    cell1.hasBottomBorder = false;

    expect(cell1.allowsMove(Direction.BOTTOM), true);

    cell1.hasRightBorder = false;

    expect(cell1.allowsMove(Direction.RIGHT), true);

    cell1.hasTopBorder = false;

    expect(cell1.allowsMove(Direction.TOP), true);

    cell1.hasLeftBorder = false;

    expect(cell1.allowsMove(Direction.LEFT), true);
  });
}