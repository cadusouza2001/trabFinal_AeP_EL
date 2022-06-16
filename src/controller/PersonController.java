package controller;

import persistence.FileRepository;
import utils.Person;
import utils.Sex;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersonController {
    int code;

    HashMap<String,Person> people;
    FileRepository repo;

    public PersonController() throws IOException {
        this.people = new HashMap<>();
        this.repo = new FileRepository();
        this.loadPeople();
    }

    public Map<String, Person> getPeople() {
        return people;
    }

    public boolean insertPerson(Person person) throws IOException {
        if (person.getHeight() < 0 || person.getWeight() < 0 || person.getAge() < 0) {
            return false;
        }
        people.put(String.format("%03d",code++),person);
        this.repo.saveToRepo(String.format("Code:%03d",code++) + ";" + person);
        return true;
    }

    public Person getPersonByCode(String code){
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
        String code = "";

        for (int i = 0; i < attributes.length; i++) {
            String[] attribute = attributes[i].split(":");
            switch (attribute[0].toLowerCase()) {
                case "code":
                    code = attribute[1];
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
            }
        }

        this.people.put(code, person);
        this.code = Integer.parseInt(code);
    }
}
