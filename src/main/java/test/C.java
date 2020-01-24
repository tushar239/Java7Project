package test;

public class C {
    public void method() {
        System.out.println("Inside C");
        throw new RuntimeException("Exception from C");
    }
}
