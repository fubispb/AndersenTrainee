package patterns.builder;

public class BuilderPattern {

    public static void main(String[] args) {
        Director director = new Director();
        director.setBuilder(new FordMondeoBuilder());
        Car car = director.BuildCar();
        System.out.println(car);
    }
}


enum Transmission {
    MANUAL, AUTO
}

class Car {
    private String make;
    private Transmission transmission;
    private int maxSpeed;

    public void setMake(String make) {
        this.make = make;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car [make=" + make + ", transmission=" + transmission
                + ", maxSpeed=" + maxSpeed + "]";
    }

}

abstract class CarBuilder {
    protected Car car;

    abstract void buildMake();

    abstract void buildTransmission();

    abstract void buildMaxSpeed();

    void createCar() {
        car = new Car();
    }

    Car getCar() {
        return car;
    }
}

class FordMondeoBuilder extends CarBuilder {
    void buildMake() {
        car.setMake("Ford Mondeo");
    }

    void buildTransmission() {
        car.setTransmission(Transmission.AUTO);
    }

    void buildMaxSpeed() {
        car.setMaxSpeed(260);
    }
}

class SubaruBuilder extends CarBuilder {
    void buildMake() {
        car.setMake("Subaru");
    }

    void buildTransmission() {
        car.setTransmission(Transmission.MANUAL);
    }

    void buildMaxSpeed() {
        car.setMaxSpeed(320);
    }
}

class Director {
    private CarBuilder builder;

    void setBuilder(CarBuilder b) {
        builder = b;
    }

    Car BuildCar() {
        builder.createCar();
        builder.buildMake();
        builder.buildTransmission();
        builder.buildMaxSpeed();
        Car car = builder.getCar();
        return car;
    }
}