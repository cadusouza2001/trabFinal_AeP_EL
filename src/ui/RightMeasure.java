package ui;

import controller.PersonController;
import utils.Person;
import utils.Sex;

import java.util.Map;
import java.util.Scanner;

public class RightMeasure {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PersonController peopleController = new PersonController();

        peopleController.insertPerson(new Person("Carlos",Sex.MALE,21,68,1.7));
        peopleController.insertPerson(new Person("Tom Holland",Sex.MALE,26,64,1.73));
        peopleController.insertPerson(new Person("Tobey Maguire",Sex.MALE,47,71,1.73));
        peopleController.insertPerson(new Person("Andrew Garfield",Sex.MALE,39,74,1.8));



        int choice = 0;
        do {
            System.out.println("Por gentileza, escolha uma das opções a seguir:");
            System.out.println("1 - Inserir pessoa");
            System.out.println("2 - Calcular IMC");
            System.out.println("3 - Calcular Peso Ideal");
            System.out.println("4 - Calcular Taxa de Gordura Corporal");
            System.out.println("5 - Listar todas as pessoas");
            System.out.println("6 - Sair");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Ocorreu um erro, tente novamente");
                sc.nextLine(); //Sem isso o Scanner fica travado
            }

            switch (choice) {
                case 1:
                    peopleController.insertPerson(registerPerson());
                    break;
                case 2:
                    System.out.println("De qual pessoa você quer calcular o IMC?");
                    listPeople(peopleController);
                    break;
                case 3:
                    System.out.println("Até mais!");
                    break;
                case 4:
                    System.out.println("Até mais!");
                    break;
                case 5:
                    System.out.println("Até mais!");
                    break;
                case 6:
                    System.out.println("Até mais!");
                    break;
                default:
                    System.out.println("Insira um número válido");
            }

        } while (choice != 6);

    }

    private static String readNumber() {
        Scanner sc = new Scanner(System.in);
        String number = "";
        while (true) {
            try {
                number = sc.next();
                Integer.parseInt(number);
                Double.parseDouble(number);
                return number;
            } catch (Exception e) {
                System.out.println("Por gentileza, insira um número válido");
                sc.nextLine(); //Sem isso o Scanner fica travado
            }
        }
    }

    public static Person registerPerson() {
        Scanner sc = new Scanner(System.in);
        String name;
        Sex sex;
        int age;
        double weight;
        double height;
        String aux = "";

        System.out.println("Qual o nome da pessoa a ser registrada?");
        name = sc.next();
        System.out.println("Qual o sexo dessa pessoa? M/F");
        while (!aux.equalsIgnoreCase("M") && !aux.equalsIgnoreCase("F")) {
            aux = sc.next();
            if (!aux.equalsIgnoreCase("M") && !aux.equalsIgnoreCase("F")) {
                System.out.println("Por gentileza, responda com M ou F");
            }
        }
        if (aux.equalsIgnoreCase("M")) {
            sex = Sex.MALE;
        } else {
            sex = Sex.FEMALE;
        }
        do {
            System.out.println("Qual a idade da pessoa?");
            age = Integer.parseInt(readNumber());
            if (age < 0 || age > 150) {
                System.out.println("Digite uma idade válida");
            }
        } while (age < 0 || age > 150);

        do {
            System.out.println("Qual o peso da pessoa em kg?");
            weight = Double.parseDouble(readNumber());
            if (weight < 0) {
                System.out.println("Digite uma valor válido");
            }
        } while (weight < 0);

        do {
            System.out.println("Qual a altura da pessoa em metros?");
            height = Double.parseDouble(readNumber());
            if (height < 0) {
                System.out.println("Digite uma valor válido");
            }
        } while (height < 0);

        return new Person(name, sex, age, weight, height);
    }

    public static void listPeople(PersonController peopleController) {
        for (Map.Entry<String, Person> entry : peopleController.getPeople().entrySet()) {
            System.out.printf("%s - %s\n", entry.getKey(), entry.getValue().getName());
        }
    }

}
