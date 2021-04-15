package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest{

	private static CategoryDAO categoryDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		categoryDAO = new CategoryDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateCategory() {
		Category newCate = new Category("Javascript");
		Category category = categoryDAO.create(newCate);
		
		assertTrue(category != null && category.getCategoryId() > 0);
	}

	@Test
	public void testGet() {
		Integer cateId = 16;
		Category cate = categoryDAO.get(cateId);
		
		assertNotNull(cate);
	}

	@Test
	public void testDeleteCategory() {
		Integer cateId = 16;
		categoryDAO.delete(cateId);
		
		Category category = categoryDAO.get(cateId);
		
		assertNull(category);
	}

	@Test
	public void testListAll() {
		List<Category> listCate = categoryDAO.listAll();
		listCate.forEach(c -> System.out.println(c.getName()));
		assertTrue(listCate.size() > 0);
	}

	@Test
	public void testCount() {
		long countCate = categoryDAO.count();
		System.out.println(countCate);
		assertTrue(countCate > 0);
	}
	
	@Test
	public void testUpdateCategory() {
		Category cate = new Category("Advanced Javascript");
		cate.setCategoryId(17);
		
		Category category = categoryDAO.update(cate);
		
		assertEquals(cate.getName(), category.getName());
	}

}
