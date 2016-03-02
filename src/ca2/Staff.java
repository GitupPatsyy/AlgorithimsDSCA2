/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2;

import java.sql.Date;

/**
 *
 * @author rorypb
 */
public class Staff {//Super Class
    private int staffID;
    private String firstName;
    String lastName;
    String emailAdd;
    int contactNo;
    
    public Staff (int staffID, String firstName, String lastName, String emailAdd, int contactNo){
        this.staffID = staffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdd = emailAdd;
        this.contactNo = contactNo;
        
    }
    
    public Staff (String fname, String lname, String eadd, int connum) {
        this(-1, fname, lname, eadd, connum);
        
    }

    /**
     * @return the staffID
     */
    public int getStaffID() {
        return staffID;
    }

    /**
     * @param staffID the staffID to set
     */
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the emailAdd
     */
    public String getEmailAdd() {
        return emailAdd;
    }

    /**
     * @param emailAdd the emailAdd to set
     */
    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    /**
     * @return the contactNo
     */
    public int getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo the contactNo to set
     */
    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }
    
    public String getStaffInfo()
    {
        String str = String.format("%5d %20s %30s %20s %10d ",
        this.staffID,
        this.firstName,
        this.lastName,
        this.emailAdd,
        this.contactNo);
        
        return str;
    }
    
    //Method to viewstaff information
    public void viewStaff() {
        System.out.println(getStaffInfo());
    }
    
    public String printReview()
    {    
        return getStaffInfo();
    }
    
}
