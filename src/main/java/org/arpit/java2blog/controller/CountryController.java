package org.arpit.java2blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@RequestMapping(value="index")
	public String index(/*@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter,*/
			HttpServletRequest request, 
			HttpServletResponse response) {
		
//		// increment hit counter
//		hitCounter++;
//
//		// create cookie and set it in response
//		// refer by ${cookie.hitCounter.value} in jsp
//		Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
//		response.addCookie(cookie);
		
		return "index";
	}
	
	@RequestMapping(value = "/getAllCountries", method = RequestMethod.GET, headers = "Accept=application/json")	
	public String getCountries(Model model,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		List<Country> listOfCountries = countryService.getAllCountries();
		model.addAttribute("country", new Country());
		model.addAttribute("listOfCountries", listOfCountries);
		model.addAttribute("username", getUserName());
		return "countryDetails";
	}

	@RequestMapping(value = "/getCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Country getCountryById(@PathVariable int id, 
			HttpServletRequest request, 
			HttpServletResponse response) {
		return countryService.getCountry(id);
	}

	@RequestMapping(value = "/addCountry", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addCountry(@ModelAttribute("country") Country country, 
			HttpServletRequest request, 
			HttpServletResponse response) {	
		if(country.getId()==0)
		{
			countryService.addCountry(country);
		}
		else
		{	
			countryService.updateCountry(country);
		}
		
		return "redirect:/getAllCountries";
	}

	@RequestMapping(value = "/updateCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String updateCountry(@PathVariable("id") int id, 
			Model model, 
			HttpServletRequest request, 
			HttpServletResponse response) {
		model.addAttribute("country", this.countryService.getCountry(id));
		model.addAttribute("listOfCountries", this.countryService.getAllCountries());
		model.addAttribute("username", getUserName());
		return "countryDetails";
	}

	@RequestMapping(value = "/deleteCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteCountry(@PathVariable("id") int id, 
			HttpServletRequest request, 
			HttpServletResponse response) {
		countryService.deleteCountry(id);
		 return "redirect:/getAllCountries";

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;
	}
	
	//Logout mapping
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(){
	    return "index";
	}
	
	private String getUserName() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String name = auth.getName(); //get logged in username
    	
    	return name;
	}
}
