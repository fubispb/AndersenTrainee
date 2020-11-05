package functional_interfaces;


@FunctionalInterface
public interface TestFunction<T, R> {

    R apply(T t);

}
