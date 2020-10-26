package collections.priority_queue;

import collections.linked_list.MyLinkedList;
import collections.queue.MyPriorityQueue;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyPriorityQueueTest {
    MyPriorityQueue<String> myQueue = new MyPriorityQueue<>();


    @BeforeEach
    void setUp() {

        myQueue.offer("10");
        myQueue.offer("9");
        myQueue.offer("8");
        myQueue.offer("7");
        myQueue.offer("6");
        myQueue.offer("5");
        myQueue.offer("4");
        myQueue.offer("3");
        myQueue.offer("2");
        assertSize(9);
    }

    @Test
    void offer() {
        myQueue.offer("111");
        assertSize(10);
        assertSuccessContains("111");
    }

    @Test
    void poll() {
        myQueue.poll();
        assertSize(8);
        assertFailContains("10");
    }

    @Test
    void peek() {
        myQueue.peek();
        assertSize(9);
        assertSuccessContains("10");
    }

    @Test
    void size() {
        myQueue.offer("1");
        myQueue.offer("0");
        myQueue.offer("99");
        myQueue.offer("98");
        assertSize(13);
    }


    public void assertSize(int size) {
        Assert.assertEquals(size, myQueue.size());
    }

    public void assertSuccessContains(String s) {
        Assert.assertTrue(myQueue.contains(s));
    }

    public void assertFailContains(String s) {
        Assert.assertFalse(myQueue.contains(s));
    }

}

