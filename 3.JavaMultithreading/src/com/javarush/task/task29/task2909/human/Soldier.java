package com.javarush.task.task29.task2909.human;

public class Soldier extends Human implements Alive{


    public Soldier(String name, int age) {
        super(name, age);
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void live() {
            fight();
    }

    public void fight() {
    }

}
