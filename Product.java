/**
 * The Product class represents a product with an ID, name, and price.
 */
public class Product {
    private int id; // The ID of the product
    private String name; // The name of the product
    private double price; // The price of the product

    /**
     * Constructs a new Product object with default values.
     */
    public Product() {
    }

    /**
     * Constructs a new Product object with the specified ID, name, and price.
     *
     * @param id    The ID of the product.
     * @param name  The name of the product.
     * @param price The price of the product.
     */
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Returns the ID of the product.
     *
     * @return The ID of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id The ID of the product to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The name of the product to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the product.
     *
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The price of the product to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
