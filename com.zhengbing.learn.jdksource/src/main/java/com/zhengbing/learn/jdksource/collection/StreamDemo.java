package com.zhengbing.learn.jdksource.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author zhengbing
 * @date 2019-07-03
 * @email mydreambing@126.com
 * @since version 1.0
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
        Stream<String> stream = strings.stream();
        Stream<String> stream2 = stream.limit(2);
        Optional<String> optional = stream.findFirst();
        System.out.println(optional);
    }
}
