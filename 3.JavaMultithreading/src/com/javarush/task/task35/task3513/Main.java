package com.javarush.task.task35.task3513;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        model.setGameTiles( new Tile[][]{
                {new Tile(2), new Tile(4), new Tile(4), new Tile(8)},
                {new Tile(4), new Tile(8), new Tile(4), new Tile(0)},
                {new Tile(0), new Tile(0), new Tile(8), new Tile(0)},
                {new Tile(0), new Tile(0), new Tile(8), new Tile(0)},
        });

        printTiles(model.getGameTiles());

        model.up();

        printTiles(model.getGameTiles());

    }

    private static void printTiles(Tile[][] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                System.out.print(tiles[i][j].value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}