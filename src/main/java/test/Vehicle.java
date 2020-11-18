package test;

public interface Vehicle {
    default void print() {
        System.out.println("I am a vehicle!");
    }
}
