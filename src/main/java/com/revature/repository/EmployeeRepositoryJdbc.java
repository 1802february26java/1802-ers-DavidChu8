package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.EmployeeRole;
import com.revature.model.EmployeeToken;
import com.revature.util.ConnectionUtil;

public class EmployeeRepositoryJdbc implements EmployeeRepository {

	private static Logger logger = Logger.getLogger(EmployeeRepositoryJdbc.class);

	/*Singleton transformation of JDBC implementation object */
	private static EmployeeRepositoryJdbc employeeDaoJdbc;
	
	
	private EmployeeRepositoryJdbc() {
		
	}
	
	public static EmployeeRepositoryJdbc getInstance() {
		if (employeeDaoJdbc == null) {
			employeeDaoJdbc = new EmployeeRepositoryJdbc();
		}
		return employeeDaoJdbc;
	}
	
	@Override
	public boolean insert(Employee employee) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(command);
			
			// Set attributes to be inserted
			statement.setInt(++statementIndex, employee.getId());
			statement.setString(++statementIndex, employee.getFirstName());
			statement.setString(++statementIndex, employee.getLastName());
			statement.setString(++statementIndex, employee.getUsername());
			statement.setString(++statementIndex, employee.getPassword());
			statement.setString(++statementIndex, employee.getEmail());
			
			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch(SQLException e) {
			logger.warn("Exception creating a new employee", e);
		}
		return false;
	}

	@Override
	public boolean update(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee select(int employeeId) {
		try(Connection connetcion = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "SELECT * FROM EMPLOYEE WHERE ID = ?";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setInt(++statementIndex, employeeId);
			ResultSet result = statement.executeQuery();
			
			int statementIndex2 = 0;
			String command2 = "SELECT * FROM EMPLOYEEROLE WHERE ID = ?";
			PreparedStatement statement2 = connection.prepareStatement(command2);
			statement2.
			
			while (result.next()) {
				return new Employee(
						result.getInt("id"),
						result.getString("firstName"),
						result.getString("lastName"),
						result.getString("username"),
						result.getString("password"),
						result.getString("email"),
						new EmployeeRole(result.getInt("roleId"),"")
						);
			}
		} catch(SQLException e) {
			logger.warn("Exception selecting a employee by id");
		}
		return new Employee();
	}

	@Override
	public Employee select(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Employee> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPasswordHash(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertEmployeeToken(EmployeeToken employeeToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployeeToken(EmployeeToken employeeToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EmployeeToken selectEmployeeToken(EmployeeToken employeeToken) {
		// TODO Auto-generated method stub
		return null;
	}

}
