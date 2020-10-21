package collections.array_list;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        MyArrayList<Integer> myList = new MyArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(7);
        System.out.println(myList);
        myList.remove(2);
        System.out.println(myList);
        System.out.println(myList.get(1));
        Integer i = 8;
        myList.remove(i);
        System.out.println(myList);


    }
}
