package gym;

import java.util.Scanner;

import gym.dao.Membershipdao;
import gym.dao.Merchandisedao;
import gym.dao.Userdao;
import gym.dao.WorkoutClassdao;
import gym.model.User;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Userdao userDAO = new Userdao();
    private static final Membershipdao membershipDAO = new Membershipdao();
    private static final WorkoutClassdao classDAO = new WorkoutClassdao();
    private static final Merchandisedao merchandiseDAO = new Merchandisedao();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("   GYM MANAGEMENT SYSTEM (2026)  ");
            System.out.println("==================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void register() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Role (Admin / Trainer / Member): ");
        String role = scanner.nextLine();

        if (userDAO.registerUser(username, password, email, phone, address, role)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Ensure username/email are unique.");
        }
    }

    private static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userDAO.loginUser(username, password);
        if (user != null) {
            System.out.println("\nLogin successful! Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
            showRoleSpecificMenu(user);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void showRoleSpecificMenu(User user) {
        String role = user.getRole();
        while (true) {
            System.out.println("\n--- Menu [" + role + "] ---");

            switch (role) {
                case "Admin" -> {
                    System.out.println("1. View all users & contact info");
                    System.out.println("2. Delete users from system");
                    System.out.println("3. Track total annual membership revenue");
                    System.out.println("4. Add new merchandise items & set prices");
                    System.out.println("5. View merchandise stock & total valuation");
                    System.out.println("6. Create workout class");
                    System.out.println("7. Delete workout class");
                    System.out.println("8. Logout");

                    int opt = scanner.nextInt();
                    scanner.nextLine();
                    switch (opt) {
                        case 1 -> userDAO.getAllUsers().forEach(u -> 
                            System.out.printf("ID: %d | User: %s | Email: %s | Phone: %s | Role: %s\n", 
                                u.getId(), u.getUsername(), u.getEmail(), u.getPhone(), u.getRole()));
                        case 2 -> {
                            System.out.print("Enter User ID to delete: ");
                            int id = scanner.nextInt();
                            System.out.println(userDAO.deleteUser(id) ? "User deleted." : "Failed to delete.");
                        }
                        case 3 -> System.out.printf("Annual Revenue: $%.2f\n", membershipDAO.getTotalAnnualRevenue());
                        case 4 -> {
                            System.out.print("Item Name: "); String name = scanner.nextLine();
                            System.out.print("Category: "); String cat = scanner.nextLine();
                            System.out.print("Price: "); double p = scanner.nextDouble();
                            System.out.print("Stock: "); int s = scanner.nextInt();
                            merchandiseDAO.addMerchandise(name, cat, p, s);
                        }
                        case 5 -> merchandiseDAO.listStockAndValuation();
                        case 6 -> {
                            System.out.print("Class Name: "); String cName = scanner.nextLine();
                            System.out.print("Schedule: "); String sched = scanner.nextLine();
                            classDAO.createClass(cName, sched, user.getId());
                        }
                        case 7 -> {
                            System.out.print("Class ID to delete: "); int cid = scanner.nextInt();
                            classDAO.deleteClass(cid);
                        }
                        case 8 -> { return; }
                    }
                }

                case "Trainer" -> {
                    System.out.println("1. Create workout class");
                    System.out.println("2. View list of self-assigned classes");
                    System.out.println("3. Purchase a gym membership");
                    System.out.println("4. View merchandise available for purchase");
                    System.out.println("5. Logout");

                    int opt = scanner.nextInt();
                    scanner.nextLine();
                    switch (opt) {
                        case 1 -> {
                            System.out.print("Class Name: "); String cName = scanner.nextLine();
                            System.out.print("Schedule: "); String sched = scanner.nextLine();
                            classDAO.createClass(cName, sched, user.getId());
                        }
                        case 2 -> classDAO.listTrainerClasses(user.getId());
                        case 3 -> {
                            System.out.print("Membership Type: "); String type = scanner.nextLine();
                            System.out.print("Price: "); double price = scanner.nextDouble();
                            membershipDAO.purchaseMembership(user.getId(), type, price);
                        }
                        case 4 -> Merchandisedao.listMerchandiseForPurchase();
                        case 5 -> { return; }
                    }
                }

                case "Member" -> {
                    System.out.println("1. Purchase a gym membership");
                    System.out.println("2. View merchandise available for purchase");
                    System.out.println("3. Browse all available workout classes");
                    System.out.println("4. View personal total membership expenses");
                    System.out.println("5. Logout");

                    int opt = scanner.nextInt();
                    scanner.nextLine();
                    switch (opt) {
                        case 1 -> {
                            System.out.print("Membership Type: "); String type = scanner.nextLine();
                            System.out.print("Price: "); double price = scanner.nextDouble();
                            membershipDAO.purchaseMembership(user.getId(), type, price);
                        }
                        case 2 -> Merchandisedao.listMerchandiseForPurchase();
                        case 3 -> classDAO.listAllClasses();
                        case 4 -> System.out.printf("Total Spent: $%.2f\n", membershipDAO.getUserTotalExpenses(user.getId()));
                        case 5 -> { return; }
                    }
                }
            }
        }
    }

    public static Userdao getUserDAO() {
        return userDAO;
    }
}
