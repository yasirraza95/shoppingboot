package com.shoppingboot.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.User;

public class LoginErepositoryImpl implements LoginErepository {


	@PersistenceContext
	private EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.shoppingboot.repository");
		return emf.createEntityManager();
	}
	
	@Override
	public ResultType validate(User user) {
		ResultType status = ResultType.NOTHING;
		
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		String statement = "select id from users where username = ? and password = ? and type = ?";
		int userId = (int) em.createNativeQuery(statement).setParameter(1, user.getUsername()).setParameter(2, user.getPassword())
				.setParameter(3, user.getType()).getSingleResult();

		if(userId > 0) {
			
			String cartStatement = "select count(*) from cart where user_id = ?";
			int cartCounter = (int) em.createNativeQuery(cartStatement).setParameter(1, userId).getSingleResult();
			
			user.setCartQty(cartCounter);
			
			String statement2 = "select * from users where username = ? and password = ? and type = ?";
			user = (User)em.createNativeQuery(statement2).setParameter(1, user.getUsername()).setParameter(2, user.getPassword())
			.setParameter(3, user.getType()).getSingleResult();
			
			status = ResultType.FOUND;			
		}

	return status;
	}

}
