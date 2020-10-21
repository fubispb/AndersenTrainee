package patterns.flyweight;

import java.util.*;

public class FlyweightPattern {

    public static void main(String[] args) {

        ShapeFactory shapeFactory = new ShapeFactory();

        List<Shape> shapes = new ArrayList<>();

        shapes.add(shapeFactory.getShape("square"));
        shapes.add(shapeFactory.getShape("round"));
        shapes.add(shapeFactory.getShape("round"));
        shapes.add(shapeFactory.getShape("round"));
        shapes.add(shapeFactory.getShape("square"));
        shapes.add(shapeFactory.getShape("round"));

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
        System.out.println("(" + x + "," + y + "): drawing point ");
    }
}

class Circle implements Shape {

    @Override
    public void draw(int x, int y) {
        int r = 5;
        System.out.println("(" + x + "," + y + "): drawing round with radius " + r);
    }
}

class Square implements Shape {

    @Override
    public void draw(int x, int y) {
        int a = 10;
        System.out.println("(" + x + "," + y + "): drawing square with side " + a);
    }
}

class ShapeFactory {

    private static final Map<String, Shape> shapes = new HashMap<>();

    public Shape getShape(String shapeName) {
        Shape shape = shapes.get(shapeName);
        if (shape == null) {
            switch (shapeName) {
                case "square":
                    shape = new Circle();
                    break;
                case "round":
                    shape = new Square();
                    break;
                case "point":
                    shape = new Point();
                    break;
            }
            shapes.put(shapeName, shape);
        }
        return shape;
    }

}