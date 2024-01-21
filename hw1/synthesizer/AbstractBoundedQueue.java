package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    /**
     * protected: Only subclasses of AbstractBoundedQueue and classes in the same
     * package as AbstractBoundedQueue can access this variable.
     */
    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return capacity;
    }

    public int fillCount() {
        return fillCount;
    }

    public abstract T peek();

    public abstract T dequeue();

    public abstract void enqueue(T x);
}
