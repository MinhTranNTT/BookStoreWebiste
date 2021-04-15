package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UsersDAOTest extends BaseDAOTest {
	
	private static UsersDAO usersDAO;
	
	@BeforeClass
	public static void setupClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		usersDAO = new UsersDAO(entityManager);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
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
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 22;
		Users users = usersDAO.get(userId);
		if(users != null) {
			System.out.println(users.getEmail());
		}
		assertNotNull(users);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users users = usersDAO.get(userId);
		
		assertNull(users);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		
		user1 = usersDAO.create(user1);
		
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 26;
		usersDAO.delete(userId);
		
		Users user = usersDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistUser() {
		Integer userId = 26;
		usersDAO.delete(userId);
		
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
	
	@Test
	public void testListAll() {
		List<Users> listUsers = usersDAO.listAll();
		
		for(Users user: listUsers) {
			System.out.println(user.getEmail());
		}
		
		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = usersDAO.count();
		
		assertEquals(5, totalUsers);
		
	}
	
	@Test
	public void testFindByEmail() {
		String email = "levanloc@gmail.com";
		Users user = usersDAO.findByEmail(email);
		
		assertNotNull(user);
	}

}
