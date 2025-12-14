package zoo.core;

public interface Feedable {
    void feed(Food food);
}

public interface Careable {
    void performCare();
}

public interface Interactable {
    void interact(Visitor visitor);
}
