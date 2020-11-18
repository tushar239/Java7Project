package test1;

// https://stackoverflow.com/questions/4848036/java-scoping-rules-and-inner-classes
public class InnerClassTest {

    public static void main(String[] args) {
        Outer.Inner1 inner1 = new Outer().new Inner1();
        Outer.Inner2 inner2 = new Outer.Inner2();
    }

}
