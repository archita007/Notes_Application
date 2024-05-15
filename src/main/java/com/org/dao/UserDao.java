package com.org.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.org.dto.User;

public class UserDao {
	public User saveUser(User user)
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("karthik");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		
		entityTransaction.begin();
		
		 entityManager.persist(user);
		 
		 entityTransaction.commit();
		 return user;
		
	}
	
	public User getUserById(int id)
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		User user = entityManager.find(User.class,id);
		
		
		
		return user;
	}
	
	public List<User> getAllUsers()
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String sql = "SELECT u FROM User u";
		
		Query query = entityManager.createQuery(sql);
		
		List<User> users = query.getResultList();
		
		
		return users;
		
	}
	
	public User validateUser(String email,String password)
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String sql = "SELECT u FROM User u WHERE u.email =?1 AND u.password = ?2";
		Query query = entityManager.createQuery(sql);
		
		query.setParameter(1, email);
		query.setParameter(2, password);
		
		List<User> users = query.getResultList();
		
		if (users.size()>0)
		{
			User user = users.get(0);
			return user;
			
		} else {
			System.out.println("No user found");
			return null;
		}
	}
	
	public boolean deleteUserById(int id)
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		User user = entityManager.find(User.class, id);
		
		entityTransaction.begin();
		
		if (user != null)
		{
			entityManager.remove(user);
			
			entityTransaction.commit();
			return true;
			
		}
		else
		{
			return false;
		}
		
		
		
		
	}

}

