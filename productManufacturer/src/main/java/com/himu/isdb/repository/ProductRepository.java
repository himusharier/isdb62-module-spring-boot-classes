package com.himu.isdb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.himu.isdb.model.Product;

@Repository
public class ProductRepository {
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert productInsert;

	public ProductRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

		this.productInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("products_sb_jdbc")
				.usingGeneratedKeyColumns("id");
	}

	public int save(Product product) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", product.getName());
		parameters.put("price", product.getPrice());
		parameters.put("quantity", product.getQuantity());
		parameters.put("model", product.getModel());

		Number key = productInsert.executeAndReturnKey(parameters);
		return key.intValue();
	}

	public Optional<Product> findById(int id) {
		try {
			String sql = "SELECT * FROM products_sb_jdbc WHERE id = ?";
			return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id));

		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	private static class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Product(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("price"),
					resultSet.getInt("quantity"), resultSet.getString("model"));
		}
	}

	public List<Product> findAll() {
		String sql = "SELECT * FROM products_sb_jdbc";
		return jdbcTemplate.query(sql, new ProductRowMapper());
	}

	public int deleteById(int id) {
		String sql = "DELETE FROM products_sb_jdbc WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	public int update(Product product) {
		String sql = "UPDATE products_sb_jdbc SET name = ?, price = ?, quantity = ?, model = ? WHERE id = ?";

		return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getQuantity(),
				product.getModel(), product.getId());
	}

	public List<Product> getProductByName(String name) {
		String sql = "SELECT * FROM products_sb_jdbc WHERE name LIKE ?";
		return jdbcTemplate.query(sql, new ProductRowMapper(), "%" + name + "%");
	}
}
