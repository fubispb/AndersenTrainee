package patterns.template_method;

public class TemplateMethod {

    public static void main(String[] args) {
        C a = new A();
        a.templateMethod();

        System.out.println();

        C b = new B();
        b.templateMethod();
    }
}

abstract class C {

    abstract void differ();

    abstract void differ2();

    final void templateMethod() {
        System.out.print("1");
        differ();
        System.out.print("3");
        differ2();
    }
}

class A extends C {
    public void differ() {
        System.out.print("2");
    }

    public void differ2() {
        System.out.print("5");
    }
}

class B extends C {
    public void differ() {
        System.out.print("4");
    }

    public void differ2() {
    }
}
