package controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Person;
import utils.Sex;

public class PersonControllerTest {

    PersonController people;
    Person person = new Person("Homelander", Sex.MASCULINO, 46, 75, 1.8);

    @Before
    public void before() throws Exception {
        people = new PersonController(("./test.txt"));
    }

    @After
    public void after() throws Exception {
        people.closeRepo();
        people.getRepo().getFile().delete();
    }

    /**
     * Method: insertPerson(Person person)
     */
    @Test
    public void testInsertPerson() throws Exception {
        people.insertPerson(person);
        Assert.assertEquals(person, people.getPeople().get("001"));

    }

} 
