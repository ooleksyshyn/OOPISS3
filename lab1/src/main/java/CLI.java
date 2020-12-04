package main.java;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import main.java.ammunition.*;

public class CLI {

    private AmmunitionItem[] items;
    private Knight knight;

    public CLI (AmmunitionItem[] items, Knight knight) {
        this.items = items;
        this.knight = knight;
    }

    private void printAmmunition (List<AmmunitionItem> ammunitionItems) {
        System.out.println();
        for (var ammunitionItem : ammunitionItems) {
            System.out.println(ammunitionItem.toString());
        }
        System.out.println();
    }

    private void printAmmunition (AmmunitionItem ammunitionItem) {
        System.out.println();
        System.out.println(ammunitionItem.toString());
        System.out.println();
    }

    private void printHelp () {
         System.out.println(
                "list - output all available ammunition items\n" +
                "sort - sort items by weight\n" +
                "find a b - output one items with price in range from a to b\n" +
                "knight - output all items equipped by a knight\n" +
                "cost - output total cost of knight`s items\n" +
                "equip n - equip nth item\n" +
                "help - output this message\n" +
                "exit\n\n"
        );
    }

    public void run()  {

        printHelp();
        var sc = new Scanner(System.in);

        while (true) {
            String query = sc.nextLine();
            var splitted = query.split(" ");
            switch (splitted[0]) {
                case "list": {
                    printAmmunition(Arrays.asList(this.items));
                    break;
                }
                case "sort": {
                    AmmunitionSorter.sortByWeight(Arrays.asList(this.items));
                    break;
                }
                case "find": {
                    if (splitted.length != 3) {
                        continue;
                    }
                    double start;
                    double end;
                    try {
                        start = Double.parseDouble(splitted[1]);
                        end = Double.parseDouble(splitted[2]);
                    }
                    catch (Throwable er) {
                        continue;
                    }
                    printAmmunition(AmmunitionFinder.find(Arrays.asList(this.items), start, end));
                    break;
                }
                case "knight": {
                    printAmmunition(knight.getEquippedItems());
                    break;
                }
                case "power": {
                    System.out.println(knight.equippedWeight());
                    break;
                }
                case "add": {
                    if (splitted.length != 2) {
                        continue;
                    }
                    int n = -1;
                    try {
                        n = Integer.parseInt(splitted[1]);
                    }
                    catch (Exception err) {
                        System.out.println(err.toString());
                    }
                    try {
                        knight.equipItem(this.items[n]);
                    }
                    catch (Exception err) {
                        System.out.println(err.toString());
                    }

                    break;
                }
                case "help": {
                    printHelp();
                    break;
                }
                case "exit": {
                    return;
                }
                default:
            }
        }
    }
}
