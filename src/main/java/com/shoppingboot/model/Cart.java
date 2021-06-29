package com.shoppingboot.model;

public class Cart {
	private int id;
	private int prodId;
	private String name;
	private String image;
	private String price;
	private String quantity;
	private String prodTotPrice;
	private String cartTotPrice;
	private String up_qty;
	private String down_qty;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProdTotPrice() {
		return prodTotPrice;
	}

	public void setProdTotPrice(String prodTotPrice) {
		this.prodTotPrice = prodTotPrice;
	}

	public String getCartTotPrice() {
		return cartTotPrice;
	}

	public void setCartTotPrice(String cartTotPrice) {
		this.cartTotPrice = cartTotPrice;
	}

	public String getUp_qty() {
		return up_qty;
	}

	public void setUp_qty(String up_qty) {
		this.up_qty = up_qty;
	}

	public String getDown_qty() {
		return down_qty;
	}

	public void setDown_qty(String down_qty) {
		this.down_qty = down_qty;
	}
}
