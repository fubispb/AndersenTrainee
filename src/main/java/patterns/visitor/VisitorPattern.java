package patterns.visitor;

public class VisitorPattern {

    public static void main(String[] args) {

        Element car = new CarElement();
        car.accept(new HooliganVisitor());
        System.out.println();
        car.accept(new MechanicVisitor());

    }
}

// Visitor
interface Visitor {
    void visit(EngineElement engine);

    void visit(BodyElement body);

    void visit(CarElement car);

    void visit(WheelElement wheel);
}

// Element
interface Element {
    void accept(Visitor visitor);
}

// Element - Body
class BodyElement implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Element - Engine
class EngineElement implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Element - Wheel
class WheelElement implements Element {
    private final String name;

    public String getName() {
        return this.name;
    }

    public WheelElement(String name) {
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class CarElement implements Element {
    private final Element[] elements;

    public CarElement() {
        this.elements = new Element[] {new WheelElement("front left"),
                new WheelElement("front right"),
                new WheelElement("rear left"),
                new WheelElement("rear right"),
                new BodyElement(), new EngineElement()};
    }

    @Override
    public void accept(Visitor visitor) {
        for (Element elem : elements) {
            elem.accept(visitor);
        }
    }
}

class HooliganVisitor implements Visitor {
    @Override
    public void visit(EngineElement engine) {
        System.out.println("Start engine.");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("Knock car body.");
    }

    @Override
    public void visit(CarElement car) {
        System.out.println("Smoke inside car");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("Kick " + wheel.getName() + " wheel");
    }
}

class MechanicVisitor implements Visitor {
    @Override
    public void visit(EngineElement engine) {
        System.out.println("Check engine.");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("polish the body.");
    }

    @Override
    public void visit(CarElement car) {
        System.out.println("Checked the appearance of the car");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("Pumped up " + wheel.getName() + " wheel");
    }
}
