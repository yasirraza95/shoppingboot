package com.shoppingboot.service;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

import com.shoppingboot.enums.ResultType;
import com.shoppingboot.model.Cart;
import com.shoppingboot.model.CartEntity;
import com.shoppingboot.model.CartModelInterface;

@Component
public interface CartHibernateService {

	public List<CartModelInterface> getCart();
	public ResultType addCart(int id);
	public int getCartCounter(long userId);
	public ResultType updateCart(int prodId, String type);
	public ResultType removeCart(int prodId);
}
