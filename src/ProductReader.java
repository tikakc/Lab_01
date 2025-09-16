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
 * ProductReader - Reads Product data from CSV file and creates Product objects
 * Uses ArrayList<Product> for object-oriented approach to data reading
 * Demonstrates file I/O with Product objects
 *
 * Name: Tika Khadka
 */
public class ProductReader {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<Product> productList = new ArrayList<>();

        final int FIELDS_LENGTH = 4;

        System.out.println("=== Product Data Reader (Object Version) ===");
        System.out.println("Select a product data file to read and display.");

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

                // Read all lines and create Product objects
                while (reader.ready()) {
                    rec = reader.readLine();
                    if (rec != null && !rec.trim().isEmpty()) {
                        // Parse the CSV line and create Product object
                        Product product = parseProductFromCSV(rec);
                        if (product != null) {
                            productList.add(product);
                        }
                    }
                }
                reader.close();

                // Display formatted data using Product objects
                displayProductData(productList);

            } else {
                System.exit(0);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Parses a CSV line and creates a Product object
     * @param csvLine The CSV line to parse
     * @return Product object or null if parsing fails
     */
    private static Product parseProductFromCSV(String csvLine) {
        try {
            String[] fields = csvLine.split(",");

            if (fields.length == 4) {
                // Trim whitespace from all fields
                String id = fields[0].trim();
                String name = fields[1].trim();
                String description = fields[2].trim();
                double cost = Double.parseDouble(fields[3].trim());

                // Create and return Product object
                return new Product(name, description, id, cost);
            }
        } catch (NumberFormatException e) {
            // Handle parsing error silently
        }
        return null;
    }

    /**
     * Displays Product data in formatted table using Product object methods
     * @param products ArrayList of Product objects to display
     */
    private static void displayProductData(ArrayList<Product> products) {
        System.out.println("\n" + "=".repeat(100));
        System.out.printf("%-8s %-20s %-30s %-10s%n",
                "ID#", "Name", "Description", "Cost");
        System.out.println("=".repeat(100));

        // Display each Product using their methods
        for (Product product : products) {
            System.out.printf("%-8s %-20s %-30s $%-9.2f%n",
                    product.getID(),
                    product.getName(),
                    product.getDescription(),
                    product.getCost());
        }

        System.out.println("=".repeat(100));
    }

}