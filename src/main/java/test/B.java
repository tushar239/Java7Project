package test;

public class B {
    public void method() {
        System.out.println("Inside B");
        C c = new C();
        try {
            c.method();
        } catch (Exception e) {
            System.out.println("inside catch block of B");
            throw new BException("BException", e);
        }

    }
}
