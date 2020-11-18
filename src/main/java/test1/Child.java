package test1;

public class Child extends Parent {
    String address;

    @Override
    public void display() {
        System.out.println("Inside Child's display()");
    }
}
