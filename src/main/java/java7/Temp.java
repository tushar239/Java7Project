package java7;

public class Temp {
    public static void main(String[] args) {
        Temp temp = new Temp();
        temp.method();
    }

    public void method() {
        A obj = new B(1);
        obj.i = 1;
        // obj.j = 2;
        System.out.println(obj.i);
        obj.method();

        C c = new C(1);
    }
}
    abstract class A {
        int i;


        protected A(int i) {
        }



        public void display() {
            System.out.println("i:" + i);
        }

        protected abstract void method();
    }

    class B extends A {
        int i;
        int j;

        protected B(int i) {
            super(i);
        }

        protected B(int i, int j) {
            super(i);
        }
        public void display() {
            System.out.println("j:" + j);
        }

        protected void method() {
            System.out.println(i);
        }
    }

    class C extends A {
        int ii;

        {
            ii = 10;
            System.out.println("Instance initializer block is invoked.");
        }

        public C(int i) {
            super(i);
            ii = 20;
            System.out.println("constructor  is invoked.");

        }

        @Override
        protected void method() {

        }

        interface inter { // all inner interfaces are static by default

        }

        class inner {

         /*static class inner1 { // static inner class can be part of another static class or top level class.

        }*/
        }
    }
