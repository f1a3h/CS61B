public class LinkedListDeque<T> {
    public class Node {
        /**
         * It is a better approach to use a circular linked list rather than a bidirectional linked list.
         * However, here is an implementation of a bidirectional linked list.
         */
        public T item;
        public Node next;
        public Node last;

        public Node(T value, Node lastNode, Node nextNode) {
            item = value;
            next = nextNode;
            last = lastNode;
        }
    }

    private Node front;
    private Node back;
    private int size;

    public LinkedListDeque() {
        front = null;
        back = null;
        size = 0;
    }

    public void addFirst(T item) {
        Node node = new Node(item, null, front);

        front = node;
        if (back == null) back = node;
        size++;
    }

    public void addLast(T item) {
        Node node = new Node(item, back, null);

        back = node;
        if (front == null) front = node;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        Node node = front;
        while (node != null) {
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        T ret;

        try {
            ret = front.item;
            front = front.next;
            size--;
            if (isEmpty()) back = null;
            return ret;
        } catch (NullPointerException e) {
            System.out.println("Exception: trying to remove a node from an empty deque!");
            return null;
        }
    }

    public T removeLast() {
        T ret;

        try {
            ret = back.item;
            back = back.last;
            size--;
            if (isEmpty()) front = null;
            return ret;
        } catch (NullPointerException e) {
            System.out.println("Exception: trying to remove a node from an empty deque!");
            return null;
        }
    }

    public T get(int index) {
        if (index >= size) {
            System.out.println("Exception: the node does not exist!");
            return null;
        }

        Node node = front;
        for (int i = 0; i < index; ++i) {
            node = node.next;
        }
        return node.item;
    }

    public Node getRecursiveHelper(int index, Node node) {
        if (index == 0) return node;
        return getRecursiveHelper(index - 1, node.next);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            System.out.println("Exception: the node does not exist!");
            return null;
        }

        Node node = getRecursiveHelper(index, front);
        return node.item;
    }

    public int size() {
        return size;
    }
}
