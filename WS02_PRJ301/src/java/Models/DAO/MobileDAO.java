package Models.DAO;

import DBUtils.DBUtils;
import Models.DTO.Mobiles;
import java.sql.*;
import java.util.*;

public class MobileDAO {

    public MobileDAO() {
    }

    public List<Mobiles> getAllMobiles() throws Exception {
        List<Mobiles> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM Mobiles";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Mobiles(
                        rs.getString("mobileId"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getString("mobileName"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getBoolean("notSale")
                ));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean deleteMobile(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Mobiles WHERE mobileId = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean insertMobile(Mobiles m) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Mobiles (mobileId, mobileName, description, price, yearOfProduction, quantity, notSale) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getMobileId());
            ps.setString(2, m.getMobileName());
            ps.setString(3, m.getDescription());
            ps.setFloat(4, m.getPrice());
            ps.setInt(5, m.getYearOfProduction());
            ps.setInt(6, m.getQuantity());
            ps.setBoolean(7, m.isNotSale());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateMobile(Mobiles mobile) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Mobiles SET mobileName = ?, description = ?, price = ?, yearOfProduction = ?, quantity = ?, notSale = ? WHERE mobileId = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mobile.getMobileName());
            stmt.setString(2, mobile.getDescription());
            stmt.setFloat(3, mobile.getPrice());
            stmt.setInt(4, mobile.getYearOfProduction());
            stmt.setInt(5, mobile.getQuantity());
            stmt.setBoolean(6, mobile.isNotSale());
            stmt.setString(7, mobile.getMobileId());
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Mobiles> searchMobilesByIdOrName(String keyword) throws SQLException, ClassNotFoundException {
        List<Mobiles> list = new ArrayList<>();
        String sql = "SELECT * FROM Mobiles WHERE LOWER(mobileId) LIKE ? OR LOWER(mobileName) LIKE ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            String pattern = "%" + keyword.toLowerCase() + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mobiles m = new Mobiles(
                        rs.getString("mobileId"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getString("mobileName"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getBoolean("notSale")
                );
                list.add(m);
            }
        }
        return list;
    }

    public List<Mobiles> searchMobileByPriceRange(float minPrice, float maxPrice) throws SQLException, ClassNotFoundException {
        List<Mobiles> result = new ArrayList<>();
        String sql = "SELECT * FROM Mobiles WHERE price BETWEEN ? AND ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setFloat(1, minPrice);
            ps.setFloat(2, maxPrice);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mobiles m = new Mobiles(
                        rs.getString("mobileId"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getString("mobileName"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getBoolean("notSale")
                );
                result.add(m);
            }
        }
        return result;
    }
     public Mobiles getMobileById(String mobileId) throws ClassNotFoundException {
        Mobiles mobile = null;
        String query = "SELECT * FROM Mobiles WHERE mobileId = ?";
        
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, mobileId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                mobile = new Mobiles();
                mobile.setMobileId(rs.getString("mobileId"));
                mobile.setDescription(rs.getString("description"));
                mobile.setPrice(rs.getFloat("price"));
                mobile.setMobileName(rs.getString("mobileName"));
                mobile.setYearOfProduction(rs.getInt("yearOfProduction"));
                mobile.setQuantity(rs.getInt("quantity"));
                mobile.setNotSale(rs.getBoolean("notSale"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mobile;
    }

}
