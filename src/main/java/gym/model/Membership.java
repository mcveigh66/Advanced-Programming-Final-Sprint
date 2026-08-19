package gym.model;

import java.sql.Timestamp;

public class Membership {
    private int id;
    private int userId;
    private String membershipType;
    private double price;
    private Timestamp purchaseDate;

    public Membership(int id, int userId, String membershipType, double price, Timestamp purchaseDate) {
        this.id = id;
        this.userId = userId;
        this.membershipType = membershipType;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Timestamp getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Timestamp purchaseDate) { this.purchaseDate = purchaseDate; }
}