package collections.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class MyPriorityQueue<T extends Comparable<T>> {
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

    public void clear() {
        for (int i = 0; i < size; i++)
            queue[i] = null;
        size = 0;
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
        if (size == 0) {
            return null;
        }
        T temp = (T) queue[0];
        if (size >= 0) System.arraycopy(queue, 1, queue, 0, size);
        queue[size] = null;
        size--;
        return temp;
    }

    public T peek() {
        return (size == 0) ? null : (T) queue[0];
    }

    public int size(){
        return size;
    }

    public boolean contains(T t) {
        if (Objects.isNull(t)) {
            for (int i = 0; i < size; i++) {
                if (Objects.isNull(queue[i])) {
                    return true;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (Objects.equals(queue[i], t)) {
                return true;
            }
        }
        return false;
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
        T temp = t;
        int currentIndex = 0;
        for (int i = 0; i < size; i++) {
            if (comparator.compare(t, (T) queue[i]) >= 0) {
                temp = (T) queue[i];
                currentIndex = i;
                break;
            }
        }
        System.arraycopy(queue, currentIndex, queue, currentIndex + 1, size - currentIndex);
        queue[currentIndex] = temp;
        size++;
    }

    private void addWithoutUserComparator(T t) {
        queue[size] = t;
        size++;
        Arrays.sort(queue, 0, size - 1);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPriorityQueue<?> that = (MyPriorityQueue<?>) o;
        return Arrays.equals(queue, that.queue);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, currentCapacity, comparator);
        result = 31 * result + Arrays.hashCode(queue);
        return result;
    }
}
