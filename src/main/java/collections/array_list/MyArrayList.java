package collections.array_list;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T> {
    private final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elements;
    private int currentCapacity;


    public MyArrayList(int capacity) {
        if (capacity >= 0) {
            this.elements = new Object[capacity];
            this.currentCapacity = capacity;
        } else throw new IllegalArgumentException("Illegal Capacity: " + capacity);

    }

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(T t) {
        if (currentCapacity == size) {
            grow();
        }
        //TODO
        //What if we can't grow?????
        elements[size] = t;
        size++;
        return true;
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

    public void remove(int index) {
        System.out.println("size before delete: " + size);
        int countToMove = size - index - 1;
        if (index >= 0 && index <= size) {
            System.arraycopy(elements, index + 1, elements, index, countToMove);
            elements[--size] = null;
            System.out.println("size after delete: " + size);
        }
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if( elements[i] == null){
                    return true;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)){
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

    }

    private void checkRange (int index) {
        if (index < 0 && index > size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
