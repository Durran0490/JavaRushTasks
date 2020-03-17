package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new TestedThread1());
        threads.add(new TestedThread2());
        threads.add(new TestedThread3());
        threads.add(new ThreadMes());
        threads.add(new TestedThread5());
    }

    public static void main(String[] args) {

    }

    public static class TestedThread1 extends Thread {
        public void run() {
            while (true) {
            }
        }
    }

    public static class TestedThread2 extends Thread {
        public void run() {
            try {
                sleep(0);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class TestedThread3 extends Thread {

        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadMes extends Thread implements Message {
        public void run() {
            try {
                while (!isInterrupted()) {
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
            }
        }

        public void showWarning() {
            interrupt();
        }
    }


    public static class TestedThread5 extends Thread {

        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = "";
            int sum = 0;
                while (true) {
                    try {
                        s = reader.readLine();
                        if ("N".equals(s)) break;
                        else sum += Integer.parseInt(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(sum);
            }
    }
}