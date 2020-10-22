package collections.array_list;


interface MyList<T> {

    int size();

    boolean add(T t);

    void add(int index, T t);

    boolean remove(T t);

    boolean contains(T t);

    T get(int index);
}
