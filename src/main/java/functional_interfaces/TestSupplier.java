package functional_interfaces;


@FunctionalInterface
public interface TestSupplier<T> {

    T get();

    default void printSomething() {
        System.out.println("Hello from default method of Supplier!");
    }

    static void printAnotherOne() {
        System.out.println("Hello from static method of Supplier!");
    }

}
