package zoo.staff;

import zoo.animals.Animal;
import zoo.core.Careable;

public abstract class Person {
    protected String name;
    protected String role;

    public abstract void work();
}

package zoo.staff;

import zoo.animals.*;

public class Zookeeper extends Person implements Careable {
    public Zookeeper(String name) {
        this.name = name;
        this.role = "Zookeeper";
    }

    @Override
    public void work() {
        // Кормит и ухаживает за животными
    }
}

package zoo.visitors;

import zoo.core.*;

public class Visitor extends Person implements Interactable {
    private int money;

    public void buyTicket(double price) { /* Логика покупки */ }
}
