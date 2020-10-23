package collections.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class MyPriorityQueue<T> {
    private int size;
    private Object[] queue;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    private int currentCapacity;
    private final Comparator<T> comparator;

    public MyPriorityQueue() {
        this.currentCapacity = DEFAULT_CAPACITY;
        this.queue = new Object[DEFAULT_CAPACITY];
        comparator = null;
    }

    public MyPriorityQueue(int capacity, Comparator<T> comparator) {
        if (capacity < 1) throw new IllegalArgumentException();
        this.queue = new Object[capacity];
        this.currentCapacity = capacity;
        this.comparator = comparator;
    }

    public MyPriorityQueue(Comparator<T> comparator) {
        this.currentCapacity = DEFAULT_CAPACITY;
        this.queue = new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;

    }

    public MyPriorityQueue(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException();
        this.queue = new Object[capacity];
        this.currentCapacity = capacity;
        comparator = null;
    }

    public void offer(T t) {
        if (size == 0) {
            queue[0] = t;
            size++;
            return;
        }
        checkCapacity();
        if (Objects.isNull(comparator)) {
            addWithoutUserComparator(t);
        } else {
            addWithUserComparator(t);
        }
    }

    public T poll() {
        return null;
    }

    public T peek() {
        return null;
    }

    public int size(){
        return size;
    }

    private void checkCapacity() {
        if (size >= currentCapacity) {
            grow();
        }
    }

    private void grow() {
        if (currentCapacity == MAX_CAPACITY) {
            throw new OutOfMemoryError();
        }
        int newCapacity = currentCapacity + ((currentCapacity > 64 ? (currentCapacity + 2) : (currentCapacity >> 1)));
        if (currentCapacity < 0) {
            newCapacity = MAX_CAPACITY;
        }
        queue = Arrays.copyOf(queue, newCapacity);
        currentCapacity = newCapacity;
    }

    private void addWithUserComparator(T t) {
        //TODO
        //T temp = t;
        //int currentIndex = 0;
        //for (int i = 0; i < size; i++) {
        //    if (comparator.compare(t, (T) queue[i]) >= 0) {
        //        queue[]
        //        size++;
        //    }
        //}
        //System.arraycopy(queue, index, queue, index + 1, size - index);
        //queue[index] = t;
        //size++;
    }

    private void addWithoutUserComparator(T t) {
        queue[size] = t;
        size++;
        Arrays.sort(queue, 0, size);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (Objects.isNull(queue[i])) {
                sb.append("null, ");
            } else {
                sb.append(queue[i].toString()).append(", ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }


}
