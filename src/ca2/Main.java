/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import static java.lang.System.out;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rorypb
 */
public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);//Scanner for input

        Model model = Model.getInstance();//Get particular instance of the model
        //Database connection made here

        int menu = 0;//Integer for selecting a menu option

        do {
            System.out.println("1 - Create entry from an input file");
            System.out.println("2 - Generate Reviews");
            System.out.println("3 - View all Drivers");
            System.out.println("4 - View all Mechs");
            System.out.println("5 - Display ALL Staff members");
            System.out.println("0 - Exit");
            System.out.println();

            System.out.println("Please choose an option: ");//Select a menu option using a number
            String line = in.nextLine();//For accepting input of a string to choose a menu button
            menu = Integer.parseInt(line);//Parsing string int to a menu option

            switch (menu) { //switch statement for changing menu option

                case 1: {//Create a Staff member from an input file
                    System.out.println("Creating entry from input file...");
                    readStaffInput(in, model); //Will create the staff in DB
                    break;
                }

                case 2: {//No idea what this will do yet.. Output file? 
                    System.out.println("Generating staff payslisps...");
                    generateReview(model);
                    break;
                }

                case 3: {
                    System.out.println("View all drivers...");
                    viewDrivers(model); //Will view all drivers

                }
                
                case 4: {
                    System.out.println("Viewing all mechs");
                    viewMechanics(model);
                }

            }
        } while (menu != 0);
    }

    //Read input file
    public static void readStaffInput(Scanner file, Model m) {
        //File name required for input
        System.out.println("Enter the file name for input:");
        String inputFileName = file.nextLine();
        File inputFile = new File(inputFileName);
        try { //Try whatever is in the {}
            Scanner in = new Scanner(inputFile); //read the file input name
            while (in.hasNextLine()) //While the next line has...
            {
                String line = in.nextLine();
                if (line.equalsIgnoreCase("D")) // if the next line as a D run the create driver method
                {
                    createDriver(in, m);

                } else if (line.equalsIgnoreCase("M")) { // If it has an M or anything else there will be a mechanic added
                    createMechanic(in, m);
                }
            }
            in.close();

        } catch (FileNotFoundException ex) { //File not found exception
            System.out.println("None existent file baffoon\n"); // Print out a message to let the user know that there is no file with that name
        }
    }

    private static void createDriver(Scanner in, Model m) {

        String fn = in.nextLine(); //String for First name
        String ln = in.nextLine(); //String for Last name
        String em = in.nextLine(); //String for Email
        int cn = in.nextInt();
        String blank = in.nextLine();//Read the /n left by the nextInt()
        String l = in.nextLine(); //String for License Number       
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //Date formatting
        Date dateStart = null;
        String startDate = in.next();
        System.out.println(); //Reads the \n after the date field
        double hd = in.nextDouble(); //For hours drove

//        System.out.print(fn +"--"+ ln +"--"+ em +"--"+ cn +"--"+ l +"--"+ dateStart +"--"+ hd);
        try { // Try to parse the date 

            dateStart = df.parse(startDate); //Nww variable to hold the parse of the date
        } catch (ParseException e) { //Catch clause
        }

        Driver d = new Driver(fn, ln, em, cn, l, dateStart, hd); //New driver object
        m.addDriver(d); //Use the models add driver method to add the driver to the arraylist
    }

    //Method to view the arraylistof Drivers
    private static void viewDrivers(Model ml) {
        List<Driver> drivers = ml.getDrivers();
        System.out.println(); //Print a blank line for space
        if (drivers.isEmpty()) { //If the drivers is empty print out the error below
            System.out.println("There are no drivers within our database");
        } else {
            System.out.printf("%10s %20s %20s %20s %12s %15s %10s %10s\n",//Formatting lenghts
                    "| StaffId |", "| First Name |", "| Last Name |", "| Email |", "| Contact No |", "| License No |", "| Start Date |", "| Hours Drove |");
            for (Driver dr : drivers) {
                System.out.printf("%10s %20s %20s %20s %12s %15s %15s %15s\n",
                        dr.getStaffID(),
                        dr.getFirstName(),
                        dr.getLastName(),
                        dr.getEmailAdd(),
                        dr.getContactNo(),
                        dr.getLicense(),
                        dr.getStartDate(),
                        dr.getHoursDrove());
            }
        }
        System.out.println();//Print a blank line for spacing
    }

    private static void createMechanic(Scanner in, Model md) {

        String fn = in.nextLine(); //String for First name
        String ln = in.nextLine(); //String for Last name
        String em = in.nextLine(); //String for Email
        int cn = in.nextInt();
        int sc = in.nextInt();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //Date formatting
        Date dateStart = null;
        String startDate = in.next();
        System.out.println(); //Reads the \n after the date field
        double ot = in.nextDouble();

        try { // Try to parse the date 

            dateStart = df.parse(startDate); //Nww variable to hold the parse of the date
        } catch (ParseException e) { //Catch clause
        }

        Mechanic m = new Mechanic(fn, ln, em, cn, sc, dateStart, ot); //New mechanic object
        md.addMechanic(m); //Use the models add mechanic method to add the driver to the arraylist
    }

    //Arraylist view of the mechanics
    private static void viewMechanics(Model ml) {
        List<Mechanic> mechs = ml.getMechanics();
        System.out.println(); //Print a blank line for space
        if (mechs.isEmpty()) { //If the mechs is empty print out the error below
            System.out.println("There are no mechanics within our database");
        } else {
            System.out.printf("%10s %20s %20s %20s %12s %15s %10s %10s\n",//Formatting lenghts
                    "| StaffId |", "| First Name |", "| Last Name |", "| Email |", "| Contact No |", "| Services Completed |", "| Start Date |", "| Over Time |");
            for (Mechanic mc : mechs) {
                System.out.printf("%10s %20s %20s %20s %12s %15s %15s %15s\n",
                        mc.getStaffID(),
                        mc.getFirstName(),
                        mc.getLastName(),
                        mc.getEmailAdd(),
                        mc.getContactNo(),
                        mc.getServicesComplete(),
                        mc.getStartDate(),
                        mc.getOverTime());
            }
        }
        System.out.println();//Print a blank line for spacing
    }

    private static void generateReview(Model m) {
        //OBject for a STAFF memeber. 
        //Staff member can be either driver or mechancic
        Staff s = null;
        //Read ids from the text file from the model
        PrintWriter output = null;

        try {
            output = new PrintWriter(new File("out.txt"));

            output.println();
        } catch (FileNotFoundException ex) {
            System.out.println("No such file be in existence inside of this dimension - check another portal");
        } finally {
            if (out != null);
            out.close();
        }

//                if (s != null) //utilises polymorphism, can call drivers or the mechanics method to print reviews
//                {
//                    s.printReview();
//                } else {
//                    System.out.println("Staff number: " + staffId + " non existent");
//                }
    }

}//Main method closing brace

