/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author rorypb
 */
public class Driver extends Staff {

    //Driver subclass adds 3 new fields
    private String license;
    private Date startDate;
    private double hoursDrove;

    public Driver(int staffID, String firstName, String lastName, String emailAdd, int contactNo, String license, Date startDate, double hoursDrove) {
        super(staffID, firstName, lastName, emailAdd, contactNo);
        this.license = license;
        this.startDate = startDate;
        this.hoursDrove = hoursDrove;

    }

    public Driver(String firstName, String lastName, String emailAdd, int contactNo, String license, Date startDate, double hoursDrove) {
        this(-1, firstName, lastName, emailAdd, contactNo, license, startDate, hoursDrove);

    }

    /**
     * @return the license
     */
    public String getLicense() {
        return license;
    }

    /**
     * @param license the license to set
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the hoursDrove
     */
    public double getHoursDrove() {
        return hoursDrove;
    }

    /**
     * @param hoursDrove the hoursDrove to set
     */
    public void setHoursDrove(double hoursDrove) {
        this.hoursDrove = hoursDrove;
    }
    
    
     
    public void viewStaff() {
        System.out.printf("%10s %20s %20s %20s %12s %15s %10s %10s\n",//Formatting lenghts
                    "| StaffId |", "| First Name |", "| Last Name |", "| Email |", "| Contact No |", "| License No |", "| Start Date |", "| Hours Drove |");
        //Super class view method called
        super.viewStaff();
        System.out.printf("%15s 15d\n", this.startDate, this.hoursDrove);
    }
    
      @Override
    public void printReview()
    {
       this.viewStaff();
       
      Calendar mCalendar = Calendar.getInstance();    
      String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
       
        System.out.println("Monthly Hours Drove for: " + month + " "  + getHoursDrove());
               
    }


}
