package com.devops.dev.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devops.dev.domainObject.Subject;

@Repository
@Transactional
public class SubjectDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(Subject Subject) {
		getSession().save(Subject);
		return;
	}

	public void delete(Subject Subject) {
		getSession().delete(Subject);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Subject> getAll() {
		/*return getSession().createQuery("from Subject as ct right outer join ct.board").list();*/
		return getSession().createQuery("from Subject").list();
	}

	public Subject getBySubjectName(String classLevelName) {
		return (Subject) getSession().createQuery("from Subject where description = :description").setParameter("description", classLevelName)
				.uniqueResult();
	}

	public Subject getById(long id) {
		return (Subject) getSession().load(Subject.class, id);
	}

	public void update(Subject subject) {
		getSession().update(subject);
		return;
	}

	public Subject getSubject(String subjectName, int classLevelTypeId) {
		return (Subject) getSession().createQuery("from Subject where subjectName = :subjectName and classLevelType.classLevelTypeId = :classLevelTypeId")
				.setParameter("subjectName", subjectName)
				.setParameter("classLevelTypeId", classLevelTypeId)
				.uniqueResult();
	}
}
