package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception {
            //init wheels here
            if (Wheel.values().length != loadWheelNamesFromDB().length) throw new Exception();
            this.wheels = new ArrayList<>();
            for (int i = 0; i < Wheel.values().length; i++) {
                try {
                    Wheel wheel = Wheel.valueOf(loadWheelNamesFromDB()[i]);
                    this.wheels.add(wheel);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {

    }
}
