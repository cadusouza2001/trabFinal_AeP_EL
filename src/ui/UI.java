package ui;

import controller.PersonController;
import utils.Calculator;
import utils.Person;
import utils.Sex;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class UI {

    Scanner sc;
    PersonController peopleController;
    Person personChosen;

    public UI() {
        try {
            this.peopleController = new PersonController();
            this.sc = new Scanner(System.in);
        } catch (IOException e) {
            this.printLine(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void menu() {
        int choice = 0;
        do {
            this.printLine("Por gentileza, escolha uma das opções a seguir:");
            this.printLine("1 - Inserir pessoa");
            this.printLine("2 - Calcular IMC");
            this.printLine("3 - Calcular Peso Ideal");
            this.printLine("4 - Calcular Taxa de Gordura Corporal");
            this.printLine("5 - Listar todas as pessoas");
            this.printLine("6 - Sair");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                this.printLine("Ocorreu um erro, tente novamente");
                sc.nextLine(); //Sem isso o Scanner fica travado
            }

            switch (choice) {
                case 1:
                    try {
                        peopleController.insertPerson(registerPerson());
                    } catch (IOException e) {
                        this.printLine("Ocorreu algum erro ao criar está pessoa por favor tente novamente.");
                    }
                    break;
                case 2:
                    listPeople(peopleController);
                    personChosen = peopleController.getPersonByCode(this.getStringValue("De qual pessoa você quer calcular o IMC? Escolha pelo código"));
                    if (Objects.isNull(personChosen)) {
                        this.printLine("Não encontramos ninguém com esse código");
                    } else {
                        System.out.printf("Nome: %s%nIMC: %.2f%n", personChosen.getName(), Calculator.calculateBMI(personChosen));
                    }
                    break;
                case 3:
                    listPeople(peopleController);
                    personChosen = peopleController.getPersonByCode(this.getStringValue("De qual pessoa você quer calcular o Peso Ideal? Escolha pelo código"));
                    if (Objects.isNull(personChosen)) {
                        this.printLine("Não encontramos ninguém com esse código");
                    } else {
                        System.out.printf("Nome: %s%nPeso Ideal: %.2f%n", personChosen.getName(), Calculator.calculateIBW(personChosen));
                    }
                    break;
                case 4:
                    listPeople(peopleController);
                    personChosen = peopleController.getPersonByCode(this.getStringValue("De qual pessoa você quer calcular a Taxa de Gordura Corporal? Escolha pelo código"));
                    if (Objects.isNull(personChosen)) {
                        this.printLine("Não encontramos ninguém com esse código");
                    } else {
                        System.out.printf("Nome: %s%nTaxa de Gordura Corporal: %.2f%n", personChosen.getName(), Calculator.calculateBFP(personChosen));
                    }
                    break;
                case 5:
                    for (Map.Entry<String, Person> entry : peopleController.getPeople().entrySet()) {
                        this.printLine(entry.getValue().infoString());
                    }
                    break;
                case 6:
                    this.printLine("Até mais!");
                    break;
                default:
                    this.printLine("Insira um número válido");
            }

        } while (choice != 6);
    }

    private int getIntegerValue(String message) {
        try {
            this.printLine(message);
            return this.sc.nextInt();
        } catch (Exception e) {
            this.printLine("Por gentileza, insira um número válido");
            this.sc.next();
            return this.getIntegerValue(message);
        }
    }

    private double getDoubleValue(String message) {
        try {
            this.printLine(message);
            return this.sc.nextDouble();
        } catch (InputMismatchException ex) {
            this.printLine("Por gentileza, insira um número válido");
            this.sc.next();
            return this.getDoubleValue(message);
        }
    }

    public String getStringValue(String message) {
        this.printLine(message);
        return this.sc.next();
    }

    public void printLine(String message) {
        System.out.println(message);
    }

    public Person registerPerson() {
        String name;
        Sex sex;
        int age;
        double weight;
        double height;
        String aux = "";

        this.printLine("Qual o nome da pessoa a ser registrada?");
        name = this.sc.next();
        this.printLine("Qual o sexo dessa pessoa? M/F");
        while (!aux.equalsIgnoreCase("M") && !aux.equalsIgnoreCase("F")) {
            aux = this.sc.next();
            if (!aux.equalsIgnoreCase("M") && !aux.equalsIgnoreCase("F")) {
                this.printLine("Por gentileza, responda com M ou F");
            }
        }
        if (aux.equalsIgnoreCase("M")) {
            sex = Sex.MALE;
        } else {
            sex = Sex.FEMALE;
        }
        do {
            age = this.getIntegerValue("Qual a idade da pessoa?");
            if (age < 0 || age > 150) {
                this.printLine("Digite uma idade válida");
            }
        } while (age < 0 || age > 150);

        do {
            weight = this.getDoubleValue("Qual o peso da pessoa em kg?");
            if (weight < 0) {
                this.printLine("Digite uma valor válido");
            }
        } while (weight < 0);

        do {
            height = this.getDoubleValue("Qual a altura da pessoa em metros?");
            if (height < 0) {
                this.printLine("Digite uma valor válido");
            }
        } while (height < 0);

        return new Person(name, sex, age, weight, height);
    }

    public static void listPeople(PersonController peopleController) {
        for (Map.Entry<String, Person> entry : peopleController.getPeople().entrySet()) {
            System.out.printf("%s - %s%n", entry.getKey(), entry.getValue().getName());
        }
    }


}
