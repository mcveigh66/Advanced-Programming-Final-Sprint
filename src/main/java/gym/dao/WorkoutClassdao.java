package gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gym.config.DatabaseConnection;

public class WorkoutClassdao {

    public boolean createClass(String className, String schedule, int trainerId) {
        String sql = "INSERT INTO workout_classes (class_name, schedule, trainer_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, className);
            stmt.setString(2, schedule);
            stmt.setInt(3, trainerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public void listAllClasses() {
        String sql = "SELECT c.id, c.class_name, c.schedule, u.username AS trainer FROM workout_classes c LEFT JOIN users u ON c.trainer_id = u.id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Available Workout Classes ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Schedule: %s | Trainer: %s\n",
                        rs.getInt("id"), rs.getString("class_name"), rs.getString("schedule"), rs.getString("trainer"));
            }
        } catch (SQLException e) {
        }
    }

    public void listTrainerClasses(int trainerId) {
        String sql = "SELECT id, class_name, schedule FROM workout_classes WHERE trainer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, trainerId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n--- Your Assigned Classes ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Schedule: %s\n",
                        rs.getInt("id"), rs.getString("class_name"), rs.getString("schedule"));
            }
        } catch (SQLException e) {
        }
    }

    public boolean deleteClass(int classId) {
        String sql = "DELETE FROM workout_classes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, classId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}