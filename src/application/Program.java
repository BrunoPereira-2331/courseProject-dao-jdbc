package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.dao.phoneNumberDao;
import model.entities.Department;
import model.entities.Seller;
import model.entities.PhoneNumber;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		phoneNumberDao phoneNumberDao = DaoFactory.createPhoneNumberDao();
		Department department = new Department(1, null);
		
		System.out.println("=== TEST 1: seller findByDepartment ===");
		//List<Seller> list = sellerDao.findByDepartment(department);
		//for (Seller obj : list) {
		//	System.out.println(obj);
		//	System.out.println();
		//}
		
		/*System.out.println("=== TEST 2: seller findAll ===");
		List<Seller> list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}*/
		
		System.out.println("=== TEST 3: seller findById");
		
		System.out.println("=== TEST 4: PhoneNumber insert ===");
		List<String> phoneNumberList = new ArrayList<>();
		phoneNumberList.add("88888888");
		phoneNumberList.add("88888888");
		
		
		PhoneNumber pn = new PhoneNumber(20, 8, phoneNumberList);
		phoneNumberDao.insert(pn);
		
		
		
		
		
		
		sc.close();
	}	
	}