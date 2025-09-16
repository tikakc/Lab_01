import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for Product class
 * Tests constructors, setters, and all additional methods
 *
 * Name: Tika Khadka
 */
class ProductTest {

    private Product testProduct;
    private Product defaultProduct;
    private Product partialProduct;

    /**
     * Set up test fixtures before each test
     * Creates test instances to use in tests
     */
    @BeforeEach
    void setUp() {
        testProduct = new Product("Laptop", "High-performance laptop", "P001", 999.99);
        defaultProduct = new Product();
        partialProduct = new Product("Mouse", "M001");
    }

    // Constructor Tests
    @Test
    @DisplayName("Test full constructor")
    void testFullConstructor() {
        assertEquals("Laptop", testProduct.getName());
        assertEquals("High-performance laptop", testProduct.getDescription());
        assertEquals("P001", testProduct.getID());
        assertEquals(999.99, testProduct.getCost(), 0.01);
    }

    @Test
    @DisplayName("Test default constructor")
    void testDefaultConstructor() {
        assertEquals("", defaultProduct.getName());
        assertEquals("", defaultProduct.getDescription());
        assertEquals("", defaultProduct.getID());
        assertEquals(0.0, defaultProduct.getCost(), 0.01);
    }

    @Test
    @DisplayName("Test partial constructor (name and ID)")
    void testPartialConstructor() {
        assertEquals("Mouse", partialProduct.getName());
        assertEquals("", partialProduct.getDescription());
        assertEquals("M001", partialProduct.getID());
        assertEquals(0.0, partialProduct.getCost(), 0.01);
    }

    @Test
    @DisplayName("Test constructor with name, ID, and cost")
    void testConstructorWithCost() {
        Product product = new Product("Keyboard", "K001", 49.99);
        assertEquals("Keyboard", product.getName());
        assertEquals("", product.getDescription());
        assertEquals("K001", product.getID());
        assertEquals(49.99, product.getCost(), 0.01);
    }

    @Test
    @DisplayName("Test constructor with null values")
    void testConstructorWithNulls() {
        Product nullProduct = new Product(null, null, null, 50.0);
        assertEquals("", nullProduct.getName());
        assertEquals("", nullProduct.getDescription());
        assertEquals("", nullProduct.getID());
        assertEquals(50.0, nullProduct.getCost(), 0.01);
    }

    @Test
    @DisplayName("Test constructor with negative cost")
    void testConstructorWithNegativeCost() {
        Product product = new Product("Test", "Description", "T001", -10.0);
        assertEquals(0.0, product.getCost(), 0.01);
    }

    // Setter Tests
    @Test
    @DisplayName("Test setName")
    void testSetName() {
        testProduct.setName("Gaming Laptop");
        assertEquals("Gaming Laptop", testProduct.getName());
    }

    @Test
    @DisplayName("Test setName with null")
    void testSetNameNull() {
        testProduct.setName(null);
        assertEquals("", testProduct.getName());
    }

    @Test
    @DisplayName("Test setDescription")
    void testSetDescription() {
        testProduct.setDescription("Updated description");
        assertEquals("Updated description", testProduct.getDescription());
    }

    @Test
    @DisplayName("Test setDescription with null")
    void testSetDescriptionNull() {
        testProduct.setDescription(null);
        assertEquals("", testProduct.getDescription());
    }

    @Test
    @DisplayName("Test setCost with valid cost")
    void testSetCostValid() {
        testProduct.setCost(1299.99);
        assertEquals(1299.99, testProduct.getCost(), 0.01);
    }

    @Test
    @DisplayName("Test setCost with zero")
    void testSetCostZero() {
        testProduct.setCost(0.0);
        assertEquals(0.0, testProduct.getCost(), 0.01);
    }

    @Test
    @DisplayName("Test setCost with negative cost")
    void testSetCostNegative() {
        double originalCost = testProduct.getCost();
        testProduct.setCost(-50.0); // Should not change
        assertEquals(originalCost, testProduct.getCost(), 0.01);
    }

    // Additional Method Tests
    @Test
    @DisplayName("Test toCSV method")
    void testToCSV() {
        String expected = "P001, Laptop, High-performance laptop, 999.99";
        assertEquals(expected, testProduct.toCSV());
    }

    @Test
    @DisplayName("Test toJSON method")
    void testToJSON() {
        String json = testProduct.toJSON();
        assertTrue(json.contains("\"ID\": \"P001\""));
        assertTrue(json.contains("\"name\": \"Laptop\""));
        assertTrue(json.contains("\"description\": \"High-performance laptop\""));
        assertTrue(json.contains("\"cost\": 999.99"));
    }

    @Test
    @DisplayName("Test toXML method")
    void testToXML() {
        String xml = testProduct.toXML();
        assertTrue(xml.contains("<ID>P001</ID>"));
        assertTrue(xml.contains("<name>Laptop</name>"));
        assertTrue(xml.contains("<description>High-performance laptop</description>"));
        assertTrue(xml.contains("<cost>999.99</cost>"));
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        String toString = testProduct.toString();
        assertTrue(toString.contains("Product{"));
        assertTrue(toString.contains("ID='P001'"));
        assertTrue(toString.contains("name='Laptop'"));
        assertTrue(toString.contains("description='High-performance laptop'"));
        assertTrue(toString.contains("cost=999.99"));
    }

    @Test
    @DisplayName("Test equals method - same object")
    void testEqualsSameObject() {
        assertTrue(testProduct.equals(testProduct));
    }

    @Test
    @DisplayName("Test equals method - equal objects")
    void testEqualsEqualObjects() {
        Product sameProduct = new Product("Laptop", "High-performance laptop", "P001", 999.99);
        assertTrue(testProduct.equals(sameProduct));
    }

    @Test
    @DisplayName("Test equals method - different objects")
    void testEqualsDifferentObjects() {
        Product differentProduct = new Product("Desktop", "High-performance desktop", "P002", 1299.99);
        assertFalse(testProduct.equals(differentProduct));
    }

    @Test
    @DisplayName("Test equals method - null object")
    void testEqualsNull() {
        assertFalse(testProduct.equals(null));
    }

    @Test
    @DisplayName("Test equals method - different class")
    void testEqualsDifferentClass() {
        String notAProduct = "Not a product";
        assertFalse(testProduct.equals(notAProduct));
    }

    @Test
    @DisplayName("Test equals method - different cost")
    void testEqualsDifferentCost() {
        Product differentCost = new Product("Laptop", "High-performance laptop", "P001", 899.99);
        assertFalse(testProduct.equals(differentCost));
    }

    // Edge Case Tests
    @Test
    @DisplayName("Test with empty strings")
    void testEmptyStrings() {
        Product emptyProduct = new Product("", "", "", 0.0);
        assertEquals("", emptyProduct.getName());
        assertEquals("", emptyProduct.getDescription());
        assertEquals("", emptyProduct.getID());
        assertEquals(0.0, emptyProduct.getCost(), 0.01);
    }

    @Test
    @DisplayName("Test cost precision")
    void testCostPrecision() {
        Product preciseProduct = new Product("Test", "Test", "T001", 123.456789);
        assertEquals(123.456789, preciseProduct.getCost(), 0.000001);
    }
}
