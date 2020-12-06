// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility that Flutter provides. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import 'package:lab3/main.dart';

void main() {
  testWidgets('Play button clicked', (WidgetTester tester) async {
    // Build our app and trigger a frame.
    await tester.pumpWidget(MyApp());

    // Verify that our counter starts at 0.
    expect(find.text('PLAY'), findsOneWidget);
    expect(find.text('Score: '), findsNothing);

    // Tap the '+' icon and trigger a frame.
    await tester.tap(find.text("PLAY"));
    await tester.pump();

    // Verify that our counter has incremented.
    expect(find.text('PLAY'), findsNothing);
    expect(find.text('Score: 0'), findsOneWidget);
  });
}
