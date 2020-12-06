import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

import 'package:lab3/main.dart';

void main() {
  testWidgets('Play button clicked', (WidgetTester tester) async {
    // Build our app and trigger a frame.
    await tester.pumpWidget(MyApp());

    expect(find.text('PLAY'), findsOneWidget);
    expect(find.text('Score: '), findsNothing);

    await tester.tap(find.text("PLAY"));
    await tester.pump();

    expect(find.text('PLAY'), findsNothing);
    expect(find.text('Score: 0'), findsOneWidget);
  });
}
