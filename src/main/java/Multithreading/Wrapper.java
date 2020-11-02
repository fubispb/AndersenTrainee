package Multithreading;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Wrapper {

    public final AtomicInteger commonInt = new AtomicInteger(0);

    public void increment() {
        synchronized (commonInt) {
            long start = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " entered Wrapper increment() method at " + start);
            System.out.println("commonInt before is: " + commonInt);
            for (int i = 0; i < 100000; i++) {
                commonInt.getAndIncrement();
            }
            System.out.println("commonInt after is: " + commonInt);
            System.out.println(Thread.currentThread().getName() + " exit Wrapper increment() method at " +
                    System.currentTimeMillis() + " spend " + (System.currentTimeMillis() - start) + "ms");
        }
    }


}
