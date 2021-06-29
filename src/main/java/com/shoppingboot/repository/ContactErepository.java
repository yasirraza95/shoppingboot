package com.shoppingboot.repository;

import com.shoppingboot.enums.ResultType;

public interface ContactErepository {

	public ResultType insert(String name, String email, String reason, String message, Long time);
}
