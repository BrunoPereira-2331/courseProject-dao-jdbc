package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

import db.DB;
import db.DbException;
import model.dao.phoneNumberDao;
import model.entities.PhoneNumber;
import model.entities.Seller;

public class PhoneNumberDaoJDBC implements phoneNumberDao {

	private Connection conn;

	public PhoneNumberDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(PhoneNumber obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO phoneNumber(Id_Seller, phoneNumber) " + "VALUES(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getIdSeller());
			st.setString(2, obj.getPhoneNumber());
			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected");

			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(PhoneNumber obj) {

	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public PhoneNumber findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement(
					"SELECT s.Id, s.Name, pn.Id_Seller ,pn.phonenumber FROM seller s "
					+ "INNER JOIN phoneNumber pn ON s.id = pn.Id_Seller WHERE s.id = ?;"
					);
					
			return null;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public List<PhoneNumber> findBySeller(Seller seller) {
		return null;
	}

	@Override
	public List<PhoneNumber> findAll() {
		return null;
	}

}
