package Multithreading;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class Test {

    private static Wrapper wrapper;
    ExecutorService fixedPool = Executors.newFixedThreadPool(4, Thread::new);
    ExecutorService singleExecutor = Executors.newSingleThreadExecutor(Thread::new);
    ExecutorService cachedPool = Executors.newCachedThreadPool(Thread::new);
    List<ExecutorService> executors = Arrays.asList(fixedPool, singleExecutor, cachedPool);


    public static void main(String[] args) {
        Test test = new Test();
        wrapper = new Wrapper();
        test.executorsCallIncrement();
        Thread t1 = new Thread(wrapper::increment);
        t1.start();
    }

    public void executorsCallIncrement() {
        executors.stream().flatMapToInt(executorService -> IntStream.range(0, 5)).forEach(i -> wrapper.increment());
        fixedPool.shutdown();
        singleExecutor.shutdown();
        cachedPool.shutdown();
    }
}
