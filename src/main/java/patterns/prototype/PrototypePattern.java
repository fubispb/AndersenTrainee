package patterns.prototype;

public class PrototypePattern {

    public static void main(String[] args) {

        Human original = new Human(18, "Vasya");
        System.out.println(original);

        Human copy = (Human) original.copy();
        System.out.println(copy);

        HumanFactory factory = new HumanFactory(copy);
        Human h1 = factory.makeCopy();
        System.out.println(h1);

        factory.setPrototype(new Human(30, "Valeriya"));
        Human h2 = factory.makeCopy();
        System.out.println(h2);
    }
}

interface Copyable {
    Object copy();
}

class Human implements Copyable {
    private final int age;
    private final String name;

    public Human(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Human [age=" + age + ", name=" + name + "]";
    }

    public Object copy() {
        return new Human(age, name);
    }
}

class HumanFactory {
    private Human human;

    public HumanFactory(Human human) {
        setPrototype(human);
    }

    public void setPrototype(Human human) {
        this.human = human;
    }

    public Human makeCopy() {
        return (Human) human.copy();
    }
}