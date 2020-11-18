package test;

import java.util.*;

public class Temp {
    public static void main(String[] args) {
        String a[] = new String[] { "A", "B", "C", "D" };

        // getting the list view of Array
        List<String> list = Arrays.asList(a);
        list.set(3, "E");

        List<String> listStrs = new ArrayList<>();
        listStrs.add("A1");
        listStrs.add("A2");
        listStrs.add("A3");

        Object[] array = listStrs.toArray();
        for (int i = 0; i < array.length; i++) {
            System.out.println((String)array[i]);
        }

        Person person = new Person();
        Car car = new Car();
        car.setModel("model");
        person.setCar(null);

        String model = Optional.of(person)
                .map(p -> p.getCar())
                .map(c -> c.getModel())
                .orElse("unknown");
        System.out.println(model);
    }
}
