package com.tutorialspoint.service;

import java.util.List;

import com.tutorialspoint.model.country.Country;

public interface CountryService {
    public Country getCountryDetails(int id);  
    
    public List<Country> getAllCountryDetails();
    
	public Country addCountryDetails(Country country);

	public void updateCountryDetails(Country country);

	public void deleteCountryDetails(int id);
}
