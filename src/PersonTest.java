import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for Person class
 * Tests constructors, setters, and all additional methods
 *
 * Name: Tika Khadka
 */
class PersonTest {

    private Person testPerson;
    private Person defaultPerson;
    private Person partialPerson;

    /**
     * Set up test fixtures before each test
     * Creates test instances to use in tests
     */
    @BeforeEach
    void setUp() {
        testPerson = new Person("Bilbo", "Baggins", "000001", "Esq.", 1060);
        defaultPerson = new Person();
        partialPerson = new Person("Frodo", "Baggins", "000002");
    }

    // Constructor Tests
    @Test
    @DisplayName("Test full constructor")
    void testFullConstructor() {
        assertEquals("Bilbo", testPerson.getFirstName());
        assertEquals("Baggins", testPerson.getLastName());
        assertEquals("000001", testPerson.getID());
        assertEquals("Esq.", testPerson.getTitle());
        assertEquals(1060, testPerson.getYOB());
    }

    @Test
    @DisplayName("Test default constructor")
    void testDefaultConstructor() {
        assertEquals("", defaultPerson.getFirstName());
        assertEquals("", defaultPerson.getLastName());
        assertEquals("", defaultPerson.getID());
        assertEquals("", defaultPerson.getTitle());
        assertEquals(1940, defaultPerson.getYOB());
    }

    @Test
    @DisplayName("Test partial constructor")
    void testPartialConstructor() {
        assertEquals("Frodo", partialPerson.getFirstName());
        assertEquals("Baggins", partialPerson.getLastName());
        assertEquals("000002", partialPerson.getID());
        assertEquals("", partialPerson.getTitle());
        assertEquals(1940, partialPerson.getYOB());
    }

    @Test
    @DisplayName("Test constructor with null values")
    void testConstructorWithNulls() {
        Person nullPerson = new Person(null, null, null, null, 1950);
        assertEquals("", nullPerson.getFirstName());
        assertEquals("", nullPerson.getLastName());
        assertEquals("", nullPerson.getID());
        assertEquals("", nullPerson.getTitle());
        assertEquals(1950, nullPerson.getYOB());
    }

    // Setter Tests
    @Test
    @DisplayName("Test setFirstName")
    void testSetFirstName() {
        testPerson.setFirstName("Samwise");
        assertEquals("Samwise", testPerson.getFirstName());
    }

    @Test
    @DisplayName("Test setFirstName with null")
    void testSetFirstNameNull() {
        testPerson.setFirstName(null);
        assertEquals("", testPerson.getFirstName());
    }

    @Test
    @DisplayName("Test setLastName")
    void testSetLastName() {
        testPerson.setLastName("Gamgee");
        assertEquals("Gamgee", testPerson.getLastName());
    }

    @Test
    @DisplayName("Test setTitle")
    void testSetTitle() {
        testPerson.setTitle("Mr.");
        assertEquals("Mr.", testPerson.getTitle());
    }

    @Test
    @DisplayName("Test setYOB with valid year")
    void testSetYOBValid() {
        testPerson.setYOB(1980);
        assertEquals(1980, testPerson.getYOB());
    }

    @Test
    @DisplayName("Test setYOB with invalid year - too low")
    void testSetYOBInvalidLow() {
        int originalYOB = testPerson.getYOB();
        testPerson.setYOB(1900); // Should not change
        assertEquals(originalYOB, testPerson.getYOB());
    }

    @Test
    @DisplayName("Test setYOB with invalid year - too high")
    void testSetYOBInvalidHigh() {
        int originalYOB = testPerson.getYOB();
        testPerson.setYOB(2050); // Should not change
        assertEquals(originalYOB, testPerson.getYOB());
    }

    // Additional Method Tests
    @Test
    @DisplayName("Test fullName method")
    void testFullName() {
        assertEquals("Bilbo Baggins", testPerson.fullName());
    }

    @Test
    @DisplayName("Test formalName method with title")
    void testFormalNameWithTitle() {
        assertEquals("Esq. Bilbo Baggins", testPerson.formalName());
    }

