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
 * ProductGenerator - Creates Product objects and saves them to CSV file
 * Uses ArrayList<Product> for object-oriented approach to data management
 * Demonstrates product data collection and file I/O
 *
 * Name: Tika Khadka
 */
public class ProductGenerator {

    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);
        ArrayList<Product> productList = new ArrayList<>();
        boolean continueInput = true;

        System.out.println("=== Product Data Generator (Object Version) ===");
        System.out.println("This program collects product information and saves it to a CSV file.");
        System.out.println("Data format: ID, Name, Description, Cost");
        System.out.println();

        // Collect product data from user
        while (continueInput) {
            System.out.println("Enter product information:");

            // Get ID (String)
            String id = SafeInput.getNonZeroLenString(pipe, "Enter Product ID (e.g., P001)");

            // Get Name (String)
            String name = SafeInput.getNonZeroLenString(pipe, "Enter Product Name");

            // Get Description (String)
            String description = SafeInput.getNonZeroLenString(pipe, "Enter Product Description");

            // Get Cost (double)
            double cost = SafeInput.getDouble(pipe, "Enter Product Cost");

            // Validate cost is not negative
            while (cost < 0) {
                System.out.println("Cost cannot be negative. Please enter a valid cost.");
                cost = SafeInput.getDouble(pipe, "Enter Product Cost");
            }

            // Create Product object and add to ArrayList
            Product product = new Product(name, description, id, cost);
            productList.add(product);


            // Ask if user wants to continue
            continueInput = SafeInput.getYNConfirm(pipe, "Do you want to add another product?");
        }

        // If we have records, save them to a file
        if (!productList.isEmpty()) {
            // Get filename from user
            String filename = SafeInput.getNonZeroLenString(pipe, "Enter filename to save data");

            // Automatically add .txt extension if not present
            if (!filename.toLowerCase().endsWith(".txt")) {
                filename = filename + ".txt";
            }

            // Save the records to file
            saveProductsToFile(productList, filename);

        }
    }

    /**
     * Saves the Product objects to a text file using their toCSV() method
     * @param products ArrayList of Product objects to save
     * @param filename Name of file to create
     */
    private static void saveProductsToFile(ArrayList<Product> products, String filename) {
        try {
            // Get current working directory and create path
            File workingDirectory = new File(System.getProperty("user.dir"));
            Path file = Paths.get(workingDirectory.getPath() + File.separator + filename);

            // Create BufferedWriter using NIO
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            // Write each Product using their toCSV() method
            for (Product product : products) {
                String csvRecord = product.toCSV();
                writer.write(csvRecord, 0, csvRecord.length());
                writer.newLine();
            }

            // Close the writer
            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}