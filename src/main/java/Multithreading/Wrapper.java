package Multithreading;

import lombok.Data;

@Data
public class Wrapper {

    public static volatile int commonInt = 0;

    public synchronized void increment() {
        long start = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " entered Wrapper increment() method at " + start);
        System.out.println("commonInt before is: " + commonInt);
        for (int i = 0; i < 100000; i++) {
            commonInt++;
        }
        System.out.println("commonInt after is: " + commonInt);
        System.out.println(Thread.currentThread().getName() + " exit Wrapper increment() method at " +
                System.currentTimeMillis() + " spend " + (System.currentTimeMillis() - start) + "ms");
    }


}
