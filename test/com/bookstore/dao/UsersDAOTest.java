package com.bookstore.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UsersDAOTest {
	private static EntityManagerFactory createEntityManagerFactory;
	private static EntityManager entityManager;
	private static UsersDAO usersDAO;
	
	
	@BeforeClass
	public static void setupClass() {
		createEntityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = createEntityManagerFactory.createEntityManager();
		
		usersDAO = new UsersDAO(entityManager);
	}
	
	@AfterClass
	public static void tearDownClass() {
		entityManager.close();
		createEntityManagerFactory.close();
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("vancanh@gmail.com");
		user1.setFullName("Van Canh");
		user1.setPassword("canhvan");
		
		user1 = usersDAO.create(user1); 
		
		assertTrue(user1.getUserId() > 0);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		
		user1 = usersDAO.create(user1);
		
	}
	
	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(22);
		user.setEmail("minhtran@gmail.com");
		user.setPassword("mydarkness");
		user.setFullName("Minh Tran");
		
		user = usersDAO.update(user);
		String expected = "mydarkness";
		String actual = user.getPassword();
		
		assertEquals(expected, actual);
	}

}
