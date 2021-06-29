package com.shoppingboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.shoppingboot.dao.CartDAO;
import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Cart;
import com.shoppingboot.model.TgtUser;

@Component
public class CartServiceImpl implements CartService {

	@Autowired
	CartDAO cartDAO;
	
	public List<Cart> getCart() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		List<Cart> cartData = cartDAO.getCart(tgtUser.getId());
		
		return cartData;
	}
	
	public ResultType addCart(int id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		ResultType response = cartDAO.addCart(tgtUser.getId(), id);
		return response;
	}
	
	public ResultType updateCart(int prodId, String type) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		ResultType response = cartDAO.updateCart(tgtUser.getId(), prodId, type);
		return response;
	}
	
	
	public ResultType removeCart(int prodId) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TgtUser tgtUser = (TgtUser)auth.getPrincipal();
		
		ResultType response = cartDAO.delCart(tgtUser.getId(), prodId);
		return response;
	}

	@Override
	public List<Cart> getCartCounter(Long userId) {
		List<Cart> cartCounter = cartDAO.getCart(userId);		
		return cartCounter;
	}
	
}
