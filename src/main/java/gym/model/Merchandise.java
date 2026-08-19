package gym.model;

public class Merchandise {
    private int id;
    private String itemName;
    private String category;
    private double price;
    private int stockQuantity;

    public Merchandise(int id, String itemName, String category, double price, int stockQuantity) {
        this.id = id;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}