package controller;

import persistence.FileRepository;
import utils.Person;
import utils.Sex;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersonController {
    int code;

    HashMap<String, Person> people;
    FileRepository repo;

    public PersonController() throws IOException {
        this.people = new HashMap<>();
        this.repo = new FileRepository();
        this.loadPeople();
    }

    public PersonController(String filePath) throws IOException {
        this.people = new HashMap<>();
        this.repo = new FileRepository(filePath);
        this.loadPeople();
    }

    public Map<String, Person> getPeople() {
        return people;
    }

    public void insertPerson(Person person) throws IOException {
        if (person.getHeight() < 0 || person.getWeight() < 0 || person.getAge() < 0) {
            return;
        }

        people.put(String.format("%03d", ++code), person);
        this.repo.saveToRepo(String.format("Code:%03d", code) + ";" + person);
    }

    public Person getPersonByCode(String code) {
        return this.people.get(code);
    }

    private void loadPeople() throws IOException {
        String line = this.repo.readLineFromRepo();
        if (line == null) {
            return;
        }
        while (line != null) {
            this.convertLineToPerson(line);
            line = this.repo.readLineFromRepo();
        }
    }

    private void convertLineToPerson(String line) {
        String[] attributes = line.split(";");
        Person person = new Person();
        String personCode = "";

        for (String s : attributes) {
            String[] attribute = s.split(":");
            switch (attribute[0].toLowerCase()) {
                case "code":
                    personCode = attribute[1];
                    break;
                case "name":
                    person.setName(attribute[1]);
                    break;
                case "sex":
                    person.setSex(attribute[1].compareToIgnoreCase("male") == 0 ? Sex.MALE : Sex.FEMALE);
                    break;
                case "age":
                    person.setAge(Integer.parseInt(attribute[1]));
                    break;
                case "weight":
                    person.setWeight(Double.parseDouble(attribute[1]));
                    break;
                case "height":
                    person.setHeight(Double.parseDouble(attribute[1]));
                    break;
                default:
                    //Não faz ação nenhuma pois a estrutura está errada no arquivo
            }
        }

        this.people.put(personCode, person);
        this.code = Integer.parseInt(personCode);
    }

    public void closeRepo() throws IOException {
        repo.getRepoWriter().close();
        repo.getRepoReader().close();
    }

    public FileRepository getRepo() {
        return repo;
    }
}
