import java.util.NoSuchElementException;

public class Queue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Queue() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Добавляет элемент в конец очереди.
     * @param element элемент для добавления
     */
    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    /**
     * Удаляет и возвращает элемент из начала очереди.
     * @return первый элемент очереди
     * @throws NoSuchElementException если очередь пуста
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }

    /**
     * Возвращает первый элемент очереди без удаления.
     * @return первый элемент очереди
     * @throws NoSuchElementException если очередь пуста
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return head.data;
    }

    /**
     * Проверяет, пуста ли очередь.
     * @return true если очередь пуста, иначе false
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Возвращает текущий размер очереди.
     * @return количество элементов в очереди
     */
    public int size() {
        return size;
    }
}
