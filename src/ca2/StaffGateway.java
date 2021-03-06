/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rorypb
 */
public class StaffGateway {

    //Should match the column names in your DB
    private static final String TABLE_NAME = "Driver";
    private static final String COLUMN_ID = "driverID";
    private static final String COLUMN_FNAME = "driverfName";
    private static final String COLUMN_LNAME = "driverlName";
    private static final String COLUMN_EMAIL = "emailAdd";
    private static final String COLUMN_CONTACT = "contactNo";
    private static final String COLUMN_DATE = "startDate";
    private static final String COLUMN_LICENSE = "licenseNo";
    private static final String COLUMN_DROVE = "hoursDrove";

    private Connection cConnection;

    public StaffGateway(Connection connection) {
        cConnection = connection;
    }

    public boolean insertDriver(Driver driver) throws SQLException {

        boolean success = true; //set so success is defaulted to true

        String query; //Query for SQL commands to communicate with DB
        PreparedStatement stmt = null; //Statement for executing the SQL query
        ResultSet keys = null; //Auto Key

        int rowsAffected = 0;
        int dID;

        //Required fields to use the Insert into the Table
        query = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_FNAME + ", "
                + COLUMN_LNAME + ", "
                + COLUMN_EMAIL + ", "
                + COLUMN_CONTACT + ", "
                + COLUMN_DATE + ", "
                + COLUMN_LICENSE + ", "
                + COLUMN_DROVE
                + ") VALUES (?,?,?,?,?,?,?)";

        try {
            stmt = cConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, driver.getFirstName());
            stmt.setString(2, driver.getLastName());
            stmt.setString(3, driver.getEmailAdd());
            stmt.setInt(4, driver.getContactNo());
            stmt.setDate(5, new Date(driver.getStartDate().getTime()));
            stmt.setString(6, driver.getLicense());
            stmt.setDouble(7, driver.getHoursDrove());

            rowsAffected = stmt.executeUpdate();//Update the table

            if (rowsAffected == 1) {
                // if one row was inserted, retrieve the id assigned to that row and create a Bus to return
                keys = stmt.getGeneratedKeys();//Auto Key
                keys.next();

                dID = keys.getInt(1);

                driver.setStaffID(dID);
            } else {
                System.err.println("No rows changed");
                success = false; //Change success if no rows are changed
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            success = false;
        } finally { //close keys
            if (keys != null) {
                keys.close();
            }
            if (stmt != null) { //close statement
                stmt.close();
                //Goo practivce to close kets
            }
        }
        return success; //return the driver if created or null if issue

    }

    public ArrayList<Driver> viewDriver() throws SQLException {
        
        String query; //for sql query
        Statement stmt; //For executing SQL
        
        ResultSet resultSet; //Represents the results of the sql query
        ArrayList<Driver> driver; //Arraylist with the driver objects
        
        //Fields to create a new driver object
        int staffID;
        String firstName;
        String lastName;
        String emailAdd;
        int contactNo;
        String license;
        Date startDate = null;
        double hoursDrove;
        
        //New driver object
        Driver driver1;
        //Executing of SQL statement 
        query = "SELECT * FROM  " + TABLE_NAME; // Select all from the table ie: driver
        stmt = this.cConnection.createStatement();
        resultSet = stmt.executeQuery(query);
        
        //New empty arraylist for object
        driver = new ArrayList<Driver>();
        while(resultSet.next()){
            staffID = resultSet.getInt(COLUMN_ID);
            firstName = resultSet.getString(COLUMN_FNAME);
            lastName = resultSet.getString(COLUMN_LNAME);
            emailAdd = resultSet.getString(COLUMN_EMAIL);
            contactNo = resultSet.getInt(COLUMN_CONTACT);
            license = resultSet.getString(COLUMN_LICENSE);
            startDate = resultSet.getDate(COLUMN_DATE);
            hoursDrove = resultSet.getDouble(COLUMN_DROVE);
                    
            driver.add(new Driver(staffID, firstName, lastName, emailAdd, contactNo, license, startDate, hoursDrove));
            
        }
        return driver;
        
    }
}

