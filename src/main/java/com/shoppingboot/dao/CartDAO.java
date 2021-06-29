package com.shoppingboot.dao;

import java.util.List;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Cart;

public interface CartDAO {
	public ResultType addCart(Long userId, int prodId);
	public ResultType delCart(Long userId, int prodId);
	public ResultType updateCart(Long userId, int prodId, String type);
	public List<Cart> getCart(Long userId);


}
