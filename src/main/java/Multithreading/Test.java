package Multithreading;


import java.util.concurrent.*;


public class Test {

    private static Wrapper wrapper;


    public static void main(String[] args) {
        wrapper = new Wrapper();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                wrapper.increment();
            }
        });
        ExecutorService fixedPool = Executors.newFixedThreadPool(4, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });

        ExecutorService singleExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });

        ExecutorService cachedPool = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });
        t1.start();
        for (int i = 0; i < 5; i++) {
            fixedPool.execute(new Runnable() {
                @Override
                public void run() {
                    wrapper.increment();
                }
            });
        }
        singleExecutor.execute(new Runnable() {
            @Override
            public void run() {
                wrapper.increment();
            }
        });
        for (int i = 0; i < 4; i++) {
            cachedPool.execute(new Runnable() {
                @Override
                public void run() {
                    wrapper.increment();
                }
            });
        }
        fixedPool.shutdown();
        singleExecutor.shutdown();
        cachedPool.shutdown();

    }

    public synchronized int increment() {
        wrapper.increment();
        return Wrapper.commonInt;
    }

}



