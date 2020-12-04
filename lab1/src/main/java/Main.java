package main.java;

import main.java.ammunition.*;

class Main {
    public static void main(String[] args) {
        AmmunitionItem[] allItems = {
            new Helmet(20, 12),
            new Helmet(20, 15),
            new Sword("escalibur", 20, 1000),
            new Sword("longclaw", 15, 1200),
            new Sword("ice", 30, 1800),
            new Armor("mitrile", 2, 100000),
            new Armor("silver", 50, 1500),
            new Armor("bronze", 60, 1300)
        };

        Knight knight = new Knight("Sir John", 100);

        CLI cli = new CLI(allItems, knight);

        cli.run();
    }
}