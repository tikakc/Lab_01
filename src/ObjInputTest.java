import java.util.Scanner;

/**
 * ObjInputTest - Demonstration program for SafeInputObj methods
 * Tests each method interactively to show they work properly
 * This program demonstrates the object-oriented approach to safe input
 *
 * Name: Tika Khadka
 */
public class ObjInputTest {

    public static void main(String[] args) {
        // Create SafeInputObj instance using default constructor
        SafeInputObj input = new SafeInputObj();
        
        System.out.println("=== SafeInputObj Method Demonstration ===");
        System.out.println("This program demonstrates all SafeInputObj methods");
        System.out.println("Follow the prompts to test each method\n");

        // Test getNonZeroLenString
        System.out.println("1. Testing getNonZeroLenString method:");
        String name = input.getNonZeroLenString("Enter your name (cannot be empty)");
        System.out.println("You entered: " + name);
        System.out.println();

        // Test getInt
        System.out.println("2. Testing getInt method:");
        int age = input.getInt("Enter your age");
        System.out.println("You entered: " + age);
        System.out.println();

        // Test getRangedInt
        System.out.println("3. Testing getRangedInt method:");
        int score = input.getRangedInt("Enter a test score", 0, 100);
        System.out.println("You entered: " + score);
        System.out.println();

        // Test getDouble
        System.out.println("4. Testing getDouble method:");
        double price = input.getDouble("Enter a price");
        System.out.println("You entered: $" + String.format("%.2f", price));
        System.out.println();

        // Test getRangedDouble
        System.out.println("5. Testing getRangedDouble method:");
        double gpa = input.getRangedDouble("Enter your GPA", 0, 4);
        System.out.println("You entered: " + gpa);
        System.out.println();

        // Test getYNConfirm
        System.out.println("6. Testing getYNConfirm method:");
        boolean confirm = input.getYNConfirm("Do you like programming?");
        System.out.println("You answered: " + (confirm ? "Yes" : "No"));
        System.out.println();

        // Test getRegExString
        System.out.println("7. Testing getRegExString method:");
        String email = input.getRegExString("Enter your email address", 
            "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        System.out.println("You entered: " + email);
        System.out.println();

        // Test another RegEx pattern
        System.out.println("8. Testing getRegExString with phone pattern:");
        String phone = input.getRegExString("Enter phone number (format: XXX-XXX-XXXX)", 
            "\\d{3}-\\d{3}-\\d{4}");
        System.out.println("You entered: " + phone);
        System.out.println();

        // Display summary
        System.out.println("=== Summary of Your Inputs ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Test Score: " + score);
        System.out.println("Price: $" + String.format("%.2f", price));
        System.out.println("GPA: " + gpa);
        System.out.println("Likes Programming: " + (confirm ? "Yes" : "No"));
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);

        System.out.println("\n=== SafeInputObj Method Testing Complete ===");
        System.out.println("All methods have been successfully demonstrated!");
        
        // Optional: Test with different constructor
        System.out.println("\n=== Testing Alternative Constructor ===");
        Scanner customScanner = new Scanner(System.in);
        SafeInputObj customInput = new SafeInputObj(customScanner);
        
        boolean testMore = customInput.getYNConfirm("Would you like to test one more input?");
        if (testMore) {
            String finalTest = customInput.getNonZeroLenString("Enter any text for final test");
            System.out.println("Final test input: " + finalTest);
        }
        
        System.out.println("Program completed successfully!");
    }
}
