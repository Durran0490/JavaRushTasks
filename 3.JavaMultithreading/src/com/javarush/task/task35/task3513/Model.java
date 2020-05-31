package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    //  Размеры поля
    private static final int FIELD_WIDTH = 4;

    //  Поле из плиточек
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

    //  Текущий счёт
    protected int score = 0;

    //  Максимальный вес плитки на игровом поле
    protected int maxTile = 2;

    //  Заполняем поле пустыми плиточками :)
    public Model() {
        resetGameTiles();
    }

    /**
     * Создаёт поле с пустыми плитками и
     * вызывает метод addTile(), который
     * изменяет значения одной из плиток на
     * 2 или 4.
     */
    protected void resetGameTiles() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }

        for (int i = 0; i < 2; i++) {
            addTile();
        }
    }

    /**
     * Меняет значение одной из пустых
     * плиток на 2 или 4, вероятность,
     * что выпадет 2 = 90%, 4 = 10%,
     * т.е соотношение 1:9, на 9 двоек
     * приходится 1 четвёрка
     */
    private void addTile() {
        List<Tile> tiles = getEmptyTiles();
        if (tiles.size() != 0) {
            Tile tile = tiles.get((int) (tiles.size() * Math.random()));
            tile.value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    /**
     * Смотрит, какие плитки пустые,
     * добавляет их в список и возвращает
     * его, для дальнейших операций.
     *
     * @return лист пустых плиток
     */
    private List<Tile> getEmptyTiles() {
        List<Tile> tiles = new ArrayList<Tile>();

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].getValue() == 0) {
                    tiles.add(gameTiles[i][j]);
                }
            }
        }

        return tiles;
    }


    /**
     * Сжатие одного ряда влево
     *
     * @param tiles
     * @return
     */
    private boolean compressTiles(Tile[] tiles) {
        boolean change = false;
        int temp;
        for (int i = tiles.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if ((tiles[j].value == 0) && (tiles[j + 1].value != 0)) {
                    temp = tiles[j + 1].value;
                    tiles[j].value = temp;
                    tiles[j + 1].value = 0;
                    change = true;
                }
            }
        }
        return change;
    }

    /**
     * Сложение клеток
     *
     * @param tiles
     * @return
     */
    private boolean mergeTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int j = 0; j < 3; j++) {
            if (tiles[j].getValue() != 0 && tiles[j].getValue() == tiles[j + 1].getValue()) {
                tiles[j].setValue(tiles[j].getValue() * 2);
                tiles[j + 1].setValue(0);
                if (tiles[j].getValue() > maxTile) maxTile = tiles[j].getValue();
                score += tiles[j].getValue();
                isChanged = true;
            }
        }

        if (isChanged) {
            Tile temp;
            for (int j = 0; j < 3; j++) {
                if (tiles[j].getValue() == 0 && tiles[j + 1].getValue() != 0) {
                    temp = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = temp;
                }
            }
        }
        compressTiles(tiles);
        return isChanged;
    }

    /**
     * rotate matrix clockwise
     */
    private void rotateMatrix() {
        Tile[][] ret = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; ++i) {
            for (int j = 0; j < FIELD_WIDTH; ++j) {
                ret[i][j] = gameTiles[FIELD_WIDTH - j - 1][i];
            }
        }
        gameTiles = ret.clone();
    }

    /**
     *
     */
    protected void left() {
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChanged = true;
            }
        }
        if (isChanged) {
            addTile();
        }
    }

    protected void up() {
        rotateMatrix();
        rotateMatrix();
        rotateMatrix();
        left();
        rotateMatrix();
    }

    protected void down() {
        rotateMatrix();
        left();
        rotateMatrix();
        rotateMatrix();
        rotateMatrix();
    }

    protected void right() {
        rotateMatrix();
        rotateMatrix();
        left();
        rotateMatrix();
        rotateMatrix();
    }

    public void setGameTiles(Tile[][] gameTiles) {
        this.gameTiles = gameTiles;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }
}