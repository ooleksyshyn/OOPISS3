import 'package:flutter/material.dart';

class CellColors {
  final Color left;
  final Color top;
  final Color right;
  final Color bottom;

  CellColors({this.left, this.top, this.right, this.bottom});
}


class Cell extends StatelessWidget {

  final innerColor;
  final outerColor;
  final child;
  final CellColors colors;

  Cell({this.innerColor, this.outerColor, this.child, this.colors});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
          border: Border(
            left: BorderSide(color: colors.left, width: 1.0),
            top: BorderSide(color: colors.top, width: 1.0),
            right: BorderSide(color: colors.right, width: 1.0),
            bottom: BorderSide(color: colors.bottom, width: 1.0),
          )
      ),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(10),
        child: Container(
          padding: EdgeInsets.all(7),
          color: outerColor,
          child: Center(
            child: ClipRRect(
              borderRadius: BorderRadius.circular(10),
              child: Container(
                color: innerColor,
                child: Center(child: child),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
