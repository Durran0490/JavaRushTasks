package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.nio.file.Watchable;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet = new Tablet(1);
        Cook amigo = new Cook("Amigo");
        tablet.addObserver(amigo);
        Waiter waiter = new Waiter();
        amigo.addObserver(waiter);

        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
    }
}
