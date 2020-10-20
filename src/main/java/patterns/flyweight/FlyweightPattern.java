package patterns.flyweight;

import java.util.*;

public class FlyweightPattern {

    public static void main(String[] args) {

        ShapeFactory shapeFactory = new ShapeFactory();

        List<Shape> shapes = new ArrayList<>();

        shapes.add(shapeFactory.getShape("квадрат"));
        shapes.add(shapeFactory.getShape("круг"));
        shapes.add(shapeFactory.getShape("круг"));
        shapes.add(shapeFactory.getShape("круг"));
        shapes.add(shapeFactory.getShape("квадрат"));
        shapes.add(shapeFactory.getShape("круг"));

        Random random = new Random();
        for (Shape shape : shapes) {
            int x = random.nextInt(100);
            int y = random.nextInt(100);
            shape.draw(x, y);
        }

    }

}

interface Shape {
    void draw(int x, int y);
}

class Point implements Shape {
    @Override
    public void draw(int x, int y) {
        System.out.println("("+x+","+y+"): рисуем точку ");
    }
}

class Circle implements Shape {

    int r = 5;

    @Override
    public void draw(int x, int y) {
        System.out.println("("+x+","+y+"): рисуем круг радиусом "+r);
    }
}

class Square implements  Shape {
    int a = 10;

    @Override
    public void draw(int x, int y) {
        System.out.println("("+x+","+y+"): рисуем квадрат со стороной "+a);
    }
}

class ShapeFactory {

    private static final Map<String, Shape> shapes = new HashMap<>();

    public Shape getShape(String shapeName) {
        Shape shape = shapes.get(shapeName);
        if(shape == null) {
            switch (shapeName) {
                case "круг":
                    shape = new Circle();
                    break;
                case "квадрат":
                    shape = new Square();
                    break;
                case "точка":
                    shape = new Point();
                    break;
            }
            shapes.put(shapeName, shape);
        }
        return shape;
    }


}