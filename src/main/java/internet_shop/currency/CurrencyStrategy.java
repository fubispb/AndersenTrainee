package internet_shop.currency;

public interface CurrencyStrategy  {

    double multiply(int price, double course, int multiplicity);

    int getMultiplicity();

    double getCourse();
}
