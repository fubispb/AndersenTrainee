package collections.array_list;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyListTest {
    MyArrayList<String> myList = new MyArrayList<>();


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
        assertSize(8);
    }

    @Test
    void add() {
        myList.add("8");
        assertSize(9);
        assertSuccessContains("8");
        myList.add(6, "5");
        assertSize(10);
        assertSuccessContains("5");
    }

    @Test
    void remove() {
        myList.remove("10");
        assertFailContains("10");
        assertSize(7);
        myList.remove(0);
        assertFailContains("9");
        assertSize(6);
    }

    @Test
    void contains() {
        myList.add("test");
        assertSuccessContains("test");
    }

    @Test
    void get() {
        assertEquals(myList.get(0), 0);
    }

    @Test
    void grow(){
        myList.add("2");
        myList.add("1");
        myList.add("0");
        assertSize(11);
    }

    public void assertSize(int size) {
        Assert.assertEquals(size, myList.size());
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