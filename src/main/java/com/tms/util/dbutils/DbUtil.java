package com.tms.util.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Malsha Nishadini
 * Made JDBC connection and retrieved data from the database
 *
 */

public class DbUtil {
    private static final Logger LOGGER = Logger.getLogger(DbUtil.class.getName());

    static Connection conn = null;
    static PreparedStatement prepareStat = null;

    public static void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/tms", "root", "");
            if (conn != null) {
                LOGGER.info("Connected to the database!");
            } else {
                LOGGER.info("Failed to make connection!");
            }

        } catch (SQLException e) {
            LOGGER.info("MySQL Connection Failed!");
            e.printStackTrace();
        }

    }

    public  static Map<String, String> getDataFromEnquiryTable() {
        makeJDBCConnection();
        Map<String, String> dbResults = new HashMap<>();

        try {
            String getQueryStatement = "SELECT * FROM tblenquiry ORDER BY id DESC LIMIT 1";
            prepareStat = conn.prepareStatement(getQueryStatement);

            ResultSet rs = prepareStat.executeQuery();
            while (rs.next()) {
                dbResults.put("FullName", rs.getString("FullName"));
                dbResults.put("Status", rs.getString("Status"));
            }

            prepareStat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbResults;

    }

}

