package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        assert capacity > 0;
        this.capacity = capacity;
        fillCount = 0;
        first = capacity / 2;
        last = first;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (!isFull()) {
            fillCount += 1;
            rb[last] = x;
            last += 1;
            if (last == capacity()) {
                last = 0;
            }
        } else {
            throw new RuntimeException("Ring Buffer Overflow");
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (fillCount() > 0) {
            fillCount -= 1;
            T ret = rb[first];
            first += 1;
            if (first == capacity()) {
                first = 0;
            }
            return ret;
        } else {
            throw new RuntimeException("Ring Buffer Underflow");
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (!isEmpty()) {
            return rb[first];
        } else {
            throw new RuntimeException("Ring Buffer Is Empty");
        }
    }

    private class ArrayRingIterator implements Iterator<T> {
        private int ptr;

        public ArrayRingIterator() {
            ptr = first;
        }
        @Override
        public boolean hasNext() {
            return ptr != last;
        }

        @Override
        public T next() {
            T ret = rb[ptr];
            if (ptr == capacity()) {
                ptr = 0;
            } else {
                ptr += 1;
            }
            return ret;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayRingIterator();
    }
}
