package functional_interfaces;


@FunctionalInterface
public interface TestBiFunction<R, K, V> {

    V apply(R r, K k);

}
