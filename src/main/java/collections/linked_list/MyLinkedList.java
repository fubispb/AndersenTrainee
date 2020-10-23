package collections.linked_list;

import collections.array_list.MyList;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyLinkedList<T> implements MyList<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    private void putFirst(T t) {
        Node<T> tempFirst = first;
        first = new Node<>(null, t, tempFirst);
        if (Objects.isNull(tempFirst)) {
            last = first;
        } else {
            tempFirst.prev = first;
        }
        size++;
    }

    private void putLast(T t) {
        Node<T> tempLast = last;
        last = new Node<>(tempLast, t, null);
        if (Objects.isNull(tempLast)) {
            first = last;
        } else {
            tempLast.next = last;
        }
        size++;
    }

    public T getFirst() {
        if (Objects.isNull(first)) {
            throw new NoSuchElementException();
        }
        return first.current;
    }

    public T getLast() {
        if (Objects.isNull(last)) {
            throw new NoSuchElementException();
        }
        return last.current;
    }

    public void addFirst(T t) {
        putFirst(t);
    }

    public void addLast(T t) {
        putLast(t);
    }

    // Return index or -1 if list does not contain the element
    public int indexOf(T t) {
        int index = 0;
        if (Objects.isNull(t)) {
            for (Node<T> i = first; Objects.nonNull(i); i = i.next, index++) {
                if (Objects.isNull(i.current)) {
                    return index;
                }
            }
        }
        for (Node<T> i = first; Objects.nonNull(i); i = i.next, index++) {
            if (Objects.equals(i.current, t)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T t) {
        putLast(t);
        return true;
    }

    @Override
    public void add(int index, T t) {
        checkRange(index);
        if (index == size) {
            putLast(t);
            return;
        }
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        Node<T> newNode = new Node<>(node.prev, t, node);
        if (Objects.isNull(node.prev)) {
            first = newNode;
            node.prev = newNode;
            size++;
            return;
        }
        Node<T> prevNode = node.prev;
        node.prev = newNode;
        prevNode.next = newNode;
        size++;
    }

    private void deleteElement(int index) {
        checkRange(index);
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        if (Objects.isNull(node.prev)) {
            removeFirst();
            return;
        }
        if (Objects.isNull(node.next)) {
            removeLast();
            return;
        }
        Node<T> nodePrev = node.prev;
        Node<T> nodeNext = node.next;
        nodePrev.next = nodeNext;
        nodeNext.prev = nodePrev;
        size--;

    }

    @Override
    public boolean remove(T t) {
        deleteElement(indexOf(t));
        return true;
    }

    @Override
    public boolean remove(int index) {
        checkRange(index);
        deleteElement(index);
        return true;
    }

    public void removeFirst() {
        if (Objects.isNull(first)) {
            throw new NoSuchElementException();
        }
        if (Objects.isNull(first.next)) {
            first = null;
            last = null;
            size--;
            return;
        }
        first = first.next;
        first.prev = null;
        size--;
    }

    public void removeLast() {
        if (Objects.isNull(last)) {
            throw new NoSuchElementException();
        }
        if (Objects.isNull(last.prev)) {
            last = null;
            first = null;
            size--;
            return;
        }
        last = last.prev;
        last.next = null;
        size--;
    }


    @Override
    public boolean contains(T t) {
        //TODO
        if (Objects.isNull(t)) {
            for (Node<T> i = first; Objects.nonNull(i); i = i.next) {
                if (Objects.isNull(i.current)) {
                    return true;
                }
            }
        }
        for (Node<T> i = first; Objects.nonNull(i); i = i.next) {
            if (Objects.equals(i.current, t)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public T get(int index) {
        checkRange(index);
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        if (Objects.isNull(node.current)) {
            return null;
        }
        return node.current;
    }

    private static class Node<T> {
        Node<T> prev;
        T current;
        Node<T> next;

        public Node(Node<T> prev, T current, Node<T> next) {
            this.prev = prev;
            this.current = current;
            this.next = next;
        }

    }

    private void checkRange(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node<T> i = first; Objects.nonNull(i); i = i.next) {
            if (Objects.isNull(i.current)) {
                sb.append("null, ");
            } else {
                sb.append(i.current.toString()).append(", ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}

