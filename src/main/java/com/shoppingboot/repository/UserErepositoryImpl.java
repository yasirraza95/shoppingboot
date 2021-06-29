package com.shoppingboot.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.TgtUser;
import com.shoppingboot.model.User;

@Repository
@Transactional
public class UserErepositoryImpl implements UserErepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.shoppingboot.repository");
		return emf.createEntityManager();
	}
	
	@Override
	public ResultType insert(User user) {
		ResultType status = ResultType.NOTHING;
		
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		if(user.getId() > 0) {
			status = ResultType.CREATED;
		} else {
			status = ResultType.ERROR;
		}
		return status;
	}

	@Override
	public ResultType updateProfile(User user) {
		ResultType status = ResultType.NOTHING;
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		
		String updateUser = "update users set name = ? , email = ? , mobile = ? , address = ? where id = ? ";
		int updatedRows = em.createNativeQuery(updateUser).setParameter(1, user.getName()).setParameter(2, user.getEmail())
				.setParameter(3, user.getMobile()).setParameter(4, user.getAddress()).setParameter(6, user.getUserId()).executeUpdate();
//			System.out.println("update info:"+updateUser);
		
		if(updatedRows > 0) {
			status = ResultType.UPDATED;
		}
		
		return status;
	}

	@Override
	public ResultType updatePassword(int userId, String oldPassword, String newPassword) {
		ResultType status = ResultType.NOTHING;
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		//TODO to be revised this method
		String updateUser = "update users set password = ? where id = ? ";
		int updatedRows = em.createNativeQuery(updateUser).setParameter(1, newPassword).setParameter(2, userId).executeUpdate();
//		int updatedRows = jdbcTemplate.update(updateUser, new Object[] {user.getName(), user.getEmail(), user.getMobile(),
//				user.getAddress(), user.getUserId()});
//			System.out.println("update info:"+updateUser);
		
		if(updatedRows > 0) {
			status = ResultType.UPDATED;
		}
		
		return status;
	}

	@Override
	public List<User> getUsers(String type) {
		EntityManager em = getEntityManager();
		@SuppressWarnings("unchecked")
		List<User> userList = em.createNativeQuery("select * from users where type =?")
		.setParameter(1, type).getResultList();
		em.detach(userList);
		return userList;
	}

	@Override
	public User getSingleUser(String username) {
		EntityManager em = getEntityManager();
		User user = (User)em.createNativeQuery("select * from users where username =?")
			.setParameter(1, username).getSingleResult();
		em.detach(user);
		return user;
	}

	@Override
	public TgtUser getUser(String username, String type) {
		EntityManager em = getEntityManager();
		TgtUser tgtUser = (TgtUser)em.createNativeQuery("select * from users where username =? and type =?")
				.setParameter(1, username)
				.setParameter(2, type).getSingleResult();
		return tgtUser;
	}

	@Override
	public User getSingleUser(int id) {
		EntityManager em = getEntityManager();
		User user = (User)em.createNativeQuery("select * from users where id =?")
			.setParameter(1, id).getSingleResult();
		em.detach(user);
		return user;
	}

	@Override
	public ResultType delUser(int userId) {
		ResultType status = ResultType.NOTHING;
		EntityManager em = getEntityManager();
		int rows = em.createNativeQuery("delete from users where id = ?")
			.setParameter(1, userId).executeUpdate();
		if(rows > 0) {
			status = ResultType.DELETED;
		} else {
			status = ResultType.ERROR;
		}
		return status;
	}

	@Override
	public ResultType validateUser(String username, String email) {
		ResultType status = ResultType.ALREADY;
		EntityManager em = getEntityManager();
		String chkStmt = "select count(*) from users where username = ? or email = ? ";	
		Query query = em.createNativeQuery(chkStmt).setParameter(1, username).setParameter(2, email);
		query.setParameter(1, username).setParameter(2, email);
		int count = ((Number)query.getSingleResult()).intValue();	
		
		if(count > 0) {
			status = ResultType.ALREADY;
		} else {
			status = ResultType.NOTHING;
		} 
			
		return status;
	}

	@Override
	public ResultType validateProvider(String username) {
		ResultType status = ResultType.NOTHING;
		EntityManager em = getEntityManager();

		String chkStmt = "select count(*) from users where username = ? or email = ? ";	
		Query query = em.createNativeQuery(chkStmt).setParameter(1, username).setParameter(2, username);

		int count = ((Number)query.getSingleResult()).intValue();

		if(count > 0) {
			status = ResultType.ALREADY;
		} else {
			status = ResultType.NOTHING;
		} 
		
		return status;
	}

}
