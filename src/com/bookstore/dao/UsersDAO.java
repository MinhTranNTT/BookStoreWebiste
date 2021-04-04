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
	public Users get(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Users> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
