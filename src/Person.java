import java.util.Calendar;

/**
 * Person class represents a person with basic information
 * Used for storing and manipulating person data in object form
 *
 * Name: Tika Khadka
 */
public class Person {
    // Fields
    private String firstName;
    private String lastName;
    private String ID;
    private String title;
    private int YOB;

    /**
     * Default constructor - initializes person with empty values
     */
    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.ID = "";
        this.title = "";
        this.YOB = 1940;
    }

    /**
     * Full constructor with all fields
     * @param firstName First name of the person
     * @param lastName Last name of the person
     * @param ID Unique identifier (should never change)
     * @param title Title prefix (Mr., Mrs., Ms., Dr., etc.)
     * @param YOB Year of birth (accepts any valid year)
     */
    public Person(String firstName, String lastName, String ID, String title, int YOB) {
        this.firstName = firstName != null ? firstName : "";
        this.lastName = lastName != null ? lastName : "";
        this.ID = ID != null ? ID : "";
        this.title = title != null ? title : "";
        this.YOB = YOB;
    }

    /**
     * Constructor with name and ID only
     * @param firstName First name of the person
     * @param lastName Last name of the person
     * @param ID Unique identifier
     */
    public Person(String firstName, String lastName, String ID) {
        this(firstName, lastName, ID, "", 1940);
    }

    // Getters
    /**
     * Gets the first name
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the ID
     * @return ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Gets the title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the year of birth
     * @return YOB
     */
    public int getYOB() {
        return YOB;
    }

    // Setters (ID should not have setter as it should never change)
    /**
     * Sets the first name
     * @param firstName First name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName != null ? firstName : "";
    }

    /**
     * Sets the last name
     * @param lastName Last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName != null ? lastName : "";
    }

    /**
     * Sets the title
     * @param title Title to set
     */
    public void setTitle(String title) {
        this.title = title != null ? title : "";
    }

    /**
     * Sets the year of birth with validation
     * @param YOB Year of birth to set (1940-2010)
     */
    public void setYOB(int YOB) {
        if (YOB >= 1940 && YOB <= 2010) {
            this.YOB = YOB;
        }
    }

    // Additional Methods
    /**
     * Returns full name (firstName + lastName)
     * @return Full name as "firstName lastName"
     */
    public String fullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns formal name (title + fullName)
     * @return Formal name as "title firstName lastName"
     */
    public String formalName() {
        if (title.isEmpty()) {
            return fullName();
        }
        return title + " " + fullName();
    }

    /**
     * Calculates age based on current year using Calendar
     * @return Age as string
     */
    public String getAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return String.valueOf(currentYear - YOB);
    }

    /**
     * Calculates age for a specified year
     * @param year Year to calculate age for
     * @return Age as string for the specified year
     */
    public String getAge(int year) {
        return String.valueOf(year - YOB);
    }

    /**
     * Returns CSV representation of the person
     * @return CSV string in format: ID, firstName, lastName, title, YOB
     */
    public String toCSV() {
        return ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
    }

    /**
     * Returns JSON representation of the person
     * @return JSON formatted string representing the person object
     */
    public String toJSON() {
        return "{\n" +
                "  \"ID\": \"" + ID + "\",\n" +
                "  \"firstName\": \"" + firstName + "\",\n" +
                "  \"lastName\": \"" + lastName + "\",\n" +
                "  \"title\": \"" + title + "\",\n" +
                "  \"YOB\": " + YOB + "\n" +
                "}";
    }

    /**
     * Returns XML representation of the person
     * @return XML formatted string representing the person object
     */
    public String toXML() {
        return "<Person>\n" +
                "  <ID>" + ID + "</ID>\n" +
                "  <firstName>" + firstName + "</firstName>\n" +
                "  <lastName>" + lastName + "</lastName>\n" +
                "  <title>" + title + "</title>\n" +
                "  <YOB>" + YOB + "</YOB>\n" +
                "</Person>";
    }

    /**
     * String representation of the person object
     * @return String representation showing all field values
     */
    @Override
    public String toString() {
        return "Person{" +
                "ID='" + ID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", YOB=" + YOB +
                '}';
    }

    /**
     * Checks equality with another Person object
     * @param obj Object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Person person = (Person) obj;

        return YOB == person.YOB &&
                ID.equals(person.ID) &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                title.equals(person.title);
    }

}