package com.sun.geekbang.TrainingCamp.week04.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HomeWork_9 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(1, 10000).forEach(i -> list.add(i));
        List<Long> longList = list.stream().parallel()
                .map(Integer::longValue)
                .sorted()
                .collect(Collectors.toList());

        // 这也算启动新线程了
        System.out.println(longList);
    }
}
