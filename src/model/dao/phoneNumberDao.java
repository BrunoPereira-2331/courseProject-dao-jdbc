package model.dao;

import java.util.List;

import model.entities.PhoneNumber;
import model.entities.Seller;

public interface phoneNumberDao {

	void insert(PhoneNumber obj);
	void update(PhoneNumber obj);
	void deleteById(Integer id);
	List<PhoneNumber> findAll();
}
