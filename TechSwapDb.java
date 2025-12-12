/*******************************************************************
* Name: Aaryan Singh
* Date: December 11, 2025
* Assignment: SDC330 Course Project - Week 4
*
* This class is used to handle the tables using SQL.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TechSwapDb {

    public static boolean createTables(Connection conn) {
        String sqlCustomers = "CREATE TABLE IF NOT EXISTS Customers (\n"
                + " customer_id integer PRIMARY KEY,\n"
                + " full_name varchar(50),\n"
                + " phone_number varchar(20),\n"
                + " total_sales integer\n"
                + ");";

        String sqlInventory = "CREATE TABLE IF NOT EXISTS Inventory (\n"
                + " device_id integer PRIMARY KEY,\n"
                + " customer_id integer,\n"
                + " type varchar(20),\n"
                + " model varchar(50),\n"
                + " condition varchar(20),\n"
                + " cost real,\n"
                + " status varchar(20),\n"
                + " storage_gb integer,\n"
                + " ram_gb integer\n"
                + ");";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sqlCustomers);
            stmt.execute(sqlInventory);
            System.out.println("Tables created successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // --- CUSTOMER OPERATIONS ---

    public static void addCustomer(Connection conn, Customer c) {
        String sql = "INSERT INTO Customers(full_name, phone_number, total_sales) VALUES(?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, c.getName());
            pst.setString(2, c.getPhoneNumber());
            pst.setInt(3, c.getTotalSales());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Customer getCustomer(Connection conn, String name) {
        String sql = "SELECT * FROM Customers WHERE full_name LIKE ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("full_name"),
                    rs.getString("phone_number"),
                    rs.getInt("total_sales")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // --- INVENTORY OPERATIONS ---

    public static void addDevice(Connection conn, ElectronicDevice device, int customerId) {
        String sql = "INSERT INTO Inventory(customer_id, type, model, condition, cost, status, storage_gb, ram_gb) VALUES(?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, customerId);
            pst.setString(2, device.getType());
            pst.setString(3, device.getModel());
            pst.setString(4, device.getConditionString());
            pst.setDouble(5, device.calculateValue());
            pst.setString(6, device.getStatus());

            if (device instanceof Smartphone) {
                pst.setInt(7, ((Smartphone) device).getStorageCapacityGB());
                pst.setInt(8, 0);
            } else if (device instanceof Laptop) {
                pst.setInt(7, 0);
                pst.setInt(8, ((Laptop) device).getRamSizeGB());
            }

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<ElectronicDevice> getAllInventory(Connection conn) {
        ArrayList<ElectronicDevice> inventory = new ArrayList<>();
        String sql = "SELECT * FROM Inventory";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String type = rs.getString("type");
                int id = rs.getInt("device_id");
                String model = rs.getString("model");
                int age = 0;
                double basePrice = rs.getDouble("cost");
                String condStr = rs.getString("condition");
                ElectronicDevice.Condition condition = ElectronicDevice.Condition.valueOf(condStr);
                
                ElectronicDevice device = null;

                if (type.equals("Smartphone")) {
                    int storage = rs.getInt("storage_gb");
                    device = new Smartphone(model, age, condition, basePrice, storage);
                } else if (type.equals("Laptop")) {
                    int ram = rs.getInt("ram_gb");
                    device = new Laptop(model, age, condition, basePrice, ram);
                }

                if (device != null) {
                    device.setId(id);
                    device.setCustomerId(rs.getInt("customer_id"));
                    device.setStatus(rs.getString("status"));
                    inventory.add(device);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return inventory;
    }

    public static void updateDeviceStatus(Connection conn, int deviceId, String newStatus) {
        String sql = "UPDATE Inventory SET status=? WHERE device_id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newStatus);
            pst.setInt(2, deviceId);
            pst.executeUpdate();
            System.out.println("Device " + deviceId + " updated to " + newStatus);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteDevice(Connection conn, int deviceId) {
        String sql = "DELETE FROM Inventory WHERE device_id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, deviceId);
            pst.executeUpdate();
            System.out.println("Device " + deviceId + " deleted from database.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}