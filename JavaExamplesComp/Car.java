class Wheel {
    private String type;
    public Wheel(String type) { this.type = type; }
    public String getType() { return type; }
}

class Car {  // Композиция: колеса создаются внутри машины
    private Wheel[] wheels = new Wheel[4];
    
    public Car() {
        for (int i = 0; i < 4; i++) {
            wheels[i] = new Wheel("Летняя");  // Создание внутри
        }
    }
    
    public void printWheels() {
        for (Wheel w : wheels) {
            System.out.println("Колесо: " + w.getType());
        }
    }
}
