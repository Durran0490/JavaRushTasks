package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes = ConsoleHelper.getAllDishesForOrder();

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
    }

    @Override
    public String toString() {
        if (dishes.isEmpty() || dishes == null) {
            return "";
        } else {
            return "Your order: " + dishes + " of " + tablet;
        }
    }
}
