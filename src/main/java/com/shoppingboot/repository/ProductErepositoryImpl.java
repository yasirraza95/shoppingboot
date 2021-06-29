package com.shoppingboot.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Product;

public class ProductErepositoryImpl implements ProductErepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.shoppingboot.repository");
		return emf.createEntityManager();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts(String prodType) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		String query = null;
		
		query = "select * from products where type = ?";

		return em.createNativeQuery(query).setParameter(1, prodType).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getTopProducts() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		String query = null;
		query = "select * from products where top_product = ? order by rand() limit 4";
		return em.createNativeQuery(query).setParameter(1, "yes").getResultList();
	}

	@Override
	public Product getSingleProduct(int id) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		String statement = "select * from products where id = ?";		
		return (Product)em.createNativeQuery(statement).setParameter(1, id).getResultList();
	}

	@Override
	public ResultType insert(Product product) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		ResultType status = ResultType.NOTHING;
		String getUser = "select count(*) from products where name = ?";
		int count = em.createNativeQuery(getUser).setParameter(1, product.getName()).getFirstResult();
		
		if(count > 0) {
			status = ResultType.NOTHING;
		} else if(count == 0){
			String insertUser = "insert into products (name, image, price, price_type, description, type, top_product) VALUES("
							+ "?, ?, ?, ?, ?, ?, ?)";
			int insertedRows = em.createNativeQuery(insertUser).setParameter(1, product.getName()).setParameter(2, product.getImage())
					.setParameter(3, product.getPrice()).setParameter(4, product.getPriceType()).setParameter(5, product.getDescription())
					.setParameter(6, product.getType()).setParameter(7, product.getTopProduct()).executeUpdate();
					
			if(insertedRows > 0) {
				status = ResultType.CREATED;
			}
		}
		return status;
	}

	@Override
	public ResultType delProduct(int id) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		ResultType status = ResultType.NOTHING;

		String chkStmt = "select count(*) from products where id = ? ";
		int count = (int) em.createNativeQuery(chkStmt).setParameter(1, id).getSingleResult();
					
		if(count > 0) {
			String delStmt = "delete from products where id = ?";
			int deletedRows = em.createNativeQuery(delStmt).executeUpdate();
			
			if(deletedRows > 0) {
				status = ResultType.DELETED;
			}
		} 

		return status;
	}

	@Override
	public ResultType updateProd(Product product) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		ResultType status = ResultType.NOTHING;

		String getUser = "select count(*) from products where id = ?";
		int count = (int) em.createNativeQuery(getUser).setParameter(1, product.getId()).getSingleResult();

		if(count == 0) {
			status = ResultType.NOTHING;
		} else if(count > 0){
			String updateUser = "update products set name = ?, image = ?, price = ?, price_type = ?,"
							+ "description = ?, type = ?, top_product = ? where id = ? ";
			int updatedRows = em.createNativeQuery(updateUser).setParameter(1, product.getName()).setParameter(2, product.getImage())
					.setParameter(3, product.getPrice()).setParameter(4, product.getPriceType()).setParameter(5, "description")
					.setParameter(6, product.getType()).setParameter(7, product.getTopProduct()).setParameter(8, product.getId())
					.executeUpdate();
			if(updatedRows > 0) {
				status = ResultType.UPDATED;
			}
		}

	return status;

	}

	@Override
	public ResultType validateProduct(String name) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		ResultType status = ResultType.NOTHING;

		String chkStmt = "select count(*) from products where name = ? ";	
		int count = (int) em.createNativeQuery(chkStmt).setParameter(1, name).getSingleResult();
		
		if(count > 0) {
			status = ResultType.ALREADY;
		} else {
			status = ResultType.NOTHING;
		} 

		return status;
	}

}
