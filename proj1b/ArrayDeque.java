public class ArrayDeque<T> implements Deque<T> {
    private T[] array;
    private int size;
    private int allocatedSize;
    private int front;
    private int back;

    public ArrayDeque() {
        size = 0;
        allocatedSize = 16;
        front = 8;
        back = 8;
        array = (T[]) new Object[allocatedSize];
    }

    private int plusOne(int index, int mod) {
        index %= mod;
        if (index == mod - 1) {
            return 0;
        }
        return index + 1;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return allocatedSize - 1;
        }
        return index - 1;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[allocatedSize << 1];
        int p1 = front;
        int p2 = allocatedSize;
        while (p1 != back) {
            newArray[p2] = array[p1];
            p2 = plusOne(p2, allocatedSize << 1);
            p1 = plusOne(p1, allocatedSize);
        }

        front = allocatedSize;
        back = p2;
        array = newArray;
        allocatedSize <<= 1;
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[allocatedSize >> 1];
        int p1 = front;
        int p2 = allocatedSize >> 2;
        while (p1 != back) {
            newArray[p2] = array[p1];
            p2 = plusOne(p2, allocatedSize >> 1);
            p1 = plusOne(p1, allocatedSize);
        }

        front = allocatedSize >> 2;
        back = p2;
        array = newArray;
        allocatedSize >>= 1;
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
    public void addFirst(T item) {
        if (size == allocatedSize - 1) {
            grow();
        }

        front = minusOne(front);
        array[front] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == allocatedSize - 1) {
            grow();
        }

        array[back] = item;
        back = plusOne(back, allocatedSize);
        size++;
    }

    @Override
    public T removeFirst() {
        if (allocatedSize >= 32 && (allocatedSize >> 2) >= size) {
            shrink();
        }

        if (isEmpty()) {
            return null;
        }

        T ret = array[front];
        front = plusOne(front, allocatedSize);
        size--;
        return ret;
    }

    @Override
    public T removeLast() {
        if (allocatedSize >= 32 && (allocatedSize >> 2) >= size) {
            shrink();
        }

        if (isEmpty()) {
            return null;
        }

        back = minusOne(back);
        size--;
        return array[back];
    }

    @Override
    public void printDeque() {
        int p = front;
        while (p != back) {
            System.out.print(array[p] + " ");
            p = plusOne(p, allocatedSize);
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            System.out.println("Exception: the node does not exist!");
            return null;
        }

        int p = front;
        for (int i = 0; i < index; ++i) {
            p = plusOne(p, allocatedSize);
        }
        return array[p];
    }
}
