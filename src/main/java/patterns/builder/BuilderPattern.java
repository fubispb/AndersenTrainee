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

    protected void createCar() {
        car = new Car();
    }

    protected Car getCar() {
        return car;
    }
}

class FordMondeoBuilder extends CarBuilder {
    public void buildMake() {
        car.setMake("Ford Mondeo");
    }

    public void buildTransmission() {
        car.setTransmission(Transmission.AUTO);
    }

    public void buildMaxSpeed() {
        car.setMaxSpeed(260);
    }
}

class SubaruBuilder extends CarBuilder {
    public void buildMake() {
        car.setMake("Subaru");
    }

    public void buildTransmission() {
        car.setTransmission(Transmission.MANUAL);
    }

    public void buildMaxSpeed() {
        car.setMaxSpeed(320);
    }
}

class Director {
    private CarBuilder builder;

    public void setBuilder(CarBuilder b) {
        builder = b;
    }

    public Car BuildCar() {
        builder.createCar();
        builder.buildMake();
        builder.buildTransmission();
        builder.buildMaxSpeed();
        Car car = builder.getCar();
        return car;
    }
}