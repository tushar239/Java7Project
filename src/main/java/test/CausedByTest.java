package test;

import java.util.*;

public class CausedByTest {
    public static void main(String[] args) {
        A a = new A();
        try {
            a.method();
        } catch (Exception e) {
            System.out.println("Inside catch block of Test");
            throw new TestException("TestException", e);
        }

        new A1<Integer>().method("abc", 1);
        A1.method1("abc", "svd");

        List<String> strs = new ArrayList<>();
        strs.add("a");
        strs.add("b");
        strs.add("b");

        Set<String> set = new TreeSet<>(strs);
        System.out.println(set);
    }

    static class A1<K> {
        public <T> void method(T t, K k) {
            System.out.println(t);
            System.out.println(k);
        }
        public static <T, K> void method1(T t, K k) {
            System.out.println(t);
            System.out.println(k);
        }
    }
}
