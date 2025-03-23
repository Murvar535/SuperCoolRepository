import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {
    private Node root;

    private class Node {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public void insert(T data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node current, T data) {
        if (current == null) {
            return new Node(data);
        }
        int compareResult = data.compareTo(current.data);
        if (compareResult < 0) {
            current.left = insertNode(current.left, data);
        } else {
            current.right = insertNode(current.right, data);
        }
        return current;
    }

    public boolean contains(T data) {
        return containsNode(root, data);
    }

    private boolean containsNode(Node current, T data) {
        if (current == null) {
            return false;
        }
        int compareResult = data.compareTo(current.data);
        if (compareResult == 0) {
            return true;
        } else if (compareResult < 0) {
            return containsNode(current.left, data);
        } else {
            return containsNode(current.right, data);
        }
    }

    public void delete(T data) {
        root = deleteNode(root, data);
    }

    private Node deleteNode(Node current, T data) {
        if (current == null) {
            return null;
        }
        int compareResult = data.compareTo(current.data);
        if (compareResult < 0) {
            current.left = deleteNode(current.left, data);
        } else if (compareResult > 0) {
            current.right = deleteNode(current.right, data);
        } else {
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }
            current.data = findMinValue(current.right);
            current.right = deleteNode(current.right, current.data);
        }
        return current;
    }

    private T findMinValue(Node node) {
        T minValue = node.data;
        while (node.left != null) {
            minValue = node.left.data;
            node = node.left;
        }
        return minValue;
    }

    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private void inOrderTraversal(Node node, List<T> result) {
        if (node != null) {
            inOrderTraversal(node.left, result);
            result.add(node.data);
            inOrderTraversal(node.right, result);
        }
    }

    public List<T> preOrder() {
        List<T> result = new ArrayList<>();
        preOrderTraversal(root, result);
        return result;
    }

    private void preOrderTraversal(Node node, List<T> result) {
        if (node != null) {
            result.add(node.data);
            preOrderTraversal(node.left, result);
            preOrderTraversal(node.right, result);
        }
    }

    public List<T> postOrder() {
        List<T> result = new ArrayList<>();
        postOrderTraversal(root, result);
        return result;
    }

    private void postOrderTraversal(Node node, List<T> result) {
        if (node != null) {
            postOrderTraversal(node.left, result);
            postOrderTraversal(node.right, result);
            result.add(node.data);
        }
    }


    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        System.out.println("In-order: " + tree.inOrder());
        System.out.println("Pre-order: " + tree.preOrder());
        System.out.println("Post-order: " + tree.postOrder());

        System.out.println("Contains 4: " + tree.contains(4));
        System.out.println("Contains 9: " + tree.contains(9));

        tree.delete(7);
        System.out.println("After deleting 7: " + tree.inOrder());
    }
}
