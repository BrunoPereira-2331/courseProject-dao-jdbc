package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Department department = new Department(2, null);
		
		System.out.println("=== Test 1: Seller findById ===");
		System.out.println(sellerDao.findById(3));
		//JOptionPane.showMessageDialog(null, sellerDao.findById(3));
		System.out.println("\n==========================");
		
		System.out.println("=== Test 2: Seller findByDepartment ===");
		List<Seller> list = sellerDao.findByDepartment(department);
	
		for (Seller obj : list) {
			System.out.println(obj);
		}		
		System.out.println("\n==========================");
		
		System.out.println("=== Test 3: Seller findAll ===");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}	
		System.out.println("\n==========================");
		
		System.out.println("\n=== TEST 4: seller insert =====");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
		
		System.out.println("\n=== TEST 5: seller update =====");
		Seller seller = sellerDao.findById(1);
		seller.setName("Martha Wayne");
		sellerDao.update(seller);
		System.out.println("Update completed");
		
		System.out.println("\n=== TEST 6: seller delete =====");
		Seller newSeller2 = new Seller(null, "anna", "anna@gmail.com", new Date(), 3000.0, department);
		sellerDao.insert(newSeller2);
		System.out.println("Inserted! New id = " + newSeller2.getId());
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed");

		sc.close();
		
	}	
	}