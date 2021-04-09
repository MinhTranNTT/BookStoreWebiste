package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UsersDAO extends JpaDAO<Users> implements GenericDAO<Users>{

	public UsersDAO(EntityManager entityManager) {
		super(entityManager);
	}

	
	// Vì lớp JpaDAO đã có hàm create, sử dụng super để thực hiện phương thức đó
	// Because create() of JpaDAO class has been, call it with super 
	public Users create(Users user) {
		return super.create(user);
	}
	
	@Override
	public Users update(Users user) {
		return super.update(user);
	}

	@Override
	public Users get(Object userId) {
		return super.find(Users.class, userId);
	}

	@Override
	public void delete(Object userId) {
		super.delete(Users.class, userId);
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNameQuery("Users.findAll");
	}

	@Override
	public long count() {
		return super.countWithNameQuery("Users.countAll");
	}
	
	public Users findByEmail(String email) {
		List<Users> listUsers = super.findWithNameQuery("Users.findByEmail", "email", email);
		
		if(listUsers != null && listUsers.size() > 0) {
			return listUsers.get(0);
		}
		return null;
	}
	

}
