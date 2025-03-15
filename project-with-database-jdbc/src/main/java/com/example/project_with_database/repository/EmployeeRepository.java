package com.example.project_with_database.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.project_with_database.model.Employee;

@Repository
public class EmployeeRepository {
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert employeeInsert;

	public EmployeeRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

		this.employeeInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("employees_sb_jdbc")
				.usingGeneratedKeyColumns("id");
	}

	public int save(Employee employee) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", employee.getName());
		parameters.put("email", employee.getEmail());
		parameters.put("designation", employee.getDesignation());
		parameters.put("age", employee.getAge());
		parameters.put("address", employee.getAddress());
		parameters.put("dob", employee.getDob());
		parameters.put("salary", employee.getSalary());

		Number key = employeeInsert.executeAndReturnKey(parameters);
		return key.intValue();
	}

	public Optional<Employee> findById(int id) {
		try {
			String sql = "SELECT * FROM employees_sb_jdbc WHERE id = ?";
			return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), id));

		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	private static class EmployeeRowMapper implements RowMapper<Employee> {
		public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Employee(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"),
					resultSet.getString("designation"), resultSet.getInt("age"), resultSet.getString("address"),
					resultSet.getObject("dob", LocalDate.class), resultSet.getDouble("salary"));
		}
	}

}
