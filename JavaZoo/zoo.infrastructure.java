package zoo.infrastructure;

import zoo.animals.Animal;

public class Enclosure {
    private String type;  // "Savanna", "Jungle"
    private Animal[] animals;

    public void addAnimal(Animal animal) { /* Добавление */ }
}

package zoo.core;

import zoo.infrastructure.Enclosure;
import zoo.staff.*;
import zoo.animals.*;
import java.util.List;

public class Zoo {
    private List<Enclosure> enclosures;
    private List<Zookeeper> staff;
    private double revenue;

    public void open() { /* Открытие зоопарка */ }
    public void addAnimal(Animal animal, Enclosure enclosure) { /* Логика */ }
}
