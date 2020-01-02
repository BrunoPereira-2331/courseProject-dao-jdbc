package model.dao;

import java.util.List;

import model.entities.PhoneNumber;
import model.entities.Seller;

public interface phoneNumberDao {

	void insert(PhoneNumber obj);
	void update(PhoneNumber obj);
	void deleteById(Integer id);
	PhoneNumber findById(Integer id);
	List<PhoneNumber> findBySeller(Seller seller);
	List<PhoneNumber> findAll();
}
