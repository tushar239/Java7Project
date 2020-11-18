package java7.enumset;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author Tushar Chokshi @ 3/26/17.
 */
/*
http://stackoverflow.com/questions/11825009/what-does-enumset-really-mean
EnumSet is a special Set type that can hold Enum values

EnumSet class is a member of the Java Collections Framework & is not synchronized. It's a high performance set implementation, much faster than HashSet.
All of the elements in an EnumSet must come from a single enumeration type that is specified when the set is created either explicitly or implicitly.

- It extends AbstractSet class and implements Set Interface in Java.
- EnumSet class is a member of the Java Collections Framework & is not synchronized.
- It’s a high performance set implementation, much faster than HashSet.
- All of the elements in an EnumSet must come from a single enumeration type that is specified when the set is created either explicitly or implicitly.
- It does not allow null Objects and throws NullPointerException if we do so.
- It uses a fail-safe iterator, so it won’t throw ConcurrentModificationException if the collection is modified while iterating.

Like most collection implementations, <tt>EnumSet</tt> is not synchronized.
If multiple threads access an enum set concurrently, and at least one of the threads modifies the set, it should be synchronized externally.
This is typically accomplished by synchronizing on some object that naturally encapsulates the enum set.
If no such object exists, the set should be "wrapped" using the Collections.synchronizedSet method.
This is best done at creation time, to prevent accidental unsynchronized access:

Set<MyEnum> s = Collections.synchronizedSet(EnumSet.noneOf(MyEnum.class));

Implementation note: All basic operations execute in constant time.
They are likely (though not guaranteed) to be much faster than their HashSet counterparts.
Even bulk operations execute in constant time if their argument is also an enum set.
*/
public class EnumSetTest {

    enum DAY {
        SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY
    }
    public static void main(String[] args) {

        EnumSet<DAY> twoDays = EnumSet.of(DAY.SATURDAY, DAY.MONDAY);
        System.out.println(twoDays);// [MONDAY, SATURDAY]
        // they are iterated in the same order they are defined (just like list)

        Set<DAY> days = Collections.unmodifiableSet(EnumSet.allOf(DAY.class));
        System.out.println(days);
        // [SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY]


    }
}
