public class Main {
    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");
        
        System.out.println(queue.dequeue()); 
        System.out.println(queue.peek());    
        System.out.println(queue.size());    
    }
}
