package collections.array_list;


public interface MyList<T> {

    int size();

    boolean add(T t);

    void add(int index, T t);

    boolean remove(T t);

    boolean remove(int index);

    boolean contains(T t);

    T get(int index);
}
