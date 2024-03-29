public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node(T value, Node prevNode, Node nextNode) {
            item = value;
            next = nextNode;
            prev = prevNode;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public T getItem() {
            return item;
        }

        public void setNext(Node node) {
            next = node;
        }

        public void setPrev(Node node) {
            prev = node;
        }

        public void setItem(T val) {
            item = val;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.setPrev(sentinel);
        sentinel.setNext(sentinel);
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node node = new Node(item, sentinel, sentinel.getNext());
        sentinel.getNext().setPrev(node);
        sentinel.setNext(node);
        size++;
    }

    @Override
    public void addLast(T item) {
        Node node = new Node(item, sentinel.getPrev(), sentinel);
        sentinel.getPrev().setNext(node);
        sentinel.setPrev(node);
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node node = sentinel.getNext();
        while (node != null && node != sentinel) {
            System.out.print(node.getItem() + " ");
            node = node.getNext();
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node node = sentinel.getNext();
        node.getNext().setPrev(sentinel);
        sentinel.setNext(node.getNext());
        size--;
        return node.getItem();
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        Node node = sentinel.getPrev();
        node.getPrev().setNext(sentinel);
        sentinel.setPrev(node.getPrev());
        size--;
        return node.getItem();
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        Node node = sentinel;
        for (int i = 0; i <= index; ++i) {
            node = node.getNext();
        }
        return node.getItem();
    }

    private T getRecursiveHelper(int index, Node node) {
        if (index == 0) {
            return node.getItem();
        }
        return getRecursiveHelper(index - 1, node.getNext());
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.getNext());
    }
}
