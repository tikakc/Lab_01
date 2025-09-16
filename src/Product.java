/**
 * Product class represents a product with basic information
 * Used for storing and manipulating product data in object form
 *
 * Name: Tika Khadka
 */
public class Product {
    // Fields
    private String name;
    private String description;
    private String ID;
    private double cost;

    /**
     * Default constructor - initializes product with empty values
     */
    public Product() {
        this.name = "";
        this.description = "";
        this.ID = "";
        this.cost = 0.0;
    }

    /**
     * Full constructor with all fields
     * @param name Name of the product
     * @param description Description of the product
     * @param ID Unique identifier (should never change)
     * @param cost Cost of the product
     */
    public Product(String name, String description, String ID, double cost) {
        this.name = name != null ? name : "";
        this.description = description != null ? description : "";
        this.ID = ID != null ? ID : "";
        this.cost = cost >= 0 ? cost : 0.0;
    }

    /**
     * Constructor with name and ID only
     * @param name Name of the product
     * @param ID Unique identifier
     */
    public Product(String name, String ID) {
        this(name, "", ID, 0.0);
    }

    /**
     * Constructor with name, ID, and cost
     * @param name Name of the product
     * @param ID Unique identifier
     * @param cost Cost of the product
     */
    public Product(String name, String ID, double cost) {
        this(name, "", ID, cost);
    }

    // Getters
    /**
     * Gets the product name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the product description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the product ID
     * @return ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Gets the product cost
     * @return cost
     */
    public double getCost() {
        return cost;
    }

    // Setters (ID should not have setter as it should never change)
    /**
     * Sets the product name
     * @param name Name to set
     */
    public void setName(String name) {
        this.name = name != null ? name : "";
    }

    /**
     * Sets the product description
     * @param description Description to set
     */
    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }

    /**
     * Sets the product cost with validation
     * @param cost Cost to set (must be >= 0)
     */
    public void setCost(double cost) {
        if (cost >= 0) {
            this.cost = cost;
        }
    }

    /**
     * Returns CSV representation of the product
     * @return CSV string in format: ID, name, description, cost
     */
    public String toCSV() {
        return ID + ", " + name + ", " + description + ", " + cost;
    }

    /**
     * Returns JSON representation of the product
     * @return JSON formatted string representing the product object
     */
    public String toJSON() {
        return "{\n" +
                "  \"ID\": \"" + ID + "\",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"description\": \"" + description + "\",\n" +
                "  \"cost\": " + cost + "\n" +
                "}";
    }

    /**
     * Returns XML representation of the product
     * @return XML formatted string representing the product object
     */
    public String toXML() {
        return "<Product>\n" +
                "  <ID>" + ID + "</ID>\n" +
                "  <name>" + name + "</name>\n" +
                "  <description>" + description + "</description>\n" +
                "  <cost>" + cost + "</cost>\n" +
                "</Product>";
    }

    /**
     * String representation of the product object
     * @return String representation showing all field values
     */
    @Override
    public String toString() {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }

    /**
     * Checks equality with another Product object
     * @param obj Object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product product = (Product) obj;

        return Double.compare(product.cost, cost) == 0 &&
                ID.equals(product.ID) &&
                name.equals(product.name) &&
                description.equals(product.description);
    }
}
