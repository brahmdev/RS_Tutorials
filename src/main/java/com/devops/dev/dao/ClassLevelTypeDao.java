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
public class ClassLevelTypeDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(ClassLevelType ClassLevelType) {
		getSession().save(ClassLevelType);
		return;
	}

	public void delete(ClassLevelType ClassLevelType) {
		getSession().delete(ClassLevelType);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<ClassLevelType> getAll() {
		/*return getSession().createQuery("from ClassLevelType as ct right outer join ct.board").list();*/
		return getSession().createQuery("from ClassLevelType").list();
	}

	public ClassLevelType getByClassLevelName(String classLevelName) {
		return (ClassLevelType) getSession().createQuery("from ClassLevelType where description = :description").setParameter("description", classLevelName)
				.uniqueResult();
	}

	public ClassLevelType getById(long id) {
		return (ClassLevelType) getSession().load(ClassLevelType.class, id);
	}

	public void update(ClassLevelType classLevelType) {
		getSession().update(classLevelType);
		return;
	}
	
	@SuppressWarnings("unchecked")
	public List<Board> getAllBoards() {
		/*return getSession().createQuery("from ClassLevelType as ct right outer join ct.board").list();*/
		return getSession().createQuery("from Board").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ClassLevel> getAllClassLevel() {
		/*return getSession().createQuery("from ClassLevelType as ct right outer join ct.board").list();*/
		return getSession().createQuery("from ClassLevel").list();
	}

	public ClassLevelType getClassLevelType(int classLevelId, int boardId, String className, String language) {
		// TODO Auto-generated method stub
		return (ClassLevelType) getSession().createQuery("from ClassLevelType where className = :className and language = :language and classLevel.classLevelId = :classLevelId and board.boardId = :boardId")
				.setParameter("className", className)
				.setParameter("language", language)
				.setParameter("boardId", boardId)
				.setParameter("classLevelId", classLevelId)
				.uniqueResult();
	}

}
