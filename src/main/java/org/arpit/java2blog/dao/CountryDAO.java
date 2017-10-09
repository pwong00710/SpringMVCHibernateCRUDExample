package org.arpit.java2blog.dao;

import java.util.List;

import org.arpit.java2blog.model.Audit;
import org.arpit.java2blog.model.Country;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDAO extends BaseDAO {

	public List<Country> getAllCountries() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Country> countryList = session.createQuery("from Country").list();
		return countryList;
	}

	public Country getCountry(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Country country = (Country) session.get(Country.class, new Integer(id));
		return country;
	}

	public Country addCountry(Country country) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(country);
		audit(Audit.ACTION_INSERT);
		return country;
	}

	public void updateCountry(Country country) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(country);
		audit(Audit.ACTION_UPDATE);
	}

	public void deleteCountry(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Country p = (Country) session.load(Country.class, new Integer(id));
		if (null != p) {
			session.delete(p);
			audit(Audit.ACTION_DELETE);
		}
	} 
}
