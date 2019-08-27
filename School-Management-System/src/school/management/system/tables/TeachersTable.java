/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.tables;

/**
 *
 * @author JOYOUS
 */
public class TeachersTable {
    String employeeID,fullName,dob,gender,qualification,address,designation;

    public TeachersTable(String employeeID, String fullName, String dob, String gender, String qualification, String address, String designation) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.qualification = qualification;
        this.address = address;
        this.designation = designation;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getQualification() {
        return qualification;
    }

    public String getAddress() {
        return address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    
}
