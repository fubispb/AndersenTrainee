package functional_interfaces;


@FunctionalInterface
public interface TestBiConsumer<T, R> {

    void accept(T t, R r);

}
