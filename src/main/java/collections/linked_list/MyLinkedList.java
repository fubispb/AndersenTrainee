package collections.linked_list;

import collections.array_list.MyList;

import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    private void putFirst(T t) {
        Node<T> tempFirst = first;
        first = new Node<>(null, t, tempFirst);
        if (tempFirst == null) {
            last = first;
        } else {
            tempFirst.prev = first;
        }
        size++;
    }

    private void putLast(T t) {
        Node<T> tempLast = last;
        last = new Node<>(tempLast, t, null);
        if (tempLast == null) {
            first = last;
        } else {
            tempLast.next = last;
        }
        size++;
    }

    public T getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.current;
    }

    public T getLast() {
        if (last == null) {
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
        if (t == null) {
            for (Node<T> i = first; i != null; i = i.next, index++) {
                if (i.current == null) {
                    return index;
                }
            }
        }
        for (Node<T> i = first; i != null; i = i.next, index++) {
            if (i.current.equals(t)) {
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == size) {
            putLast(t);
        }
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        Node<T> newNode = new Node<>(node.prev, t, node);
        if (node.prev == null) {
            first = newNode;
            node.prev = newNode;
            return;
        }
        Node<T> prevNode = node.prev;
        node.prev = newNode;
        prevNode.next = newNode;
    }

    @Override
    public boolean remove(T t) {



        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }

    public boolean removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        if (first.next == null) {
            first = null;
            last = null;
            size--;
            return true;
        }
        first = first.next;
        first.prev = null;
        size--;
        return true;
    }

    public boolean removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        if (last.prev == null) {
            last = null;
            first = null;
            size--;
            return true;
        }
        last = last.prev;
        last.next = null;
        size--;
        return true;
    }


    @Override
    public boolean contains(T t) {
        return false;
    }

    @Override
    public T get(int index) {
        return null;
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

    @Override
    public String toString() {
        if (size == 0) {
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node<T> i = first; i != null; i = i.next) {
            sb.append(i.current.toString());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}

