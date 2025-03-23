public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        System.out.println("Размер стека: " + stack.size()); // 3
        System.out.println("Верхний элемент: " + stack.peek()); // 30
        
        while (!stack.isEmpty()) {
            System.out.println("Извлечено: " + stack.pop());
        }
    }
}
