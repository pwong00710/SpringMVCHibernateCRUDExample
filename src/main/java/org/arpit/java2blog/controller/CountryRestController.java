package org.arpit.java2blog.controller;

import java.util.List;

import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.model.Status;
import org.arpit.java2blog.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryRestController {

	@Autowired
	CountryService countryService;
	
	private static final int exit_success = 0; 
	private static final int exit_failure = 1;

	@RequestMapping(value = "/rest/getAllCountries", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Country> getCountries() {

		List<Country> listOfCountries = countryService.getAllCountries();
		return listOfCountries;
	}
	
	@RequestMapping(value = "/rest/v/getAllCountries", method = RequestMethod.GET, produces = "application/org.arpit.java2blog.v1+json")
	public List<Country> getCountriesV1() {

		List<Country> listOfCountries = countryService.getAllCountries();
		return listOfCountries;
	}
	
	@RequestMapping(value = "/rest/v/getAllCountries", method = RequestMethod.GET, produces = "application/org.arpit.java2blog.v2+json")
	public List<Country> getCountriesV2() {

		List<Country> listOfCountries = countryService.getAllCountries();
		return listOfCountries;
	}	
	

	@RequestMapping(value = "/rest/getCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Country getCountryById(@PathVariable int id) {
		return countryService.getCountry(id);
	}

	@RequestMapping(value = "/rest/addCountry", method = RequestMethod.POST, headers = "Accept=application/json")
	public Country addCountry(@RequestBody Country country) {
		if (country.getId() == 0)
		{
			return countryService.addCountry(country);
		} else {
			countryService.updateCountry(country);
			return country;
		}
	}

	@RequestMapping(value = "/rest/updateCountry", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Status updateCountry(@RequestBody Country country) {
		countryService.updateCountry(country);
		return new Status(exit_success);
	}

	@RequestMapping(value = "/rest/deleteCountry/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public Status deleteCountry(@PathVariable("id") int id) {
		countryService.deleteCountry(id);
		return new Status(exit_success);
	}
	
//	@RequestMapping(value = "/rest/login", method = RequestMethod.GET, headers = "Accept=application/json")
//	public Status login() {
//		return new Status(exit_success);
//	}
//	
//	@RequestMapping(value = "/rest/logout", method = RequestMethod.GET, headers = "Accept=application/json")
//	public Status logout() {
//		return new Status(exit_success);
//	}	
}

