package com.hackerthon.model;

public class Employee {

	public String employeeID;
	public String fullName;
	public String address;
	public String facultyName;
	public String department;
	public String designation;
	
	
	
	
	public String getEmployeeID() {
		return employeeID;
	}




	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}




	public String getFullName() {
		return fullName;
	}




	public void setFullName(String fullName) {
		this.fullName = fullName;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getFacultyName() {
		return facultyName;
	}




	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}




	public String getDepartment() {
		return department;
	}




	public void setDepartment(String department) {
		this.department = department;
	}




	public String getDesignation() {
		return designation;
	}




	public void setDesignation(String designation) {
		this.designation = designation;
	}





	@Override
public String toString() {
	
	return "Employee ID = " + employeeID + "\n" + "FullName = " + fullName + "\n" + "Address = " + address + "\n"
			+ "Faculty Name = " + facultyName + "\n" + "Department = " + department + "\n" + "Designation = "
			+ designation;
}


	



}
