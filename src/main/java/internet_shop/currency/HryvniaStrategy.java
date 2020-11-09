package internet_shop.currency;

import java.io.Serializable;

public class HryvniaStrategy implements CurrencyStrategy, Serializable {

    private final String CODE = "UAH";
    private final String NAME = "hryvnia";
    private double course = 1;
    private int multiplicity = 20;

    @Override
    public int getMultiplicity() {
        return multiplicity;
    }

    @Override
    public double getCourse() {
        return course;
    }

    @Override
    public double multiply(int price, double course, int multiplicity) {
        return (price * course) * multiplicity;
    }

    public String getCode() {
        return CODE;
    }
    public String getName() {
        return NAME;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public void setMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
    }
}
