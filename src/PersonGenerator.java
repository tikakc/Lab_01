import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * PersonGenerator - Creates Person objects and saves them to CSV file
 * Now uses ArrayList<Person> instead of ArrayList<String>
 * Demonstrates object-oriented approach to data management
 *
 * Name: Tika Khadka
 */
public class PersonGenerator {

    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);
        ArrayList<Person> personList = new ArrayList<>();  // Changed to ArrayList<Person>
        boolean continueInput = true;

        System.out.println("=== Person Data Generator (Object Version) ===");
        System.out.println("This program collects person information and saves it to a CSV file.");
        System.out.println("Data format: ID, FirstName, LastName, Title, YearOfBirth");
        System.out.println();

        // Collect person data from user
        while (continueInput) {
            System.out.println("Enter person information:");

            // Get ID (String)
            String id = SafeInput.getNonZeroLenString(pipe, "Enter ID (e.g., 000001)");

            // Get First Name (String)
            String firstName = SafeInput.getNonZeroLenString(pipe, "Enter First Name");

            // Get Last Name (String)
            String lastName = SafeInput.getNonZeroLenString(pipe, "Enter Last Name");

            // Get Title (String)
            String title = SafeInput.getNonZeroLenString(pipe, "Enter Title (e.g., Mr., Ms., Esq., Mrs., Dr.)");

            // Get Year of Birth (int)
            int yearOfBirth = SafeInput.getRangedInt(pipe, "Enter Year of Birth", 1940, 2010);

            // Create Person object and add to ArrayList
            Person person = new Person(firstName, lastName, id, title, yearOfBirth);
            personList.add(person);

            System.out.println("\nPerson added: " + person.formalName());
            System.out.println("Total persons: " + personList.size());

            // Demonstrate using Person methods
            System.out.println("Full name: " + person.fullName());
            System.out.println("Current age: " + person.getAge());

            // Ask if user wants to continue
            continueInput = SafeInput.getYNConfirm(pipe, "Do you want to add another person?");
        }

        // If we have records, save them to a file
        if (!personList.isEmpty()) {
            // Get filename from user
            String filename = SafeInput.getNonZeroLenString(pipe, "Enter filename to save data");

            // Automatically add .txt extension if not present
            if (!filename.toLowerCase().endsWith(".txt")) {
                filename = filename + ".txt";
            }

            // Save the records to file
            savePersonsToFile(personList, filename);

            // Display summary using Person objects
            System.out.println("File saved as: " + filename);
            System.out.println("\nPersons saved:");
            for (int i = 0; i < personList.size(); i++) {
                Person p = personList.get(i);
                System.out.println((i + 1) + ". " + p.formalName() + " (Age: " + p.getAge() + ")");
            }

            // Demonstrate different output formats
            System.out.println("\n=== Data Format Examples ===");
            if (!personList.isEmpty()) {
                Person firstPerson = personList.get(0);
                System.out.println("CSV: " + firstPerson.toCSV());
                System.out.println("JSON: " + firstPerson.toJSON());
                System.out.println("XML: " + firstPerson.toXML());
            }

        } else {
            System.out.println("No records to save.");
        }

        System.out.println("\nProgram completed. Thank you!");
    }

    /**
     * Saves the Person objects to a text file using their toCSV() method
     * @param persons ArrayList of Person objects to save
     * @param filename Name of file to create
     */
    private static void savePersonsToFile(ArrayList<Person> persons, String filename) {
        try {
            // Get current working directory and create path
            File workingDirectory = new File(System.getProperty("user.dir"));
            Path file = Paths.get(workingDirectory.getPath() + File.separator + filename);

            // Create BufferedWriter using NIO
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            // Write each Person using their toCSV() method
            for (Person person : persons) {
                String csvRecord = person.toCSV();  // Use Person's toCSV() method
                writer.write(csvRecord, 0, csvRecord.length());
                writer.newLine();
            }

            // Close the writer
            writer.close();
            System.out.println("\nData file written successfully!");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}