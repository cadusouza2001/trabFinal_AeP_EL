package utils;

public class Person {
    String name;
    Sex sex;
    int age;
    double weight;
    double height;

    public Person(String name, Sex sex, int age, double weight, double height) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String weightStatus() {
        double bmi = Calculator.calculateBMI(this);
        if (sex == Sex.FEMALE) {
            if (bmi < 19.1) {
                return "Underweight";
            } else if (bmi <= 25.8) {
                return "Ideal";
            } else return "Obese";
        } else {
            if (bmi < 20.7) {
                return "Underweight";
            } else if (bmi <= 26.4) {
                return "Ideal";
            } else return "Obese";
        }
    }

    public String infoString() {
        return "Name: " + name +
                "\nSex: " + sex +
                "\nAge: " + age +
                "\nWeight: " + weight + "kg" +
                "\nHeight: " + height + "m" +
                "\nBody Mass Index: " + Calculator.calculateBMI(this) +
                "\nWeight status: " + weightStatus() +
                "\nBody Fat Percentage: " + Calculator.calculateBFP(this) +
                "\nIdeal Body Weight: " + Calculator.calculateIBW(this) + "\n";
    }

    @Override
    public String toString() {
        return "Name:" + name +
                ";Sex:" + sex +
                ";Age:" + age +
                ";Weight:" + weight +
                ";Height:" + height + "\n";
    }
}
