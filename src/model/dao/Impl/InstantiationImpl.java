package model.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.entities.Department;
import model.entities.PhoneNumber;
import model.entities.Seller;

public class InstantiationImpl {
	
	protected static Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(dep);
		return seller;
	}
	
	protected static Seller instantiateSeller(ResultSet rs, Department dep, PhoneNumber pn) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(dep);
		seller.setPhoneNumber(pn);
		return seller;
	}

	protected static Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("s.Department_Id"));
		dep.setName(rs.getString("d.name"));
		return dep;
	}
	
	protected static PhoneNumber instantiatePhoneNumber(ResultSet rs) throws SQLException {
		PhoneNumber pn = new PhoneNumber();
		pn.setId(rs.getInt("pn.Id"));
		pn.setIdSeller(rs.getInt("s.Id"));
		while(rs.next()) {
			pn.addPhoneNumber(rs.getString("pn.phonenumber"));
		}
		return pn;
	}
}