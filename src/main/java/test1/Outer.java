package test1;

public class Outer {
    private String name;
    private int age;
    private static String first;

    public void display() {
        System.out.println(new Inner1().address);
    }

    class Inner1 {
        String address;
        int age;

        public void display() {
            System.out.println(name); // Instance Inner class can access even private members of outer class
        }
        public void displayAge() {
            System.out.println(Outer.this.age);
        }

        class Inner1Inner1 {

        }
    }

    static class Inner2 {
        public void displayFirst() {
            System.out.println(first); // static inner class can access only static members of outer class
        }

        static class Inner2Inner2 {

        }
    }
}
