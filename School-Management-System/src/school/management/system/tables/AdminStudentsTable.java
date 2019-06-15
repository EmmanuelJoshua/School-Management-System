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
public class AdminStudentsTable {
    String registrationNumber,fullName,dob,gender,studentClass,residentialAddress,paymentStatus;

    public AdminStudentsTable(String registrationNumber, String fullName, String dob, String gender, String studentClass, String residentialAddress, String paymentStatus) {
        this.registrationNumber = registrationNumber;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.studentClass = studentClass;
        this.residentialAddress = residentialAddress;
        this.paymentStatus = paymentStatus;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
