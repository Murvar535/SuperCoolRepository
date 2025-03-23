public class Main {
    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");
        
        System.out.println(queue.dequeue()); // First
        System.out.println(queue.peek());    // Second
        System.out.println(queue.size());    // 2
    }
}
