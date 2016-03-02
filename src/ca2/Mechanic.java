/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.util.Date;
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
    
    //Formatting method for stafffview
    public String formatStaffView() {
        String str = String.format("%10s %20s %20s %20s %12s %15s %10s %10s\n",//Formatting lenghts
                    "| StaffId |", "| First Name |", "| Last Name |", "| Email |", "| Contact No |", "| No of Services |", "| Start Date |", "| Over Time |");
        
        String staffInfo = super.getStaffInfo();
        String dates = String.format("%15s 15s\n", this.startDate, this.overTime);
        
        return str + staffInfo + dates;
        
    }
    

    //A display method to override the display from the superlass (Staff)
    //Display method is in both super classes sub classes, so polymorphism can be utilised here
    public void viewStaff() {
        System.out.println(this.formatStaffView());
    }

    @Override
    public String printReview() {
       String staffView = this.formatStaffView();

        Calendar cCalendar = Calendar.getInstance();
        String month = cCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        
        String dateHeading = "Monthly OT hours for " + month + " are " + getOverTime();
        String review;
        if (getOverTime() > 20){
            review = "Your work level has been above par and we would like to congratulate and hope you keep up the hard work";
        }
        else {
             review = "Your work level is slacking you are going to have to the finger out or you may face a serious sacking.";
        }
        
        return staffView + dateHeading + review;

    }

}
