package com.shoppingboot.model;

import java.io.Serializable;
public class Orders implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String ordNo;
	private String charges;
	private String prodName;
	private String quantity;
	private String image;
	private String status;
	private String shipName;
	private String shipMobile;
	private String shipAddress;
	private String date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getShipMobile() {
		return shipMobile;
	}
	public void setShipMobile(String shipMobile) {
		this.shipMobile = shipMobile;
	}
	public String getShipAddress() {
		return shipAddress;
	}
	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
