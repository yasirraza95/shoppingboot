package com.shoppingboot.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Cart;

public class CartErepositoryImpl implements CartErepository {

	private Calendar calendar;
	private Date currentTime;
	public long time;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.shoppingboot.repository");
		return emf.createEntityManager();
	}
	
	
	@Override
	public ResultType addCart(Long userId, int prodId) {
		ResultType status = ResultType.NOTHING;

		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		calendar = Calendar.getInstance();
		currentTime = calendar.getTime();
		time = currentTime.getTime();
		
		int dbQty = 0;
		int quantity = 0;
		int existCounter = 0;
		
		String chkStmt = "select quantity from cart where user_id = ? and prod_id = ?";
		
		try {
			dbQty = (int) em.createNativeQuery(chkStmt).setParameter(1, userId).setParameter(2, prodId).getSingleResult();	
		} catch(Exception e) {
			e.printStackTrace();
			dbQty = 0;
		}
			
		String existStmt = "select count(*) from cart where user_id = ? and prod_id = ?";
		existCounter = (int) em.createNativeQuery(existStmt).setParameter(1, userId).setParameter(2, prodId).getSingleResult();
		
		boolean exist = false;
		
			if (existCounter > 0) {
				exist = true;
			}
	
			if (exist) {
				
				String updtStmt = "update cart set quantity = ? where user_id = ? and prod_id = ?";
				quantity = dbQty + 1;
				
				int affectedRows = em.createNativeQuery(updtStmt).setParameter(1, quantity)
						.setParameter(2, userId).setParameter(3, prodId).executeUpdate();
				
				if(affectedRows > 0) {
					status = ResultType.UPDATED;	
				}
	
			} else {
				String insStmt =  "insert into cart (user_id, prod_id, quantity, time) values (?, ?, ?, ?)";
				
				int affectedRows = em.createNativeQuery(insStmt).setParameter(1, userId).setParameter(2, prodId)
						.setParameter(3, 1).setParameter(4, new Timestamp(time)).executeUpdate();
				
				if(affectedRows > 0) {
					status = ResultType.CREATED;	
				}
				
			}

		return status;
	}

	@Override
	public ResultType delCart(Long userId, int prodId) {
		ResultType status = ResultType.NOTHING;
		
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		String	chkStmt = "select count(*) from cart where user_id = ? and prod_id = ?";
		int count = (int) em.createNativeQuery(chkStmt).getSingleResult();
		
		if (count > 0) {
			String delStmt = "delete from cart where user_id = ? and prod_id = ?";
			int deletedRows = em.createNativeQuery(delStmt).setParameter(1, userId).setParameter(2, prodId).executeUpdate();
			
			if(deletedRows > 0) {
				status = ResultType.DELETED;
			}
		}
		return status;
	}

	@Override
	public ResultType updateCart(Long userId, int prodId, String type) {
		ResultType status = ResultType.NOTHING;

		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		String chkStmt = "select quantity from cart where user_id = ? and prod_id = ?";
		int dbQty = (int) em.createNativeQuery(chkStmt).setParameter(1, userId).setParameter(2, prodId).getSingleResult();

			if (dbQty > 0) {
				if(type.equals("up")) {
					dbQty = dbQty + 1;
				} else {
					dbQty = dbQty - 1;
				}
				
				String updateStmt = "update cart set quantity = ? where user_id = ? and prod_id = ?";
				int updatedRows = em.createNativeQuery(updateStmt).setParameter(1, dbQty)
						.setParameter(2, userId).setParameter(3, prodId).executeUpdate();
				
				if(updatedRows > 0) {
					status = ResultType.UPDATED;
				}
			}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cart> getCart(Long userId) {
		List<Cart> arrayList = new ArrayList<>();
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		String statement = "select products.image as image, products.name as name, products.price as price,"
					+ " cart.quantity as quantity, cart.id as id, cart.prod_id as prod_id from cart inner join users"
					+ " on cart.user_id = users.id inner join products on cart.prod_id = products.id where users.id = ? ";
			arrayList = em.createNativeQuery(statement).setParameter(1, userId).getResultList();
		return arrayList;
	}

}
