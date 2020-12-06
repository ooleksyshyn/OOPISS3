import 'package:flutter/material.dart';
import 'cell.dart';
import 'player.dart';
import 'labyrinth.dart';
import 'labyrinth_cell.dart';


Color edgeColor(bool hasEdge) {
  if (hasEdge) {
    return Colors.white;
  } else {
    return Colors.black;
  }
}


class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}


class _HomePageState extends State<HomePage> {
  int score = 0;
  Labyrinth labyrinth = new Labyrinth();
  bool isGameOn = false;

  void startGame() {
    setState(() {
      labyrinth.barriers = labyrinth.createLabyrinth();
      isGameOn = true;
    });
  }

  void checkFinish() {
    if (labyrinth.isAtFinish()) {
      setState(() {
        ++score;
      });
      startGame();
    }
  }
  
  void moveVertical(var delta) {
    if (!isGameOn) {
      return;
    }

    Direction dir;
    if (delta > 5) {
      dir = Direction.BOTTOM;
    } else if (delta < -5) {
      dir = Direction.TOP;
    }

    if (labyrinth.currentCell().allowsMove(dir) && labyrinth.allowsMove(dir)) {
      setState(() {
        labyrinth.move(dir);
      });
    }

    checkFinish();
  }
  
  void moveHorizontal(var delta) {
    if (!isGameOn) {
      return;
    }

    Direction dir;
    if (delta > 5) {
      dir = Direction.RIGHT;
    } else if (delta < -5) {
      dir = Direction.LEFT;
    }

    if (labyrinth.currentCell().allowsMove(dir) && labyrinth.allowsMove(dir)) {
      setState(() {
        labyrinth.move(dir);
      });
    }

    checkFinish();
  }

  CellColors indexToColor(int index) {
    var cell = labyrinth.cellAt(index);

    return CellColors(
      left: edgeColor(cell.hasLeftBorder),
      top: edgeColor(cell.hasTopBorder),
      right: edgeColor(cell.hasRightBorder),
      bottom: edgeColor(cell.hasBottomBorder),
    );
  }

  Widget displayCell(BuildContext context, int index) {
    CellContent cont = labyrinth.at(index);

    switch (cont) {
      case CellContent.PLAYER: {
        return Cell(
          child: Player(),
          colors: indexToColor(index),
        );
      }
      case CellContent.GOAL: {
        return Cell(
          innerColor: Colors.yellow,
          colors: indexToColor(index),
        );
      }
      case CellContent.NONE: {
        return Cell(
          colors: indexToColor(index),
        );
      }
    }


    // nothing
    return Cell();
  }

  Widget toolbar() {
    if (isGameOn) {
      return Text(
        "Score: " + score.toString(),
        style: TextStyle(
          color: Colors.white,
          fontSize: 40
        ),
      );
    } else {
      return GestureDetector(
        onTap: startGame,
        child: Text("PLAY", style: TextStyle(
            color: Colors.white,
            fontSize: 40
        ),),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      body: Column(
        children: [
          Expanded(
            flex: 6,
            child: GestureDetector(
              onVerticalDragUpdate: (details) { moveVertical(details.delta.dy); },
              onHorizontalDragUpdate: (details) { moveHorizontal(details.delta.dx); },
              child: Container(
                child: GridView.builder(
                  physics: NeverScrollableScrollPhysics(),
                  itemCount: Labyrinth.numberOfSquares,
                  gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: Labyrinth.nColumns,
                  ),
                  itemBuilder: (BuildContext context, int index) {
                    return displayCell(context, index);
                  },
                ),
              ),
            ),
          ),
          Expanded(
            child: Container(
              child: toolbar(),
            ),
          ),
        ],
      ),
    );
  }
}
