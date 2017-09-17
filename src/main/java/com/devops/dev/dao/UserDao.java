package com.devops.dev.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devops.dev.domainObject.Board;
import com.devops.dev.domainObject.StudentDetail;
import com.devops.dev.domainObject.UserRoles;
import com.devops.dev.domainObject.Users;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(Users users) {
		getSession().save(users);
		return;
	}

	public void delete(Users Users) {
		getSession().delete(Users);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Users> getAll() {
		/*return getSession().createQuery("from Users as ct right outer join ct.board").list();*/
		return getSession().createQuery("from Users").list();
	}

	public Users getByUsersName(String username) {
		return (Users) getSession().createQuery("from Users where username = :username").setParameter("username", username)
				.uniqueResult();
	}

	public void update(Users user) {
		getSession().update(user);
		return;
	}
	
	@SuppressWarnings("unchecked")
	public List<Board> getAllBoards() {
		/*return getSession().createQuery("from Users as ct right outer join ct.board").list();*/
		return getSession().createQuery("from Board").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> getAllUsers() {
		/*return getSession().createQuery("from Users as ct right outer join ct.board").list();*/
		return getSession().createQuery("from Users").list();
	}

	public void saveStudentDetail(StudentDetail studentDetail) {
		getSession().save(studentDetail);
		return;
	}

	public void saveRole(UserRoles userRole) {
		getSession().save(userRole);
		return;
	}


}
