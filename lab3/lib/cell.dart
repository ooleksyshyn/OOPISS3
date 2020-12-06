import 'package:flutter/material.dart';

class Cell extends StatelessWidget {

  final innerColor;
  final outerColor;
  final child;

  Cell({this.innerColor, this.outerColor, this.child});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(1.0),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(10),
        child: Container(
          padding: EdgeInsets.all(4),
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
