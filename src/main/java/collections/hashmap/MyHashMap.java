package collections.hashmap;

import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final float loadFactor;
    private int size = 0;
    private int currentCapacity;
    private int bucketsCount;
    private Node<K, V>[] table;


    public MyHashMap(float loadFactor) {
        this.currentCapacity = INITIAL_CAPACITY;
        this.loadFactor = loadFactor;
    }

    public MyHashMap() {
        this.currentCapacity = INITIAL_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public void put(K key, V value) {
        checkCapacity();
        Node<K, V> newNode = createNewNode(hashKey(key), key, value);
        insertIntoMap(newNode);
    }

    public V get(K key) {
        int index = getIndexInTableByKey(key);
        if (Objects.isNull(table[index])) {
            return null;
        }
        Node<K, V> temp = table[index];
        do {
            if (checkKeyHashAndEquals(temp.key, key)) {
                return temp.value;
            }
            if (Objects.nonNull(temp.next)) {
                temp = temp.next;
            }
        }
        while (Objects.nonNull(temp.next));
        if (checkKeyHashAndEquals(temp.key, key)) {
            return temp.value;
        }
        return null;
    }

    public void clear() {
        size = 0;
        bucketsCount = 0;
        currentCapacity = INITIAL_CAPACITY;
        table = (Node<K, V>[]) new Node[currentCapacity];

    }

    //True if Node deleted by key or false if do nothing
    public boolean remove(K key) {
        int index = getIndexInTableByKey(key);
        if (Objects.isNull(table[index])) {
            return false;
        }
        Node<K, V> temp, prev, next;
        temp = prev = table[index];
        if (Objects.isNull(temp.next) && checkKeyHashAndEquals(temp.key, key)) {
            table[index] = null;
            size--;
            bucketsCount--;
            return true;
        }
        int countOfLinkedNodes = 1;
        while (Objects.nonNull(temp.next)) {
            if ((countOfLinkedNodes == 1) && checkKeyHashAndEquals(temp.key, key)) {
                table[index] = temp.next;
                size--;
                return true;
            }
            if (checkKeyHashAndEquals(temp.key, key)) {
                prev.next = temp.next;
                size--;
                return true;
            }
            temp = temp.next;
            if (countOfLinkedNodes > 1) {
                prev = prev.next;
            }
            countOfLinkedNodes++;
        }
        if (checkKeyHashAndEquals(temp.key, key)) {
            prev.next = null;
            size--;
            return true;
        }
        return false;
    }

    public boolean containsKey(K k) {
        return get(k) != null;
    }

    public boolean containsValue(V v) {
        for (int i = 0; i < table.length; i++) {
            if (Objects.isNull(table[i])) {
                continue;
            }
            if (Objects.isNull(table[i].next)) {
                if(Objects.equals(table[i].value, v)) {
                    return true;
                }
                continue;
            }
            Node<K, V> temp = table[i];
            do {
                if (Objects.equals(temp.value, v)) {
                    return true;
                }
                temp = temp.next;
            }
            while (Objects.nonNull(temp.next));
            if (Objects.equals(temp.value, v)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public int getBucketsCount() {
        return bucketsCount;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    private void checkCapacity() {
        if (Objects.isNull(table)) {
            table = (Node<K, V>[]) new Node[currentCapacity];
        }
        if (bucketsCount >= (currentCapacity * loadFactor)) {
            grow();
        }
    }

    private int getIndexInTableByKey(K key) {
        return (currentCapacity - 1) & Objects.hashCode(key);
    }

    private void grow() {
        currentCapacity = currentCapacity << 1;
        Node<K, V>[] oldTable = table;
        table = (Node<K, V>[]) new Node[currentCapacity];
        size = 0;
        bucketsCount = 0;
        Node<K, V> tempNode, growTemp;
        for (int i = 0; i < oldTable.length; i++) {
            if (Objects.isNull(oldTable[i])) {
                continue;
            }
            if (Objects.isNull(oldTable[i].next)) {
                insertIntoMap(oldTable[i]);
                continue;
            }
            tempNode = oldTable[i];
            while (Objects.nonNull(tempNode.next)) {
                growTemp = tempNode.next;
                tempNode.next = null;
                insertIntoMap(tempNode);
                tempNode = growTemp;
            }
            insertIntoMap(tempNode);
        }
    }

    private void insertIntoMap(Node<K, V> newNode) {
        int index = getIndexInTableByKey(newNode.key);
        if (size == 0 || Objects.isNull(table[index])) {
            table[index] = newNode;
            size++;
            bucketsCount++;
            return;
        }
        if (checkKeyHashAndEquals(table[index].key, newNode.key)) {
            table[index].value = newNode.value;
            return;
        }
        Node<K, V> temp = table[index];
        while (Objects.nonNull(temp.next)) {
            if (checkKeyHashAndEquals(temp.key, newNode.key)) {
                temp.value = newNode.value;
                return;
            }
            temp = temp.next;

        }
        temp = table[index];
        while (Objects.nonNull(temp.next)) {
            temp = temp.next;
        }
        temp.next = newNode;
        size++;
    }

    private boolean checkKeyHashAndEquals(K key1, K key2) {
        return ((Objects.hashCode(key1) == Objects.hashCode(key2) &&
                key1 == key2) || (Objects.equals(key1, key2)));
    }

    private Node<K, V> createNewNode(int hash, K key, V value) {
        return new Node<>(hash, key, value, null);
    }

    private int hashKey(K key) {
        if (Objects.isNull(key)) {
            return 0;
        }
        return Objects.hashCode(key);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < table.length; i++) {
            if (Objects.isNull(table[i])) {
                continue;
            }
            if (Objects.nonNull(table[i].next)) {
                sb.append(table[i].toString()).append(", ");
                Node<K, V> temp = table[i];
                while (Objects.nonNull(temp.next)) {
                    sb.append(temp.next.toString()).append(", ");
                    temp = temp.next;
                }
            } else sb.append(table[i].toString()).append(", ");

        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyHashMap<?, ?> myHashMap = (MyHashMap<?, ?>) o;
        return Arrays.equals(table, myHashMap.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(loadFactor, size, currentCapacity, bucketsCount);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }

    private static class Node<K, V> {

        private final int hash;
        private final K key;
        private V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final String toString() {
            return key + "=" + value;
        }
    }
}
