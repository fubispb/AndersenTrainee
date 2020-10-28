package collections.hashmap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MyHashMapTest {

    MyHashMap<String, Integer> map = new MyHashMap<>();

    @BeforeEach
    void setUp() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        map.put("ten", 10);
        assertSize(10);
    }

    @Test
    void put() {
       map.put("eleven", 11);
       map.put("one", 155);
       assertSize(11);
       assertSuccessContainsKey("eleven");
       assertSuccessContainsValue(11);
       assertSuccessContainsValue(155);
       assertFailContainsValue(1);
    }

    @Test
    void get() {
        assertEquals("one", 1);
        assertSize(10);
    }

    @Test
    void clear() {
        map.clear();
        assertSize(0);
        assertCapacity(16);
        assertBucketsCount(0);
    }

    @Test
    void remove() {
        map.remove("eight");
        assertFailContainsKey("eight");
        assertFailContainsValue(8);
        assertSize(9);
    }

    @Test
    void containsKey() {
        map.put("eee", 122);
        assertSuccessContainsKey("eee");
    }

    @Test
    void containsValue() {
        map.put("eee", 122);
        assertSuccessContainsValue(122);
    }

    @Test
    void size() {
        map.put("one", 2);
        assertSize(10);
        map.put("twelve", 12);
        assertSize(11);
    }

    public void assertSize(int size) {
        Assert.assertEquals(size, map.size());
    }

    public void assertCapacity(int capacity) {
        Assert.assertEquals(capacity, map.getCurrentCapacity());
    }

    public void assertBucketsCount(int count) {
        Assert.assertEquals(count, map.getBucketsCount());
    }

    public void assertSuccessContainsKey(String s) {
        Assert.assertTrue(map.containsKey(s));
    }
    public void assertSuccessContainsValue(Integer v) {
        Assert.assertTrue(map.containsValue(v));
    }
    public void assertFailContainsKey(String s) {
        Assert.assertFalse(map.containsKey(s));
    }

    public void assertFailContainsValue(Integer v) {
         Assert.assertFalse(map.containsValue(v));
    }

    public void assertEquals(String s, Integer v) {
        Assert.assertEquals(map.get("one"), v);
    }

}