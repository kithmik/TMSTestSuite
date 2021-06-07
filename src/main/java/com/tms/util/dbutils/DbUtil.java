package com.tms.util.dbutils;

import com.opencsv.exceptions.CsvValidationException;
import com.tms.util.csvutils.CsvUtils;
import java.io.IOException;
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
            String[] credentials = CsvUtils.readCSV();
            conn = DriverManager.getConnection(credentials[0], credentials[1], credentials[2]);
            if (conn != null) {
                LOGGER.info("Connected to the database!");
            } else {
                LOGGER.info("Failed to make connection!");
            }

        } catch (SQLException e) {
            LOGGER.info("MySQL Connection Failed!");
            e.printStackTrace();
        } catch (CsvValidationException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static Map<String, String> getDataFromEnquiryTable() {
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

    public static Map<String, String> getDataFromBookingTable() {
        makeJDBCConnection();
        Map<String, String> dbResults = new HashMap<>();

        try {
            String getQueryStatement = "SELECT * FROM tblbooking ORDER BY BookingId DESC LIMIT 1";
            prepareStat = conn.prepareStatement(getQueryStatement);

            ResultSet rs = prepareStat.executeQuery();
            while (rs.next()) {
                dbResults.put("FromDate", rs.getString("FromDate"));
                dbResults.put("ToDate", rs.getString("ToDate"));
                dbResults.put("Comment", rs.getString("Comment"));
                dbResults.put("status", rs.getString("status"));
            }

            prepareStat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbResults;

    }

    public static Map<String, String> getDataFromTourPackagesTable() {
        makeJDBCConnection();
        Map<String, String> dbResults = new HashMap<>();

        try {
            String getQueryStatement = "SELECT * FROM tbltourpackages WHERE PackageId IN(SELECT MAX(PackageId) FROM tbltourpackages)";
            prepareStat = conn.prepareStatement(getQueryStatement);
            ResultSet rs = prepareStat.executeQuery();
            while (rs.next()) {
                dbResults.put("PackageName", rs.getString("PackageName"));
                dbResults.put("PackagePrice", rs.getString("PackagePrice"));
                dbResults.put("PackageFetures", rs.getString("PackageFetures"));
            }
            prepareStat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbResults;

    }

    public static Map<String, String> getDataFromUserTable(String gmail) {
        makeJDBCConnection();
        Map<String, String> dbResults = new HashMap<>();

        try {
            String getQueryStatement = "SELECT * FROM tblusers WHERE EmailId ='" + gmail +"'";
            prepareStat = conn.prepareStatement(getQueryStatement);
            ResultSet rs = prepareStat.executeQuery();
            while (rs.next()) {
                dbResults.put("FullName", rs.getString("FullName"));
                dbResults.put("MobileNumber", rs.getString("MobileNumber"));
            }
            prepareStat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbResults;
    }


    public static String getRegisteredUser(String user) {
        makeJDBCConnection();
        String result ="";
        result = "fail";


        try {
            String getQueryStatement = "SELECT * FROM tblusers WHERE FullName ='" + user +"'";
            prepareStat = conn.prepareStatement(getQueryStatement);
            ResultSet rs = prepareStat.executeQuery();

            while (rs.next()) {
                result = "success";
            }

            prepareStat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return result;
    }
}
