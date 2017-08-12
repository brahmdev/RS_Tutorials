package com.devops.dev.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devops.dev.domainObject.Chapter;

@Repository
@Transactional
public class ChapterDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(Chapter Chapter) {
		getSession().save(Chapter);
		return;
	}

	public void delete(Chapter Chapter) {
		getSession().delete(Chapter);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Chapter> getAll() {
		/*return getSession().createQuery("from Chapter as ct right outer join ct.board").list();*/
		return getSession().createQuery("from Chapter").list();
	}

	public Chapter getByChapterName(String classLevelName) {
		return (Chapter) getSession().createQuery("from Chapter where description = :description").setParameter("description", classLevelName)
				.uniqueResult();
	}

	public Chapter getById(long id) {
		return (Chapter) getSession().load(Chapter.class, id);
	}

	public void update(Chapter chapter) {
		getSession().update(chapter);
		return;
	}
}
