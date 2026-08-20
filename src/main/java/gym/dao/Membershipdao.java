package gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gym.config.DatabaseConnection;

public class Membershipdao {

    public boolean purchaseMembership(int userId, String type, double price) {
        String sql = "INSERT INTO memberships (user_id, membership_type, price) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, type);
            stmt.setDouble(3, price);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public double getTotalAnnualRevenue() {
        String sql = "SELECT SUM(price) AS total FROM memberships WHERE purchase_date >= NOW() - INTERVAL '1 year'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
        }
        return 0.0;
    }

    public double getUserTotalExpenses(int userId) {
        String sql = "SELECT SUM(price) AS total FROM memberships WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
        }
        return 0.0;
    }
}