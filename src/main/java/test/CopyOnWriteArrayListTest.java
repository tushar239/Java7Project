package test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
    public static void main(String[] args) throws InterruptedException {
        //final ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        final CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        System.out.println("Before thread starts: "+ numbers.hashCode()); // 29615266

        // new thread to concurrently modify the nilList
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // sleep a little so that for loop below can print part of the nilList
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                numbers.set(4, 10);
                numbers.add(11);
                System.out.println("Inside thread, numbers: " + numbers); // numbers:  [1, 2, 3, 4, 10, 11]
            }
        });

        t.start();
        t.join();

        System.out.println("Before for loop: " + numbers.hashCode()); // 29615266

        // This one throws ConcurrentModificationException for ArrayList, but not for CopyOnWriteArrayList
        // This one uses Iterator 'Itr' of ArrayList to get next element, which can throw ConcurrentModificationException
        System.out.println("Before for loop, numbers: "+numbers);
        for (Integer number : numbers) {
            numbers.add(13);
            System.out.println("Inside for loop, "+ numbers.hashCode()+" ,numbers.size(): "+ numbers.size());
            System.out.println(number);
            // sleep a little to let other thread finish adding an element before this iteration is complete
            Thread.sleep(100);
        }

        System.out.println("After for loop: "+numbers);

        // This one doesn't throw ConcurrentModificationException for ArrayList
        // This one (nilList.get(index)) doesn't throw ConcurrentModificationException
        /*for (int i=0; i<numbers.size(); i++) {
            System.out.println("Inside for loop, "+ numbers.hashCode()+" ,numbers.size(): "+ numbers.size());
            System.out.println(numbers.get(i));
            // sleep a little to let other thread finish adding an element before this iteration is complete
            Thread.sleep(100);
        }*/
        System.out.println("After for loop: "+ numbers.hashCode()); // 918073256
    }
}
