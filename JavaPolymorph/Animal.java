abstract class Animal {
    public abstract void makeSound();
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Гав-гав!");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Мяу-мяу!");
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        Animal[] animals = {
            new Dog(),
            new Cat(),
            new Dog()
        };
        
        for (Animal animal : animals) {
            animal.makeSound();  // Полиморфный вызов
        }
    }
}
