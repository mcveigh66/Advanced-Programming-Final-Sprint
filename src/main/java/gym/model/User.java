package gym.model;

public class User {
    private final int id;
    private final String username;
    private final String passwordHash;
    private final String email;
    private final String phone;
    private final String address;
    private final String role; 

    public User(int id, String username, String passwordHash, String email, String phone, String address, String role) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getRole() { return role; }
}
