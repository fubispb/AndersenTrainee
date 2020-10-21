package patterns.bridge;

public class BridgePattern {

    public static void main(String[] args) {

        Car car = new Hatchback(new BMW());
        car.showDetails();

    }

}

abstract class Car {
    protected Make make;

    public Car(Make m) {
        make = m;
    }

    abstract void showType();

    void showDetails() {
        showType();
        make.setMake();
    }
}

class Sedan extends Car {

    public Sedan(Make m) {
        super(m);
    }

    @Override
    void showType() {
        System.out.println("Sedan");
    }
}

class Hatchback extends Car {

    public Hatchback(Make m) {
        super(m);
    }

    @Override
    void showType() {
        System.out.println("Hatchback");
    }
}

class Coupe extends Car {

    public Coupe(Make m) {
        super(m);
    }

    @Override
    void showType() {
        System.out.println("Coupe");
    }
}

interface Make {
    void setMake();
}

class Kia implements Make {

    public void setMake() {
        System.out.println("Kia");
    }
}

class Skoda implements Make {

    public void setMake() {
        System.out.println("Skoda");
    }
}

class BMW implements Make {

    public void setMake() {
        System.out.println("BMW");
    }
}