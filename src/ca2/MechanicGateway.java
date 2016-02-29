package ca2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rorypb
 */
public class MechanicGateway {


    //Should match the column names in your DB
    private static final String TABLE_NAME = "Mechanic";
    private static final String COLUMN_ID = "mechanicID";
    private static final String COLUMN_FNAME = "mechanicfName";
    private static final String COLUMN_LNAME = "mechaniclName";
    private static final String COLUMN_EMAIL = "emailAdd";
    private static final String COLUMN_CONTACT = "contactNo";
    private static final String COLUMN_SERVE = "servicesCompleted";
    private static final String COLUMN_DATE = "startDate";
    private static final String COLUMN_OT = "overTime";

    private Connection cConnection;

    public MechanicGateway(Connection connection) {
        cConnection = connection;
    }

    public boolean insertMechanic(Mechanic mechanic) throws SQLException {

        boolean success = true; //set so success is defaulted to true

        String query; //Query for SQL commands to communicate with DB
        PreparedStatement stmt = null; //Statement for executing the SQL query
        ResultSet keys = null; //Auto Key

        int rowsAffected = 0;
        int mID;

        //Required fields to use the Insert into the Table
        query = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_FNAME + ", "
                + COLUMN_LNAME + ", "
                + COLUMN_EMAIL + ", "
                + COLUMN_CONTACT + ", "
                + COLUMN_SERVE + ", "
                + COLUMN_DATE + ", "
                + COLUMN_OT
                + ") VALUES (?,?,?,?,?,?,?)";

        try {
            stmt = cConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, mechanic.getFirstName());
            stmt.setString(2, mechanic.getLastName());
            stmt.setString(3, mechanic.getEmailAdd());
            stmt.setInt(4, mechanic.getContactNo());
            stmt.setDate(6, new Date(mechanic.getStartDate().getTime()));
            stmt.setInt(5, mechanic.getServicesComplete());
            stmt.setDouble(7, mechanic.getOverTime());

            rowsAffected = stmt.executeUpdate();//Update the table

            if (rowsAffected == 1) {
                // if one row was inserted, retrieve the id assigned to that row and create a Bus to return
                keys = stmt.getGeneratedKeys();//Auto Key
                keys.next();

                mID = keys.getInt(1);

                mechanic.setStaffID(mID);
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
        return success; //return the mechanic if created or null if issue

    }

    public ArrayList<Mechanic> viewMechanic() throws SQLException {
        
        String query; //for sql query
        Statement stmt; //For executing SQL
        
        ResultSet resultSet; //Represents the results of the sql query
        ArrayList<Mechanic> mechanic; //Arraylist with the mechanic objects
        
        //Fields to create a new mechanic object
        int staffID;
        String firstName;
        String lastName;
        String emailAdd;
        int contactNo;
        int servicesCompleted;
        Date startDate = null;
        double overTime;
        
        //New mechanic object
        Mechanic mechanic1;
        //Executing of SQL statement 
        query = "SELECT * FROM  " + TABLE_NAME; // Select all from the table ie: mechanic
        stmt = this.cConnection.createStatement();
        resultSet = stmt.executeQuery(query);
        
        //New empty arraylist for object
        mechanic = new ArrayList<Mechanic>();
        while(resultSet.next()){
            staffID = resultSet.getInt(COLUMN_ID);
            firstName = resultSet.getString(COLUMN_FNAME);
            lastName = resultSet.getString(COLUMN_LNAME);
            emailAdd = resultSet.getString(COLUMN_EMAIL);
            contactNo = resultSet.getInt(COLUMN_CONTACT);
            servicesCompleted = resultSet.getInt(COLUMN_SERVE);
            startDate = resultSet.getDate(COLUMN_DATE);
            overTime = resultSet.getDouble(COLUMN_OT);
                    
            mechanic.add(new Mechanic(staffID, firstName, lastName, emailAdd, contactNo, servicesCompleted, startDate, overTime));
            
        }
        return mechanic;
        
    }
}
