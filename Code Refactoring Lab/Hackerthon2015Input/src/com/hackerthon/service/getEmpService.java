package com.hackerthon.service;

import org.xml.sax.SAXException;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
import javax.xml.xpath.XPathExpressionException;
import com.hackerthon.common.UtilTransform;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import com.hackerthon.common.UtilQ;
import java.io.IOException;
import com.hackerthon.model.Employee;

import java.util.ArrayList;
import java.util.Map;
import com.hackerthon.common.UtilC;

public class getEmpService extends UtilC {

	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();

	private static Connection connection;

	private static Statement statement;

	private PreparedStatement preparedStatement;

	public getEmpService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("password"));
		} catch (Exception e) {
		} 
	}

	public void employeesFormXml() {

		try {
			int s = UtilTransform.XMLXPATHS().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> l = UtilTransform.XMLXPATHS().get(i);
				Employee employee = new Employee();
				employee.setEmployeeID(l.get("XpathEmployeeIDKey"));
				employee.setFullName(l.get("XpathEmployeeNameKey"));
				employee.setAddress(l.get("XpathEmployeeAddressKey"));
				employee.setFacultyName(l.get("XpathFacultyNameKey"));
				employee.setDepartment(l.get("XpathDepartmentKey"));
				employee.setDesignation(l.get("XpathDesignationKey"));
				employeeList.add(employee);
				System.out.println(employee.toString() + "\n");
			}
		} catch (Exception e) {
		}
	}

	public void createEmployeeTable() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(UtilQ.Q("q2"));
			statement.executeUpdate(UtilQ.Q("q1"));
		} catch (Exception e) {
		}
	}

	public void addEmployees() {
		try {
			preparedStatement = connection.prepareStatement(UtilQ.Q("q3"));
			connection.setAutoCommit(false);
			
			//enhanced for loop
			for (Employee employee : employeeList) {
				preparedStatement.setString(1, employee.getEmployeeID());
				preparedStatement.setString(2, employee.getFullName());
				preparedStatement.setString(3, employee.getAddress());
				preparedStatement.setString(4, employee.getFacultyName());
				preparedStatement.setString(5, employee.getDepartment());
				preparedStatement.setString(6, employee.getDesignation());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (Exception e) {
		}
	}

	public void getEmployeeByID(String employeeID) {

		Employee employee = new Employee();
		try {
			preparedStatement = connection.prepareStatement(UtilQ.Q("q4"));
			preparedStatement.setString(1, employeeID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee.setEmployeeID(resultSet.getString(1));
				employee.setFullName(resultSet.getString(2));
				employee.setAddress(resultSet.getString(3));
				employee.setFacultyName(resultSet.getString(4));
				employee.setDepartment(resultSet.getString(5));
				employee.setDesignation(resultSet.getString(6));
			}
			ArrayList<Employee> arrayList = new ArrayList<Employee>();
			arrayList.add(employee);
			displayEmployeesDetails(arrayList);
		} catch (Exception ex) {
		}
	}

	public void deleteEmployee(String employeeID) {

		try {
			preparedStatement = connection.prepareStatement(UtilQ.Q("q6"));
			preparedStatement.setString(1, employeeID);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displayEmployee() {

		ArrayList<Employee> arrayList = new ArrayList<Employee>();
		try {
			preparedStatement = connection.prepareStatement(UtilQ.Q("q5"));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeID(resultSet.getString(1));
				employee.setFullName(resultSet.getString(2));
				employee.setAddress(resultSet.getString(3));
				employee.setFacultyName(resultSet.getString(4));
				employee.setDepartment(resultSet.getString(5));
				employee.setDesignation(resultSet.getString(6));
				arrayList.add(employee);
			}
		} catch (Exception e) {
		}
		displayEmployeesDetails(arrayList);
	}
	
	public void displayEmployeesDetails(ArrayList<Employee> arrayList){
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(int i = 0; i < arrayList.size(); i++){
			Employee employee = arrayList.get(i);
			System.out.println(employee.getEmployeeID() + "\t" + employee.getFullName() + "\t\t"
					+ employee.getAddress() + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t"
					+ employee.getDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
}
