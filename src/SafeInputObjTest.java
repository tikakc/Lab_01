import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * JUnit test class for SafeInputObj class
 * Tests all methods using simulated input streams
 * Note: These tests simulate user input for interactive methods
 *
 * Name: Tika Khadka
 */
class SafeInputObjTest {

    private SafeInputObj safeInputObj;

    /**
     * Set up test fixtures before each test
     * Creates test instances to use in tests
     */
    @BeforeEach
    void setUp() {
    }

    // Constructor Tests
    @Test
    @DisplayName("Test default constructor")
    void testDefaultConstructor() {
        SafeInputObj obj = new SafeInputObj();
        assertNotNull(obj);
    }

    @Test
    @DisplayName("Test parameterized constructor")
    void testParameterizedConstructor() {
        Scanner testScanner = new Scanner("test");
        SafeInputObj obj = new SafeInputObj(testScanner);
        assertNotNull(obj);
    }

    // Method Tests with Simulated Input
    @Test
    @DisplayName("Test getNonZeroLenString with valid input")
    void testGetNonZeroLenStringValid() {
        String input = "Hello World\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        String result = obj.getNonZeroLenString("Enter text");
        assertEquals("Hello World", result);
    }

    @Test
    @DisplayName("Test getNonZeroLenString with empty then valid input")
    void testGetNonZeroLenStringEmptyThenValid() {
        String input = "\nValid Input\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        String result = obj.getNonZeroLenString("Enter text");
        assertEquals("Valid Input", result);
    }

    @Test
    @DisplayName("Test getInt with valid input")
    void testGetIntValid() {
        String input = "42\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        int result = obj.getInt("Enter number");
        assertEquals(42, result);
    }

    @Test
    @DisplayName("Test getInt with invalid then valid input")
    void testGetIntInvalidThenValid() {
        String input = "abc\n25\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        int result = obj.getInt("Enter number");
        assertEquals(25, result);
    }

    @Test
    @DisplayName("Test getRangedInt with valid input")
    void testGetRangedIntValid() {
        String input = "15\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        int result = obj.getRangedInt("Enter number", 10, 20);
        assertEquals(15, result);
    }

    @Test
    @DisplayName("Test getRangedInt with out of range then valid input")
    void testGetRangedIntOutOfRangeThenValid() {
        String input = "5\n15\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        int result = obj.getRangedInt("Enter number", 10, 20);
        assertEquals(15, result);
    }

    @Test
    @DisplayName("Test getDouble with valid input")
    void testGetDoubleValid() {
        String input = "3.14\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        double result = obj.getDouble("Enter number");
        assertEquals(3.14, result, 0.001);
    }

    @Test
    @DisplayName("Test getDouble with invalid then valid input")
    void testGetDoubleInvalidThenValid() {
        String input = "abc\n2.718\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        double result = obj.getDouble("Enter number");
        assertEquals(2.718, result, 0.001);
    }

    @Test
    @DisplayName("Test getRangedDouble with valid input")
    void testGetRangedDoubleValid() {
        String input = "15.5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        double result = obj.getRangedDouble("Enter number", 10, 20);
        assertEquals(15.5, result, 0.001);
    }

    @Test
    @DisplayName("Test getRangedDouble with out of range then valid input")
    void testGetRangedDoubleOutOfRangeThenValid() {
        String input = "5.5\n12.3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        double result = obj.getRangedDouble("Enter number", 10, 20);
        assertEquals(12.3, result, 0.001);
    }

    @Test
    @DisplayName("Test getYNConfirm with Y input")
    void testGetYNConfirmYes() {
        String input = "Y\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        boolean result = obj.getYNConfirm("Continue");
        assertTrue(result);
    }

    @Test
    @DisplayName("Test getYNConfirm with N input")
    void testGetYNConfirmNo() {
        String input = "N\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        boolean result = obj.getYNConfirm("Continue");
        assertFalse(result);
    }

    @Test
    @DisplayName("Test getYNConfirm with case insensitive input")
    void testGetYNConfirmCaseInsensitive() {
        String input = "y\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        boolean result = obj.getYNConfirm("Continue");
        assertTrue(result);
    }

    @Test
    @DisplayName("Test getYNConfirm with invalid then valid input")
    void testGetYNConfirmInvalidThenValid() {
        String input = "maybe\nY\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        boolean result = obj.getYNConfirm("Continue");
        assertTrue(result);
    }

    @Test
    @DisplayName("Test getRegExString with valid pattern")
    void testGetRegExStringValid() {
        String input = "abc123\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        String result = obj.getRegExString("Enter alphanumeric", "[a-zA-Z0-9]+");
        assertEquals("abc123", result);
    }

    @Test
    @DisplayName("Test getRegExString with invalid then valid pattern")
    void testGetRegExStringInvalidThenValid() {
        String input = "abc@123\nabc123\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        String result = obj.getRegExString("Enter alphanumeric", "[a-zA-Z0-9]+");
        assertEquals("abc123", result);
    }

    @Test
    @DisplayName("Test getRegExString with email pattern")
    void testGetRegExStringEmailPattern() {
        String input = "test@example.com\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        SafeInputObj obj = new SafeInputObj(scanner);
        
        String result = obj.getRegExString("Enter email", "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        assertEquals("test@example.com", result);
    }
}
