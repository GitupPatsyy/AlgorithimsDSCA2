/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author rorypb
 */
public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);//Scanner for input

        Model model = Model.getInstance();//Get particular instance of the model

        int menu = 0;//Integer for selecting a menu option

        do {
            System.out.println("1 - Create entry from an input file");
            System.out.println("2 - Generate Staff P45");
            System.out.println("3 - View all Drivers");
            System.out.println("4 - Display ALL Staff members");
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
                    System.out.println("Generating staff P45...");
                    break;
                }

                case 3: {
                    System.out.println("View all drivers...");
                    viewDrivers(model); //Will view all drivers
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
            try {
                Scanner in = new Scanner(inputFile);
                while(in.hasNextLine())
                {
                    String line = in.nextLine();
                    if(line.equalsIgnoreCase("D"))
                    {
                        createDriver(in, m);
                        
                    }
                    
                    
                    else {
                       System.out.println("Dum dum");
                   }
                }
                in.close();
                
            }
            catch (FileNotFoundException ex){
                System.out.println("None existent file baffoon\n");
            }
        }

    private static void createDriver(Scanner in, Model m) {
        
        String fn = in.nextLine(); //String for First name
        String ln = in.nextLine(); //String for Last name
        String em = in.nextLine(); //String for Email
        System.out.println("Email " + em);
        int cn = in.nextInt();
        String blank = in.nextLine();//Read the /n left by the nextInt()
        String l = in.nextLine(); //String for License Number       
        DateFormat df = new SimpleDateFormat ("yyyy-MM-dd"); //Date formatting
        Date dateStart = null; 
        String startDate = in.next();
        System.out.println(); //Reads the \n after the date field
        double hd = in.nextDouble(); //For hours drove
        blank = in.nextLine(); //Eat up end of line
        
        
//        System.out.print(fn +"--"+ ln +"--"+ em +"--"+ cn +"--"+ l +"--"+ dateStart +"--"+ hd);
        
        
        try {
            
            dateStart = df.parse(startDate);
        }
        catch(ParseException e) {
            Logger.getLogger(em, startDate);
        }
        
        Driver d = new Driver(fn, ln, em, cn, l, dateStart, hd);
        m.addDriver(d);
        }
 
 //Method to view the arraylist
   private static void viewDrivers(Model ml) {
       List<Driver> drivers = ml.getDrivers();
       System.out.println(); //Print a blank line for space
        if(drivers.isEmpty()) { //If the drivers is empty print out the error below
            System.out.println("There are no drivers within our database");
        } else {
          System.out.printf("%10s %20s %20s %30s %12s %20s %10s %5s\n",//Formatting lenghts
                    "StaffId", "First Name", "Last Name", "Email", "Contact No", "License No", "Start Date", "Hours Drove"); 
            for (Driver dr : drivers) {
                System.out.printf("%9s %20s %20s %20s %12s %20s%.2s %7s\n",
                        dr.getStaffID(),
                        dr.getFirstName(),
                        dr.getLastName(),
                        dr.getEmailAdd(),
                        dr.getContactNo(),
                        dr.getLicense(),
                        dr.getStartDate(),
                        dr.getStartDate());
            }
        }
        System.out.println();//Print a blank line for spacing
    }  
        
        
   
    
    
    
}//Main method closing brace
    
        



    


