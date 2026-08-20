package gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gym.config.DatabaseConnection;

public class Merchandisedao {

    public boolean addMerchandise(String name, String category, double price, int stock) {
        String sql = "INSERT INTO merchandise (item_name, category, price, stock_quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setDouble(3, price);
            stmt.setInt(4, stock);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void listMerchandiseForPurchase() {
        String sql = "SELECT id, item_name, category, price FROM merchandise WHERE stock_quantity > 0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Merchandise Available ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Item: %s | Category: %s | Price: $%.2f\n",
                        rs.getInt("id"), rs.getString("item_name"), rs.getString("category"), rs.getDouble("price"));
            }
        } catch (SQLException e) {
        }
    }

    public void listStockAndValuation() {
        String sql = "SELECT item_name, price, stock_quantity, (price * stock_quantity) AS valuation FROM merchandise";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Stock & Total Valuation ---");
            double totalValuation = 0;
            while (rs.next()) {
                double val = rs.getDouble("valuation");
                totalValuation += val;
                System.out.printf("Item: %s | Price: $%.2f | Stock: %d | Valuation: $%.2f\n",
                        rs.getString("item_name"), rs.getDouble("price"), rs.getInt("stock_quantity"), val);
            }
            System.out.printf(">>> Total Inventory Valuation: $%.2f <<<\n", totalValuation);
        } catch (SQLException e) {
        }
    }
}
