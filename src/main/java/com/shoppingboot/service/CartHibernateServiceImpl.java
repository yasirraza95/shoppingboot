package com.shoppingboot.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Cart;
import com.shoppingboot.model.CartEntity;
import com.shoppingboot.model.CartModelInterface;
import com.shoppingboot.model.TgtUser;
import com.shoppingboot.repository.CartRepository;

@Service
@Transactional
public class CartHibernateServiceImpl implements CartHibernateService {

	@Autowired
	private CartRepository cartRepository;
	
	private Authentication auth = null;
	
	
	@Override
	public List<CartModelInterface> getCart() {
		auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		List<CartModelInterface> cartList = cartRepository.getCart(tgtUser.getId());
//		System.out.println("list="+cartList.iterator());
		return cartList;
		
	}


	@Override
	public ResultType addCart(int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		ResultType response = ResultType.NOTHING;
		
		int status = 0;
//		String quantity = cartRepository.checkQuantity(tgtUser.getId(), id);
		CartEntity cartEntity = cartRepository.findOneByUserIdAndProdId(tgtUser.getId(), id);
//		System.out.println("entity="+cartEntity);
		int quantity = 0;
		
		try {
			quantity = cartEntity.getQuantity();
		} catch(NullPointerException e) {
			quantity = 0;
		}

		
//				System.out.println("qty="+quantity);
		int counter = 0;
		if(quantity > 0) {
			counter = quantity + 1; 
//			System.out.println("counter="+counter);
			status = cartRepository.updateCart(tgtUser.getId(), id, counter);
			response = ResultType.UPDATED;
		} else {
			status = cartRepository.addCart(tgtUser.getId(), id);
			response = ResultType.CREATED;
		}
		
		return response;
	}


	@Override
	public int getCartCounter(long userId) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		String counter = cartRepository.countByUserId(tgtUser.getId());
//		System.out.println("ctr="+counter);
		return Integer.parseInt(counter);
	}


	@Override
	public ResultType updateCart(int prodId, String type) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		ResultType status = ResultType.NOTHING;
		//check quantity then update
//		String dbQuantity = cartRepository.checkQuantity(tgtUser.getId(), prodId);
		CartEntity cartEntity = cartRepository.findOneByUserIdAndProdId(tgtUser.getId(), prodId);
		String dbQuantity = ""+cartEntity.getQuantity();
		int finalQuantity;
		
		if(type.equals("up")) {
			finalQuantity = Integer.parseInt(dbQuantity) + 1;
			System.out.println(dbQuantity);
		} else {
			finalQuantity = Integer.parseInt(dbQuantity) - 1;
		}
		
		//TODO work from here to replace nativequery method
//		int exist = cartRepository.findByUserIdAndProdId(tgtUser.getId(), prodId);
//		if(exist > 0) {
//			
//		}
		
//		int response = cartRepository.updateCart(tgtUser.getId(), prodId, finalQuantity);
		
		CartEntity exists = cartRepository.findOneByUserIdAndProdId(tgtUser.getId(), prodId);
		//TODO work from here
		CartEntity cartQty = new CartEntity();
		cartQty.setId(exists.getId());
		cartQty.setUserId(tgtUser.getId());
		cartQty.setProdId(prodId);
		cartQty.setQuantity(finalQuantity);
		
		if(exists.getId() > 0) {
			cartRepository.save(cartQty);
			status = ResultType.UPDATED;
		}
		
//		if(response > 0) {
//			status = ResultType.UPDATED;
//		}
		
		return status;
	}


	@Override
	public ResultType removeCart(int prodId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		ResultType status = ResultType.NOTHING;
//		int response = cartRepository.removeCart(tgtUser.getId(), prodId);
//		System.out.println("res="+response);
		
//		if(response > 0) {
//			status = ResultType.DELETED;
//		}
		
		cartRepository.deleteByUserIdAndProdId(tgtUser.getId(), prodId);
		status = ResultType.DELETED;
		
		return status;
	}

}
