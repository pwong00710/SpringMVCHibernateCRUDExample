package org.arpit.java2blog.service;

import java.util.List;

import org.arpit.java2blog.dao.CountryDAO;
import org.arpit.java2blog.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("countryService")
public class CountryService {

	@Autowired
	CountryDAO countryDao;

	public CountryDAO getCountryDao() {
		return countryDao;
	}

	public void setCountryDao(CountryDAO countryDao) {
		this.countryDao = countryDao;
	}

	@Transactional
	public List<Country> getAllCountries() {
		return countryDao.getAllCountries();
	}

	@Transactional
	public Country getCountry(int id) {
		return countryDao.getCountry(id);
	}

	@Transactional
	public Country addCountry(Country country) {
		return countryDao.addCountry(country);
	}

	@Transactional
	public void updateCountry(Country country) {
		countryDao.updateCountry(country);
	}

	@Transactional
	public void deleteCountry(int id) {
		countryDao.deleteCountry(id);
	}
}

