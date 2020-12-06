import 'package:flutter/material.dart';
import 'cell.dart';
import 'player.dart';
import 'labyrinth.dart';


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
      labyrinth.createLabyrinth();
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

    var move = 0;
    if (delta > 5) {
      move = 1;
    } else if (delta < -5) {
      move = -1;
    }

    if (!labyrinth.isBarrier(labyrinth.playerX + move, labyrinth.playerY)) {
      setState(() {
        labyrinth.playerX += move;
      });
    }

    checkFinish();
  }
  
  void moveHorizontal(var delta) {
    if (!isGameOn) {
      return;
    }

    var move = 0;
    if (delta > 5) {
      move = 1;
    } else if (delta < -5) {
      move = -1;
    }

    if (!labyrinth.isBarrier(labyrinth.playerX, labyrinth.playerY + move)) {
      setState(() {
        labyrinth.playerY += move;
      });
    }

    checkFinish();
  }

  Widget displayCell(BuildContext context, int index) {
    CellContent cont = labyrinth.at(index);

    switch (cont) {
      case CellContent.PLAYER: {
        return Cell(
          child: Player(),
        );
      }
      case CellContent.GOAL: {
        return Cell(
          innerColor: Colors.red,
        );
      }
      case CellContent.BARRIER: {
        return Cell(
          outerColor: Colors.blue[900],
          innerColor: Colors.blue[800],
        );
      }
      case CellContent.NONE: {
        return Cell();
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
    labyrinth.init();
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
