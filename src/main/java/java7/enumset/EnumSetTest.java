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

All of the elements in an enum set must come from a single enum type.
Enum sets are represented internally as bit vectors. This representation is extremely compact and efficient.
The space and time performance of this class should be good enough to allow its use as a high-quality, typesafe alternative to traditional <tt>int</tt>-based "bit flags."
Even bulk operations (such as containsAll and retainAll) should run very quickly if their argument is also an enum set.
The iterator returned by the <tt>iterator</tt> method traverses the elements in their <i>natural order</i> (the order in which the enum constants are declared).

Null elements are not permitted.  Attempts to insert a null element will throw NullPointerException.
Attempts to test for the presence of a null element or to remove one will, however, function properly.

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
