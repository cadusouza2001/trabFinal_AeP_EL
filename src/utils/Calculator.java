package utils;

public final class Calculator {

    private Calculator() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static double calculateBMI(Person person) {
        double height = person.getHeight();
        return person.getWeight() / (height * height);
    }

    public static double calculateBFP(Person person) {
        return (1.2*calculateBMI(person))-(10.8*(person.getSex()==Sex.FEMININO ? 0 : 1))+(0.23*person.getAge())-5.4;
    }

    public static double calculateIBW(Person person) {
        double height = person.getHeight()*100;
        return (height-100)-((height-150)/(person.getSex()==Sex.MASCULINO ? 4 : 2));
    }

}
