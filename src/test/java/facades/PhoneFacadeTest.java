package facades;

import entities.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneFacadeTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void getPhoneFacade() {
    }

    @Test
    void create(Person person, int number, String description)
    {
        if(person == null)
        {
            throw new IllegalArgumentException("Person cannot be null");
        }
        if(number == 0 || (""+number).length() != 8)
        {
            throw new IllegalArgumentException("Number must be 8 digits");
        }
    }

    @Test
    void getByInt() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}