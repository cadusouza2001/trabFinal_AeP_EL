package utils;

import org.junit.Assert;
import org.junit.Test;
import utils.Calculator;
import utils.Person;
import utils.Sex;

public class CalculatorTest {
    Person malePerson = new Person("Homelander", Sex.MALE, 46, 75, 1.8);
    Person femalePerson = new Person("Maeve", Sex.FEMALE, 36, 58, 1.72);

    /**
     * Method: calculateBMI(Person person)
     */
    @Test
    public void testCalculateBMI() {
        Assert.assertEquals(23.15, Calculator.calculateBMI(malePerson), 0.01);
    }


    /**
     * Method: calculateBFP(Person person)
     */
    @Test
    public void testCalculateMaleBFP() {
        Assert.assertEquals(22.15, Calculator.calculateBFP(malePerson), 0.01);
    }

    /**
     * Method: calculateBFP(Person person)
     */
    @Test
    public void testCalculateFemaleBFP() {
        Assert.assertEquals(26.4, Calculator.calculateBFP(femalePerson), 0.01);
    }


    /**
     * Method: calculateIBW(Person person)
     */
    @Test
    public void testCalculateMaleIBW() {
        Assert.assertEquals(72.5, Calculator.calculateIBW(malePerson), 0.01);
    }

    /**
     * Method: calculateIBW(Person person)
     */
    @Test
    public void testCalculateFemaleIBW() {
        Assert.assertEquals(61, Calculator.calculateIBW(femalePerson), 0.01);
    }


} 
