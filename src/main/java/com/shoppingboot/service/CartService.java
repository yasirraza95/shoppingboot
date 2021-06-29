package com.shoppingboot.service;

import java.util.List;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Cart;

public interface CartService {

	public List<Cart> getCart();
	public List<Cart> getCartCounter(Long userId);
	public ResultType addCart(int id);
	public ResultType updateCart(int prodId, String type);
	public ResultType removeCart(int prodId);
	
}
