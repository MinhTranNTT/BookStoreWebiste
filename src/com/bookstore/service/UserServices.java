package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UsersDAO;
import com.bookstore.entity.Users;

public class UserServices {
	
	private EntityManager entityManager;
	private UsersDAO usersDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
		
		usersDAO = new UsersDAO(entityManager);
	}
	
	public void listUser(String message) throws ServletException, IOException {
		List<Users> listUsers = usersDAO.listAll();
		
		request.setAttribute("listUsers", listUsers);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	
	}
	
	public void listUser() throws ServletException, IOException {
		listUser(null);
	}

	public void createUser() throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Users existUser = usersDAO.findByEmail(email);
		
		if(existUser != null) {
			
			String message = "Could not create user. A user with email '" + email + "' already exist";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} 
		else {
			Users user = new Users(email, fullname, password);
			usersDAO.create(user);
			listUser("New Created User Succesfully");
		}
		
		
	}

	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users users = usersDAO.get(userId);
		
		String destPage = "user_form.jsp";
		
		if(users == null) {
			
			destPage = "message.jsp"; 
			String message = "Could not find user with " + userId;
			
			request.setAttribute("message", message);
			
		} else {
			
			request.setAttribute("user", users);	
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);

	}

	public void updateUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Users userById = usersDAO.get(userId);
		Users userByEmail = usersDAO.findByEmail(email);
	
		
		if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Cound not update user. User with email " + email + " already exists.";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Users user = new Users(userId, email, fullName, password);
			usersDAO.update(user);
			
			String message = "User has been update successfully";
			listUser(message);
		}	
		
	}

	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = usersDAO.get(userId);
		String message = "You has been delete successfully";
		
		if (user == null) {
			
			message = "Cound not delete user. User with ID " + userId + " has been remove.";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			
			usersDAO.delete(userId);
			listUser(message);
		}
		
		
	}
}
