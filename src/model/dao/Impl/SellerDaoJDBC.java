package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.PhoneNumber;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	private InstantiationImpl InstImpl;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO seller(Name, Id, Email, BirthDate, BaseSalary, Department_Id) VALUES(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());

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
	public void update(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE seller s" + "SET s.Name = ?, s.Email = ?, s.BirthDate = ?, "
					+ "s.BaseSalary = ?, s.Department_Id = ? " + "WHERE Id = ?");

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Integer phoneSellerId;
		try {
			st = conn.prepareStatement(
					"SELECT s.*,d.name as depName, pn.Id as PhoneID, pn.phonenumber as PhoneNumber FROM seller s "
					+"INNER JOIN phonenumber pn ON s.Id = pn.Id_Seller "
					+"INNER JOIN department d ON s.Department_Id = d.Id "
					+"WHERE s.id = ? ORDER BY s.Name");
			

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = InstImpl.instantiateDepartment(rs);
				PhoneNumber pn = InstImpl.instantiatePhoneNumber(rs);
				Seller seller = InstImpl.instantiateSeller(rs, dep, pn);
				return seller;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT s.*, d.name as 'depName', pn.Id as 'Phone ID' , pn.phonenumber as 'Phone Number' FROM seller s "
					+"INNER JOIN phonenumber pn ON s.Id = pn.Id_Seller "
					+"INNER JOIN department d ON s.Department_Id = d.Id ORDER BY s.Name"
					);
			
			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();

			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {
				Department dep = map.get(rs.getInt("s.Department_Id"));
				if (dep == null) {
					dep = InstImpl.instantiateDepartment(rs);
					map.put(rs.getInt("s.Department_Id"), dep);
				}
				//repair seller - Impl - Pn
				//Seller seller = InstImpl.instantiateSeller(rs, dep);
				//list.add(seller);
				
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT s.*, d.Name as DepName FROM seller s "  
					+"INNER JOIN department d ON s.Department_Id = d.id "  
					+"WHERE s.Department_Id = ? ORDER BY s.name"
					);

			st.setInt(1, department.getId());

			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> mapDep = new HashMap<>();

			while (rs.next()) {
				Department depart = mapDep.get(rs.getInt("s.Department_Id"));
				
				if (depart == null) {
					depart = InstImpl.instantiateDepartment(rs);
					mapDep.put(rs.getInt("s.Department_Id"), depart);
				}

				Seller obj = InstImpl.instantiateSeller(rs, depart);
				list.add(obj);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	/*public List<Seller> findByPhoneNumber(String phoneNumber) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
			"SELECT s.*, pn.Id as PhoneID, pn.phonenumber as PhoneNumber, d.Name as DepName FROM seller s "
			+ "INNER JOIN phonenumber pn ON s.Id = pn.Id_Seller "
			+ "INNER JOIN department d ON s.Department_Id = d.Id "
			+ "WHERE pn.phonenumber = ? ORDER BY s.Name;");
			
			st.setString(1, x);
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return null;
	}*/
}