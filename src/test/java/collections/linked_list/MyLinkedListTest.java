package collections.linked_list;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyLinkedListTest {
    MyLinkedList<String> myList = new MyLinkedList<>();


    @BeforeEach
    void setUp() {

        myList.add("10");
        myList.add("9");
        myList.add("8");
        myList.add("7");
        myList.add("6");
        myList.add("5");
        myList.add("4");
        myList.add("3");
        myList.add(null);
        assertSize(9);
    }

    @Test
    void getFirst() {
        assertEquals("10", 0);
    }

    @Test
    void getLast() {
        assertEquals(null, myList.size() - 1);
    }

    @Test
    void addFirst() {
        myList.addFirst("111");
        assertSize(10);
        assertEquals(myList.getFirst(), 0);
    }

    @Test
    void addLast() {
        myList.addLast("111");
        assertSize(10);
        assertEquals(myList.getLast(), myList.size() - 1);
    }

    @Test
    void indexOf() {
        assertIndex(myList.indexOf("10"), 0);
    }

    @Test
    void size() {
        myList.add("119");
        assertSize(10);
    }

    @Test
    void add() {
        myList.add("88");
        assertSize(10);
        assertSuccessContains("88");
        myList.add(6, "58");
        assertSize(11);
        assertSuccessContains("58");
        assertEquals("58", 6);
    }

    @Test
    void remove() {
        myList.remove("10");
        assertFailContains("10");
        assertSize(8);
        myList.remove(0);
        assertFailContains("9");
        assertSize(7);
        myList.remove(null);
        assertSize(6);
        assertFailContains(null);
    }

    @Test
    void contains() {
        myList.add("test");
        assertSuccessContains("test");
        assertSize(10);
    }

    @Test
    void get() {
        assertEquals(myList.get(0), 0);
    }


    public void assertSize(int size) {
        Assert.assertEquals(size, myList.size());
    }

    public void assertIndex(int indexA, int indexB) {
        Assert.assertEquals(indexA, indexB);
    }

    public void assertSuccessContains(String s) {
        Assert.assertTrue(myList.contains(s));
    }

    public void assertFailContains(String s) {
        Assert.assertFalse(myList.contains(s));
    }

    public void assertEquals(String s, int index) {
        Assert.assertEquals(myList.get(index), s);
    }

}