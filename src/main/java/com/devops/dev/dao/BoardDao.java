package com.devops.dev.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devops.dev.domainObject.Board;
import com.devops.dev.domainObject.Board;

@Repository
@Transactional
public class BoardDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(Board board) {
		getSession().save(board);
		return;
	}

	public void delete(Board board) {
		getSession().delete(board);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Board> getAll() {
		return getSession().createQuery("from Board").list();
	}

	public Board getByBoardName(String boardName) {
		return (Board) getSession().createQuery("from Board where boardName = :boardName").setParameter("boardName", boardName)
				.uniqueResult();
	}

	public Board getById(long id) {
		return (Board) getSession().load(Board.class, id);
	}

	public void update(Board board) {
		getSession().update(board);
		return;
	}
}
