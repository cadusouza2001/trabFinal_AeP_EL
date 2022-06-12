package controller;

import utils.Person;

import java.util.HashMap;
import java.util.Map;

public class PersonController {
    static int code;

    HashMap<String,Person> people;

    public PersonController() {
        this.people = new HashMap<>();
    }

    public Map<String, Person> getPeople() {
        return people;
    }

    public boolean insertPerson(Person person) {
        if (person.getHeight() < 0 || person.getWeight() < 0 || person.getAge() < 0) {
            return false;
        }
        people.put(String.format("%03d",code++),person);
        return true;
    }

    public Person getPersonByCode(String code){
        return this.people.get(code);
    }
}
