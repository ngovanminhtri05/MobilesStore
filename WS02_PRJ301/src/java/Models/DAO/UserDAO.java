package Models.DAO;

import DBUtils.DBUtils;
import Models.DTO.User;
import java.sql.*;

public class UserDAO {

    public UserDAO() {}

    public User checkLogin(String userID, int password) {
        String sql = "SELECT userId, fullName, role FROM Users WHERE userId = ? AND password = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userID);
            ps.setInt(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getString("userId"),
                         // You can ignore or securely handle this in real apps
                        rs.getString("fullName"),
                        rs.getInt("role")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     public static void main(String[] args) {
        UserDAO dao = new UserDAO();

        // Replace with actual values from your database
        String userId = "tri";
        int password = 123;

        User user = dao.checkLogin(userId, password);

        if (user != null) {
            System.out.println("Login success:");
            System.out.println("UserID: " + user.getUserId());
            System.out.println("Full Name: " + user.getFullName());
            System.out.println("Role: " + user.getRole());
        } else {
            System.out.println("Login failed: Invalid credentials.");
        }
    }
}
