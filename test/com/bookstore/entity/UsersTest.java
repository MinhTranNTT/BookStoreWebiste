package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class UsersTest {

	public static void main(String[] args) {
		
		Users user1 = new Users();
		user1.setEmail("nguyenngoc@gmail.com");
		user1.setFullName("Nguyen Ngoc");
		user1.setPassword("kisseslove");
		
		EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = createEntityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(user1);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		createEntityManagerFactory.close();
		
		System.out.println("a Users Object was persisted");
		
	}

}
