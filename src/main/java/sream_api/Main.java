package sream_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list.stream().map(x -> x * 2).forEach(System.out::println);

        list.parallelStream().map(x -> x * 2).forEach(System.out::println);
        list.parallelStream().map(x -> x * 2).forEachOrdered(System.out::println);


        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        Stream<Integer> streamList2 = list2.stream();
        IntStream.range(4, 7).forEach(list2::add);
        streamList2.forEach(System.out::println);

    }
}