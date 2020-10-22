package collections.array_list;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<T> implements MyList<T> {
    private int size;
    private Object[] elements;
    private int currentCapacity;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;


    public MyArrayList(int capacity) {
        if (capacity >= 0) {
            this.elements = new Object[capacity];
            this.currentCapacity = capacity;
        } else throw new IllegalArgumentException("Illegal Capacity: " + capacity);

    }

    public MyArrayList() {
        int DEFAULT_CAPACITY = 10;
        this.elements = new Object[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public boolean add(T t) {
        checkCapacity();
        elements[size] = t;
        size++;
        return true;
    }

    public void add(int index, T t) {
        checkRange(index);
        checkCapacity();
        System.arraycopy(elements, index, elements, index +1, size - index);
        elements[index] = t;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(o)){
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean remove(int index) {
        int countToMove = size - index - 1;
        if (index >= 0 && index <= size) {
            System.arraycopy(elements, index + 1, elements, index, countToMove);
            elements[--size] = null;
            return true;
        }
        return false;

    }

    @Override
    public boolean contains(T t) {
        if (t == null) {
            for (int i = 0; i < size; i++) {
                if( elements[i] == null){
                    return true;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(t)){
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return (T) elements[index];
    }

    private void grow() {
        if (currentCapacity == MAX_CAPACITY) {
            throw new OutOfMemoryError();
        }
        int newCapacity = currentCapacity + (currentCapacity >> 1);
        if (currentCapacity < 0){
            newCapacity = MAX_CAPACITY;
        }
        elements = Arrays.copyOf(elements, newCapacity);
        currentCapacity = newCapacity;
    }

    private void checkRange (int index) {
        if (index < 0 && index > size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkCapacity() {
        if (currentCapacity == size) {
            grow();
        }
    }

    public int size(){
        return size;
    }

    @Override
    public String toString() {
        if(size == 0){
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<?> that = (MyArrayList<?>) o;
        return size == that.size && Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, currentCapacity);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }
}
