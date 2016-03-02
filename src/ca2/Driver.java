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

    public String formatStaffView() {
        String str = String.format("%10s %20s %20s %20s %12s %15s %10s %10s\n",//Formatting lenghts
                "| StaffId |", "| First Name |", "| Last Name |", "| Email |", "| Contact No |", "| License No |", "| Start Date |", "| Hours Drove |");
        String staffInfo = super.getStaffInfo();
        String dates = String.format("%15s 15d\n", this.startDate, this.hoursDrove);

        return str + staffInfo + dates;
    }

    public void viewStaff() {
        System.out.println(formatStaffView());
    }

    @Override
    public String printReview() {
        String staffView = this.formatStaffView();

        Calendar mCalendar = Calendar.getInstance();
        String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

        String dateHeading = "Monthly Hours Drove for: " + month + " " + getHoursDrove();

        String review;
        if (getHoursDrove() > 20) {
            review = "Your work level has been above par and we would like to congratulate and hope you keep up the hard work";
        } else {
            review = "Your work level is slacking you are going to have to the finger out or you may face a serious sacking.";
        }
        
        return staffView + month + dateHeading + review;

    }

}
