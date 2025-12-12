/*******************************************************************
* Name: Aaryan Singh
* Date: December 11, 2025
* Assignment: SDC330 Course Project - Week 4
*
* This class is used to connect the SQL Database.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase {
    public static Connection connect(String database) {
        String url = "jdbc:sqlite:" + database;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
