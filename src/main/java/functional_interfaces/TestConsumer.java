package functional_interfaces;


@FunctionalInterface
public interface TestConsumer<T> {

    void accept(T t);

}
