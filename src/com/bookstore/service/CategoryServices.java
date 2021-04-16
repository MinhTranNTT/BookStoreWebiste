package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;


public class CategoryServices {
	
	private EntityManager entityManager;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CategoryServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
		
		categoryDAO = new CategoryDAO(entityManager);
	}

	public void listCategory() throws ServletException, IOException  {
		listCategory(null);
	}
	
	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("listCategory", listCategory);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name");
		Category existCategory = categoryDAO.findByName(name);
		
		if (existCategory != null) {
			
			String message = "Cound not create category. A category with name " + name + " already exists.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
			
		} else {
			
			Category category = new Category(name);
			categoryDAO.create(category);
			String message = "New category created successfully";
			listCategory(message);
		}
	}

	public void editCategory() throws ServletException, IOException {
		int cateId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(cateId);
		
		String destPage = "category_form.jsp";
		
		if(category == null) {
			destPage = "message.jsp"; 
			String message = "Could not edit category with " + cateId;
			
			request.setAttribute("message", message);
			
		} else {
			
			request.setAttribute("category", category);	
		}
		
//		request.setAttribute("category", category);	
//		String editPage = "category_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		int cateId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");
		
		Category categoryById = categoryDAO.get(cateId);
		Category categoryByName = categoryDAO.findByName(categoryName);
		
		if (categoryByName != null && categoryByName.getCategoryId() != categoryById.getCategoryId()) {
			String message = "Cound not update. A category with name '" + categoryName + "' already exist";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
			
		} else {
			
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			String message = "Category has been updated succesfully";
			listCategory(message);
		}
		
	}

	public void deleteCategory() throws ServletException, IOException {
		int cateId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(cateId);
		String message = "You has been deleted successfully";
		
		if(category == null) {
			
			message = "Cound not delete category. A category with " + category.getName() + " not exist";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(message);
			requestDispatcher.forward(request, response);
		} else {
			
			categoryDAO.delete(cateId);
			listCategory(message);
		}
	}
	
	
}
