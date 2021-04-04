package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoryTest {
	
	public static void main(String[] args) {
		
		Category category1 = new Category("Code Java");
				
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(category1);
		
		entityManager.getTransaction().commit();
		
		// Close
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("a Category Object was persisted");
	}

}
