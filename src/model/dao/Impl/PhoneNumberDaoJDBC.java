package model.dao.Impl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.phoneNumberDao;
import model.entities.PhoneNumber;

public class PhoneNumberDaoJDBC implements phoneNumberDao {

	private Connection conn;

	public PhoneNumberDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(PhoneNumber pn) {
		PreparedStatement st = null;
		List <String> listPn = pn.getPhoneNumber();
		try {
			Integer rowsAffected = null;
			st = conn.prepareStatement("INSERT INTO phoneNumber(Id_Seller, phoneNumber) " + "VALUES(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, pn.getIdSeller());
			for (String list : listPn) {
			st.setString(2, list);
			rowsAffected = st.executeUpdate();
			}
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					pn.setId(id);
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
	public List<PhoneNumber> findAll() {
		return null;
	}

}
