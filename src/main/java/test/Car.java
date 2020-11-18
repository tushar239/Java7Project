package test;

public class Car implements Vehicle, FourWheeler {

    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public void print() {
        System.out.println("I am a four wheeler car vehicle!");
    }
}
