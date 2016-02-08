/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.sql.Date;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author rorypb
 */
public class Mechanic extends Staff {

    //New fields for Sub Class
    private int servicesComplete;
    private Date startDate;
    private double overTime;

    public Mechanic(int staffID, String firstName, String lastName, String emailAdd, int contactNo, int servicesComplete, Date startDate, double overTime) {
        super(staffID, firstName, lastName, emailAdd, contactNo);
        this.servicesComplete = servicesComplete;
        this.startDate = startDate;
        this.overTime = overTime;

    }

    public Mechanic(String firstName, String lastName, String emailAdd, int contactNo, int servicesComplete, Date startDate, double overTime) {
        this(-1, firstName, lastName, emailAdd, contactNo, servicesComplete, startDate, overTime);

    }

    /**
     * @return the servicesComplete
     */
    public int getServicesComplete() {
        return servicesComplete;
    }

    /**
     * @param servicesComplete the servicesComplete to set
     */
    public void setServicesComplete(int servicesComplete) {
        this.servicesComplete = servicesComplete;
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
     * @return the overTime
     */
    public double getOverTime() {
        return overTime;
    }

    /**
     * @param overTime the overTime to set
     */
    public void setOverTime(double overTime) {
        this.overTime = overTime;
    }

    //A display method to override the display from the superlass (Staff)
    //Display method is in both super classes sub classes, so polymorphism can be utilised here
    public void viewStaff() {

        System.out.printf("%5s %20s %30s %30s %30s %20s %10d %10s %10d \n",
                "Id", "First Name", "Last Name", "Email", "Contact No", "No of Services", "Start Date", "Over Time");
        //Super class view method called
        super.viewStaff();
        System.out.printf("%10s 10d\n", this.startDate, this.overTime);

    }

    @Override
    public void printPayslip() {
        this.viewStaff();

        Calendar cCalendar = Calendar.getInstance();
        String month = cCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        
        System.out.println("Monthly pay for " + month + "€3200");

    }

}
