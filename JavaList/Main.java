public class Main {
    public static void main(String[] args) {
        MyList<Integer> arrayList = new MyArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(0, 3);
        System.out.println("ArrayList: " + arrayList);
        System.out.println("Элемент по индексу 1: " + arrayList.get(1));
        arrayList.remove(1);
        System.out.println("После удаления: " + arrayList);
    }
}
