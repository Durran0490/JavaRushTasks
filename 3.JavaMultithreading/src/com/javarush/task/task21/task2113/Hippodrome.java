package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public static void main(String[] args) {
        game = new Hippodrome(new ArrayList<>());
        Horse horse1 = new Horse("Lili", 3, 0);
        Horse horse2 = new Horse("Mona", 3, 0);
        Horse horse3 = new Horse("Heli", 3, 0);
        game.horses.add(horse1);
        game.horses.add(horse2);
        game.horses.add(horse3);
        game.run();
        game.printWinner();
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void move() {
        for (Horse h : horses) {
            h.move();
        }
    }

    void print() {
        for (Horse h : horses) {
            h.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("\n");
        }
    }

    public Horse getWinner() {
        Collections.sort(horses, new Comparator<Horse>() {
            public int compare(Horse c1, Horse c2) {
                if (c1.getDistance() > c2.getDistance()) return -1;
                if (c1.getDistance() < c2.getDistance()) return 1;
                return 0;
            }});
        return horses.get(0);
    }

    public void printWinner() {
        System.out.printf("Winner is %s!\n", getWinner().getName());

    }

}
