package test;

public class A {
    public void method() {
        System.out.println("Inside A");

        B b = new B();

        try {
            b.method();
        } catch (Exception e) {
            System.out.println("Inside catch block of A");
            throw new AException("AException", e);
        }
    }
}