    @Test
    @DisplayName("Test formalName method without title")
    void testFormalNameWithoutTitle() {
        Person noTitle = new Person("John", "Doe", "001", "", 1980);
        assertEquals("John Doe", noTitle.formalName());
    }

    @Test
    @DisplayName("Test getAge method with current year")
    void testGetAgeCurrentYear() {
        String age = testPerson.getAge();
        assertNotNull(age);
        assertTrue(Integer.parseInt(age) > 900); // Should be a reasonable age
    }

    @Test
    @DisplayName("Test getAge method with specific year")
    void testGetAgeSpecificYear() {
        String age = testPerson.getAge(2000);
        assertEquals("940", age);
    }

    @Test
    @DisplayName("Test toCSV method")
    void testToCSV() {
        String expected = "000001, Bilbo, Baggins, Esq., 1060";
        assertEquals(expected, testPerson.toCSV());
    }

    @Test
    @DisplayName("Test toJSON method")
    void testToJSON() {
        String json = testPerson.toJSON();
        assertTrue(json.contains("\"ID\": \"000001\""));
        assertTrue(json.contains("\"firstName\": \"Bilbo\""));
        assertTrue(json.contains("\"lastName\": \"Baggins\""));
        assertTrue(json.contains("\"title\": \"Esq.\""));
        assertTrue(json.contains("\"YOB\": 1060"));
    }

    @Test
    @DisplayName("Test toXML method")
    void testToXML() {
        String xml = testPerson.toXML();
        assertTrue(xml.contains("<ID>000001</ID>"));
        assertTrue(xml.contains("<firstName>Bilbo</firstName>"));
        assertTrue(xml.contains("<lastName>Baggins</lastName>"));
        assertTrue(xml.contains("<title>Esq.</title>"));
        assertTrue(xml.contains("<YOB>1060</YOB>"));
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        String toString = testPerson.toString();
        assertTrue(toString.contains("Person{"));
        assertTrue(toString.contains("ID='000001'"));
        assertTrue(toString.contains("firstName='Bilbo'"));
        assertTrue(toString.contains("lastName='Baggins'"));
        assertTrue(toString.contains("title='Esq.'"));
        assertTrue(toString.contains("YOB=1060"));
    }

    @Test
    @DisplayName("Test equals method - same object")
    void testEqualsSameObject() {
        assertTrue(testPerson.equals(testPerson));
    }

    @Test
    @DisplayName("Test equals method - equal objects")
    void testEqualsEqualObjects() {
        Person samePerson = new Person("Bilbo", "Baggins", "000001", "Esq.", 1060);
        assertTrue(testPerson.equals(samePerson));
    }

    @Test
    @DisplayName("Test equals method - different objects")
    void testEqualsDifferentObjects() {
        Person differentPerson = new Person("Frodo", "Baggins", "000002", "Esq.", 1120);
        assertFalse(testPerson.equals(differentPerson));
    }

    @Test
    @DisplayName("Test equals method - null object")
    void testEqualsNull() {
        assertFalse(testPerson.equals(null));
    }

    @Test
    @DisplayName("Test equals method - different class")
    void testEqualsDifferentClass() {
        String notAPerson = "Not a person";
        assertFalse(testPerson.equals(notAPerson));
    }

    // Edge Case Tests
    @Test
    @DisplayName("Test with empty strings")
    void testEmptyStrings() {
        Person emptyPerson = new Person("", "", "", "", 1940);
        assertEquals("", emptyPerson.getFirstName());
        assertEquals("", emptyPerson.getLastName());
        assertEquals("", emptyPerson.getID());
        assertEquals("", emptyPerson.getTitle());
    }

    @Test
    @DisplayName("Test fullName with empty names")
    void testFullNameEmpty() {
        Person emptyPerson = new Person("", "", "001", "", 1940);
        assertEquals(" ", emptyPerson.fullName()); // Should be a space between empty strings
    }
}