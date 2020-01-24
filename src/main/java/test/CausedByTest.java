package test;

public class CausedByTest {
    public static void main(String[] args) {
        A a = new A();
        try {
            a.method();
        } catch (Exception e) {
            System.out.println("Inside catch block of Test");
            throw new TestException("TestException", e);
        }
    }
}
