package com.tutorialspoint.service;

import java.util.ArrayList;
import java.util.List;

import org.arpit.java2blog.dao.CountryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorialspoint.model.country.Country;

@Service 
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryDAO countryDao;
	
	@Transactional
	public Country getCountryDetails(int id)  
	{  		
		org.arpit.java2blog.model.Country x = countryDao.getCountry(id);		
		return clone(x);  
	}
	
	@Transactional
	public List<Country> getAllCountryDetails() {
		List<org.arpit.java2blog.model.Country> xl = countryDao.getAllCountries();
		
		if (xl == null || xl.size() == 0) return null;
		
		List<Country> countryList = new ArrayList<Country>();
		
		for ( org.arpit.java2blog.model.Country x : xl) {
			Country country = clone(x);
			countryList.add(country);
		}
		
		return countryList;
	}
	
	@Transactional
	public Country addCountryDetails(Country country) {
		org.arpit.java2blog.model.Country x = countryDao.addCountry(clone(country));		
		return clone(x);
	}

	@Transactional
	public void updateCountryDetails(Country country) {
		countryDao.updateCountry(clone(country));

	}

	@Transactional
	public void deleteCountryDetails(int id) {
		countryDao.deleteCountry(id);
	}
	
	private Country clone(org.arpit.java2blog.model.Country x) {
		if (x == null) return null;
		
		Country country = new Country();
		country.setId(x.getId());  
		country.setCountryName(x.getCountryName());
		country.setPopulation(x.getPopulation());
		return country;  		
	}
	
	private org.arpit.java2blog.model.Country clone(Country country) {
		if (country == null) return null;
		
		org.arpit.java2blog.model.Country x = new org.arpit.java2blog.model.Country();
		x.setId(country.getId());  
		x.setCountryName(country.getCountryName());
		x.setPopulation(country.getPopulation());
		return x;  		
	}
}
