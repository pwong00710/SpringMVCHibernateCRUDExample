package com.tutorialspoint.service.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tutorialspoint.model.country.Country;
import com.tutorialspoint.service.CountryService;
import com.tutorialspoint.webservice.countryservice.AddCountryDetailsRequest;
import com.tutorialspoint.webservice.countryservice.AddCountryDetailsResponse;
import com.tutorialspoint.webservice.countryservice.AllCountryDetailsRequest;
import com.tutorialspoint.webservice.countryservice.AllCountryDetailsResponse;
import com.tutorialspoint.webservice.countryservice.CountryDetailsRequest;
import com.tutorialspoint.webservice.countryservice.CountryDetailsResponse;
import com.tutorialspoint.webservice.countryservice.DeleteCountryDetailsRequest;
import com.tutorialspoint.webservice.countryservice.DeleteCountryDetailsResponse;
import com.tutorialspoint.webservice.countryservice.UpdateCountryDetailsRequest;
import com.tutorialspoint.webservice.countryservice.UpdateCountryDetailsResponse;

@Endpoint
public class CountryServiceEndpoint {
	private static final String TARGET_NAMESPACE = "http://tutorialspoint.com/webservice/countryservice";
	private static final int exit_success = 0; 
	private static final int exit_failure = 1;
	
	@Autowired  
	private CountryService countryService_i;
	
	@PayloadRoot(localPart = "CountryDetailsRequest", namespace = TARGET_NAMESPACE)  
	public @ResponsePayload CountryDetailsResponse getCountryDetails(@RequestPayload CountryDetailsRequest request)  
	{  
		CountryDetailsResponse response = new CountryDetailsResponse();  
		
		Country country = countryService_i.getCountryDetails(request.getId());  
		response.setCountryDetails(country);  
		return response;  
	}  
	
	@PayloadRoot(localPart = "AllCountryDetailsRequest", namespace = TARGET_NAMESPACE)  
	public @ResponsePayload AllCountryDetailsResponse getAllCountryDetails(@RequestPayload AllCountryDetailsRequest request)  
	{  
		AllCountryDetailsResponse response = new AllCountryDetailsResponse();  
		
		List<Country> countryList = countryService_i.getAllCountryDetails();
		
		for (Country country : countryList) {
			response.getCountryDetails().add(country);
		}
		return response;  
	}	
	
	@PayloadRoot(localPart = "AddCountryDetailsRequest", namespace = TARGET_NAMESPACE)  
	public @ResponsePayload AddCountryDetailsResponse addCountryDetails(@RequestPayload AddCountryDetailsRequest request)  
	{  
		AddCountryDetailsResponse response = new AddCountryDetailsResponse();  
		
		Country countryDetails = countryService_i.addCountryDetails(request.getCountryDetails());
		
		response.setCountryDetails(countryDetails);
		
		return response;  
	}	
	
	@PayloadRoot(localPart = "UpdateCountryDetailsRequest", namespace = TARGET_NAMESPACE)  
	public @ResponsePayload UpdateCountryDetailsResponse updateCountryDetails(@RequestPayload UpdateCountryDetailsRequest request)  
	{  
		UpdateCountryDetailsResponse response = new UpdateCountryDetailsResponse();  
		
		countryService_i.updateCountryDetails(request.getCountryDetails());

		response.setStatusCode(exit_success);
		
		return response;  
	}
	
	@PayloadRoot(localPart = "DeleteCountryDetailsRequest", namespace = TARGET_NAMESPACE)  
	public @ResponsePayload DeleteCountryDetailsResponse deleteCountryDetails(@RequestPayload DeleteCountryDetailsRequest request)  
	{  
		DeleteCountryDetailsResponse response = new DeleteCountryDetailsResponse();  
		
		countryService_i.deleteCountryDetails(request.getId());

		response.setStatusCode(exit_success);
		
		return response;  
	}
	
	public void setCountryService(CountryService countryService_p)  
	{  
		this.countryService_i = countryService_p;  
	}
}
