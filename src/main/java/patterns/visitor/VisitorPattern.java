package patterns.visitor;

public class VisitorPattern {

    public static void main(String[] args) {

        Element car = new CarElement();
        car.accept(new HooliganVisitor());
        System.out.println();
        car.accept(new MechanicVisitor());

    }
}

//Посетитель (Visitor)
interface Visitor {
    void visit(EngineElement engine);

    void visit(BodyElement body);

    void visit(CarElement car);

    void visit(WheelElement wheel);
}

//Элемент - Автозапчасть
interface Element {
    void accept(Visitor visitor);
}

//Кузов
class BodyElement implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

//Двигатель
class EngineElement implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

//Колесо
class WheelElement implements Element {
    private String name;

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
    Element[] elements;

    public CarElement() {
        this.elements = new Element[] {new WheelElement("переднее левое"),
                new WheelElement("переднее парвое"),
                new WheelElement("заднее левое"),
                new WheelElement("заднее правое"),
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
        System.out.println("Завёл двигатель.");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("Постучал по корпусу.");
    }

    @Override
    public void visit(CarElement car) {
        System.out.println("Покурил внутри машины");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("Пнул " + wheel.getName() + " колесо");
    }
}

class MechanicVisitor implements Visitor {
    @Override
    public void visit(EngineElement engine) {
        System.out.println("Проверил двигатель.");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("Отполировал кузов.");
    }

    @Override
    public void visit(CarElement car) {
        System.out.println("Проверил внешний вид автомобиля");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("Подкачал " + wheel.getName() + " колесо");
    }
}
