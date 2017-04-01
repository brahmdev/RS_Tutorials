package com.devops.dev.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devops.dev.domainObject.Country;

@Repository
@Transactional
public class CountryDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(Country Country) {
		getSession().save(Country);
		return;
	}

	public void delete(Country Country) {
		getSession().delete(Country);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Country> getAll() {
		return getSession().createQuery("from Country").list();
	}

	public Country getByCountryCode(String countryCode) {
		return (Country) getSession().createQuery("from Country where countryCode = :countryCode").setParameter("countryCode", countryCode)
				.uniqueResult();
	}

	public Country getById(long id) {
		return (Country) getSession().load(Country.class, id);
	}

	public void update(Country Country) {
		getSession().update(Country);
		return;
	}
}
