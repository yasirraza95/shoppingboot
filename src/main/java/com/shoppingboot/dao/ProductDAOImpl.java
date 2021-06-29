package com.shoppingboot.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Product;

public class ProductDAOImpl implements ProductDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@SuppressWarnings("deprecation")
	public List<Product> getProducts(String prodType) {
		List<Product> arrayList = new ArrayList<>();
				
		String query = null;
		
			query = "select * from products where type = ?";
//			System.out.println(query);
			arrayList = jdbcTemplate.query(query, new Object[] {prodType}, rs -> {
				List<Product> prodList = new ArrayList<>();

				while(rs.next()) {
					Product product = new Product();
					product.setId(Integer.parseInt(rs.getString("id")));
					product.setName(rs.getString("name"));
					product.setImageName(rs.getString("image"));
					product.setDescription(rs.getString("description"));
					product.setPrice(Integer.parseInt(rs.getString("price")));
					product.setType(rs.getString("type"));

					prodList.add(product);
					
				}

				return prodList;
			});
			
		
		return arrayList;
	}
	
	
	@SuppressWarnings("deprecation")
	public List<Product> getTopProducts() {
		List<Product> arrayList = new ArrayList<>();
				
		String query = null;
		
		//get all products
			query = "select * from products where top_product = ? order by rand() limit 4";
			
			arrayList = jdbcTemplate.query(query, new Object[] {"yes"}, rs -> {
				List<Product> prodList = new ArrayList<>();

				while(rs.next()) {
					Product product = new Product();
					product.setId(Integer.parseInt(rs.getString("id")));
					product.setName(rs.getString("name"));
					product.setImageName(rs.getString("image"));
					product.setDescription(rs.getString("description"));
					product.setPrice(Integer.parseInt(rs.getString("price")));
					product.setType(rs.getString("type"));

					prodList.add(product);
					
				}

				return prodList;
			});
		
		
		return arrayList;
	}
	
	
	public Product getSingleProduct(int id) {

		Product product = new Product();
		
		String statement = "select * from products where id = ?";
		
		jdbcTemplate.query(statement, new Object[] {id}, rs -> {
			product.setName(rs.getString("name"));
			product.setPrice(rs.getInt("price"));
			product.setPriceType(rs.getString("price_type"));
			product.setType(rs.getString("type"));
		});
		
		return product;
	}

	public ResultType insert(Product product) {

		ResultType status = ResultType.NOTHING;


			String getUser = "select count(*) from products where name = ?";
			int count = jdbcTemplate.queryForObject(getUser, new Object[] {product.getName()}, Integer.class);
			
			if(count > 0) {
				status = ResultType.NOTHING;
			} else if(count == 0){
				String insertUser = "insert into products (name, image, price, price_type, description, type, top_product) VALUES("
								+ "?, ?, ?, ?, ?, ?, ?)";
				int insertedRows = jdbcTemplate.update(insertUser, new Object[] {product.getName(), product.getImage(),
						product.getPrice(), product.getPriceType(), product.getDescription(), product.getType(), product.getTopProduct()});
				
				if(insertedRows > 0) {
					status = ResultType.CREATED;
				}
				
			}

			return status;
	}

	public ResultType delProduct(int id) {

		ResultType status = ResultType.NOTHING;

		String chkStmt = "select count(*) from products where id = ? ";
		int count = jdbcTemplate.queryForObject(chkStmt, new Object[] {id}, Integer.class);
					
		if(count > 0) {
			String delStmt = "delete from products where id = ?";
			int deletedRows = jdbcTemplate.update(delStmt);
			
			if(deletedRows > 0) {
				status = ResultType.DELETED;
			}
		} 

		return status;
	}

	public ResultType updateProd(Product product) {
		
		ResultType status = ResultType.NOTHING;


			String getUser = "select count(*) from products where id = ?";
			int count = jdbcTemplate.queryForObject(getUser, new Object[] {product.getId()}, Integer.class);

			if(count == 0) {
				status = ResultType.NOTHING;
			} else if(count > 0){
				String updateUser = "update products set name = ?, image = ?, price = ?, price_type = ?,"
								+ "description = ?, type = ?, top_product = ? where id = ? ";
				int updatedRows = jdbcTemplate.update(updateUser, new Object[] {product.getName(), product.getImage(),
						product.getPrice(), product.getPriceType(), "description", product.getType(), product.getTopProduct(),
						product.getId()});
				
				if(updatedRows > 0) {
					status = ResultType.UPDATED;
				}
			}

		return status;
	}

	public ResultType validateProduct(String name) {

		ResultType status = ResultType.NOTHING;

		String chkStmt = "select count(*) from products where name = ? ";	
		int count = jdbcTemplate.queryForObject(chkStmt, new Object[] {name}, Integer.class);
		
		if(count > 0) {
			status = ResultType.ALREADY;
		} else {
			status = ResultType.NOTHING;
		} 

		return status;
	}

}
