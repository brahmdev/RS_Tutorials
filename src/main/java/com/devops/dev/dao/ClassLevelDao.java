package com.devops.dev.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devops.dev.domainObject.Board;
import com.devops.dev.domainObject.ClassLevel;
import com.devops.dev.domainObject.ClassLevelType;

@Repository
@Transactional
public class ClassLevelDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(ClassLevel ClassLevel) {
		getSession().save(ClassLevel);
		return;
	}

	public void delete(ClassLevel ClassLevel) {
		getSession().delete(ClassLevel);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<ClassLevel> getAll() {
		return getSession().createQuery("from ClassLevel").list();
	}

	public ClassLevel getByClassLevelName(String classLevelName) {
		return (ClassLevel) getSession().createQuery("from ClassLevel where description = :description").setParameter("description", classLevelName)
				.uniqueResult();
	}

	public ClassLevel getById(long id) {
		return (ClassLevel) getSession().load(ClassLevel.class, id);
	}

	public void update(ClassLevel classLevel) {
		getSession().update(classLevel);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<ClassLevelType> getAllClassLevelType() {
		return getSession().createQuery("from ClassLevelType").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ClassLevelType> getClassLevelTypeForBoard(String boardId) {
		return (List<ClassLevelType>) getSession().createQuery("from ClassLevelType where board.boardId = :boardId").setParameter("boardId", Integer.parseInt(boardId))
		.list();
	}
	
}
