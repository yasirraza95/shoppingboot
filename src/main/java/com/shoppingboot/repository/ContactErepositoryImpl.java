package com.shoppingboot.repository;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import com.shoppingboot.enums.ResultType;

public class ContactErepositoryImpl implements ContactErepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.shoppingboot.repository");
		return emf.createEntityManager();
	}
	
	@Override
	public ResultType insert(String name, String email, String reason, String message, Long time) {
		ResultType status = ResultType.NOTHING;
		
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		
		String insertMsg = "insert into contact (name, email, reason, message, date) VALUES(?, ?, ?, ?, ?)";
		int insertedRows = em.createNativeQuery(insertMsg).setParameter(1, name).setParameter(2, email)
				.setParameter(3, reason).setParameter(4, message).setParameter(5, new Timestamp(time)).executeUpdate();
				
		
		if(insertedRows > 0) {
			status = ResultType.CREATED;
		}
		
		return status;
	}

}
