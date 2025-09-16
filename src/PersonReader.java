import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 * PersonReader - Reads Person data from CSV file and creates Person objects
 * Now uses ArrayList<Person> instead of parsing strings directly
 * Demonstrates object-oriented approach to data reading
 *
 * Name: Tika Khadka
 */
public class PersonReader {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<Person> personList = new ArrayList<>();  // Changed to ArrayList<Person>

        final int FIELDS_LENGTH = 5;

        System.out.println("=== Person Data Reader (Object Version) ===");
        System.out.println("Select a person data file to read and display.");

        try {
            // Get current working directory
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            // Show file chooser dialog
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                // Use NIO to read the file
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                // Read all lines and create Person objects
                while (reader.ready()) {
                    rec = reader.readLine();
                    if (rec != null && !rec.trim().isEmpty()) {
                        // Parse the CSV line and create Person object
                        Person person = parsePersonFromCSV(rec);
                        if (person != null) {
                            personList.add(person);
                        }
                    }
                }
                reader.close();

                System.out.println("\nData file read successfully!");
                System.out.println("File: " + selectedFile.getName());
                System.out.println("Person objects created: " + personList.size());

                // Display formatted data using Person objects
                displayPersonData(personList);

                // Demonstrate different output formats
                demonstrateOutputFormats(personList);

            } else {
                // User cancelled file selection
                System.out.println("No file selected. Program terminated.");
                System.out.println("Please run the program again and select a file.");
                System.exit(0);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\nProgram completed. Thank you!");
    }

    /**
     * Parses a CSV line and creates a Person object
     * @param csvLine The CSV line to parse
     * @return Person object or null if parsing fails
     */
    private static Person parsePersonFromCSV(String csvLine) {
        try {
            String[] fields = csvLine.split(",");

            if (fields.length == 5) {
                // Trim whitespace from all fields
                String id = fields[0].trim();
                String firstName = fields[1].trim();
                String lastName = fields[2].trim();
                String title = fields[3].trim();
                int yob = Integer.parseInt(fields[4].trim());

                // Create and return Person object
                return new Person(firstName, lastName, id, title, yob);
            } else {
                System.out.println("Warning: Invalid CSV format: " + csvLine);
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing year of birth in line: " + csvLine);
            return null;
        }
    }

    /**
     * Displays Person data in formatted table using Person object methods
     * @param persons ArrayList of Person objects to display
     */
    private static void displayPersonData(ArrayList<Person> persons) {
        System.out.println("\n" + "=".repeat(90));
        System.out.printf("%-8s %-15s %-15s %-8s %-6s %-25s %-8s%n",
                "ID#", "Firstname", "Lastname", "Title", "YOB", "Formal Name", "Age");
        System.out.println("=".repeat(90));

        // Display each Person using their methods
        for (Person person : persons) {
            System.out.printf("%-8s %-15s %-15s %-8s %-6d %-25s %-8s%n",
                    person.getID(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getTitle(),
                    person.getYOB(),
                    person.formalName(),
                    person.getAge());
        }

        System.out.println("=".repeat(90));
        System.out.println("Data display completed successfully!");
    }

    /**
     * Demonstrates different output formats (CSV, JSON, XML) using Person methods
     * @param persons ArrayList of Person objects
     */
    private static void demonstrateOutputFormats(ArrayList<Person> persons) {
        if (!persons.isEmpty()) {
            System.out.println("\n=== Output Format Demonstrations ===");
            Person firstPerson = persons.get(0);

            System.out.println("\nFirst person in different formats:");
            System.out.println("toString(): " + firstPerson.toString());
            System.out.println("\nCSV format:");
            System.out.println(firstPerson.toCSV());
            System.out.println("\nJSON format:");
            System.out.println(firstPerson.toJSON());
            System.out.println("\nXML format:");
            System.out.println(firstPerson.toXML());

            // Show usage of additional methods
            System.out.println("\n=== Person Method Demonstrations ===");
            System.out.println("Full Name: " + firstPerson.fullName());
            System.out.println("Formal Name: " + firstPerson.formalName());
            System.out.println("Age (current year): " + firstPerson.getAge());
            System.out.println("Age in year 2000: " + firstPerson.getAge(2000));
        }
    }
}