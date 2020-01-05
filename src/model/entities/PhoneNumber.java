package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumber implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private int idSeller;
	private List<String> phoneNumber = new ArrayList<>();
	
	public PhoneNumber () {}
	
	public PhoneNumber(Integer id, int idSeller, List<String> phoneNumber) {
		this.id = id;
		this.idSeller = idSeller;
		for (String pn : phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
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

	public List<String> getPhoneNumber() {
		return phoneNumber;
	}
	
	public void addPhoneNumber(String phoneNumber) {
		this.phoneNumber.add(phoneNumber);
	}

	public void removePhoneNumber(Integer id) {
		this.phoneNumber.remove(id);
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