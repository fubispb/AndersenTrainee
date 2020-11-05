package functional_interfaces;


public class Main {

    public static void main(String[] args) {

        TestPredicate<String> predicate = (x) -> x.length() == 7;
        System.out.println(predicate.test("Test"));

        TestConsumer<String> consumer = System.out::println;
        consumer.accept("Hello Consumer!");

        TestSupplier<String> supplier = () -> "Hello Supplier!";
        System.out.println(supplier.get());
        supplier.printSomething();
        TestSupplier.printAnotherOne();

        TestFunction<String, Integer> function = (x) -> x.length();
        System.out.println("Length of string is: " + function.apply("Some string"));

        int a = 5;
        int b = 15;
        TestBiConsumer<Integer, Integer> biConsumer = (x, v) -> System.out.println("Sum of integers: " + (x + v));
        biConsumer.accept(a, b);

        TestBiFunction<Integer, Integer, String> testBiFunction = (x, k) -> "Multiply of integers is: " + x * k;
        System.out.println(testBiFunction.apply(a, b));

    }

}
