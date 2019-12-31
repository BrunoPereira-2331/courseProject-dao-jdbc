package model.entities;

import java.io.Serializable;

public class PhoneNumber implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id = 1;
	private int idSeller = 2;
	private String phoneNumber = "2222-2222";
	
	public PhoneNumber () {}
	
	public PhoneNumber(int id, int idSeller, String phoneNumber) {
		this.id = id;
		this.idSeller = idSeller;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSeller() {
		return idSeller;
	}

	public void setIdSeller(int idSeller) {
		this.idSeller = idSeller;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Phone ID:" + id 
				+ "\n Seller ID:" + idSeller 
				+ "\n Phone Seller:" + phoneNumber;
	}
}