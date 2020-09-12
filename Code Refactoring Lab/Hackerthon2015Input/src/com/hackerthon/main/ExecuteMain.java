package com.hackerthon.main;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.hackerthon.common.UtilTransform;
import com.hackerthon.service.getEmpService;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		getEmpService employeeService = new getEmpService();
		try {
			UtilTransform.RequestTransform();
			employeeService.employeesFormXml();
			employeeService.createEmployeeTable();
			employeeService.addEmployees();
/*			employeeService.getEmployeeByID("EMP10004");
			employeeService.getEmployeeByID("EMP10001");		*/
			employeeService.displayEmployee();
		} catch (Exception e) {
		}

	}

}
