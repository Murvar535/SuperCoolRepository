package zoo.animals;

import zoo.core.*;

public abstract class Animal implements Feedable, Interactable {
    protected String name;
    protected int age;
    protected double health;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.health = 100.0;
    }

    public abstract String getSound();  // Полиморфизм звуков
}

package zoo.animals;

public class Lion extends Animal implements Careable {
    public Lion(String name, int age) { super(name, age); }

    @Override
    public String getSound() { return "Roar!"; }

    @Override
    public void feed(Food food) { /* Логика кормления */ }

    @Override
    public void performCare() { /* Уход за львом */ }

    @Override
    public void interact(Visitor visitor) { /* Взаимодействие */ }
}

package zoo.animals;

public class Monkey extends Animal {
    public Monkey(String name, int age) { super(name, age); }

    @Override
    public String getSound() { return "Ooh-ooh-ah-ah!"; }

    @Override
    public void feed(Food food) { /* Логика */ }

    @Override
    public void interact(Visitor visitor) { /* Игра с посетителем */ }
}
