package test.webservices;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.arpit.java2blog.model.Country;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringRestfulTest {
	
	static final String baseURL = "http://localhost:8080/SpringMVCHibernateCRUDExample";
	static final String username = "priya";
	static final String password = "Aa123456";
//	static String cookie;
//	static List<Cookie> cookies;
//	static CookieStore httpCookieStore;
	static HttpClientBuilder builder;
	static int countryId;
	
	static Logger log = Logger.getLogger(SpringRestfulTest.class.getName());

	@BeforeClass
	public static void beforeClass() throws Exception {
		login();
	}
	
	private static void login() throws Exception {
//		CookieHandler.setDefault(new CookieManager());
		
		CookieStore httpCookieStore = new BasicCookieStore();
		builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);

		HttpClient client = builder.build();
		HttpPost post = new HttpPost(baseURL+"/login");
		post.setHeaders(getDefaultHeader());

		List<NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));

		post.setEntity(new UrlEncodedFormEntity(nvps));

		HttpResponse response = client.execute(post);
		log.info("Response Code : " + response.getStatusLine().getStatusCode());
		Assert.assertEquals(HttpStatus.FOUND.value(), response.getStatusLine().getStatusCode());

		HttpEntity entity = response.getEntity();
		BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			log.info(line);
		}

//		cookie = response.getFirstHeader("Set-Cookie") == null ? "" :
//			response.getFirstHeader("Set-Cookie").toString();
		
		EntityUtils.consume(entity);
		
		log.info("Complete "+getCurrentMethodName());
	}

	private static Header[] getDefaultHeader() {
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded"));
		headers.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-TW,zh;q=0.8,en-US;q=0.6,en;q=0.4,zh-CN;q=0.2"));
		headers.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
		headers.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"));
		headers.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, sdch, br"));
		headers.add(new BasicHeader("Upgrade-Insecure-Requests", "1"));
//		headers.add(new BasicHeader("Cookie", cookie));
		return headers.toArray(new Header[0]);
	}

	@Test
	public void stage1_getAllCountriesTest() throws Exception {		
		HttpClient client = builder.build();
		HttpGet request = new HttpGet(baseURL+"/rest/getAllCountries");
		request.setHeaders(getDefaultJsonHeader());

		HttpResponse response = client.execute(request);
		log.info("Response Code : " + response.getStatusLine().getStatusCode());
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusLine().getStatusCode());
		
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			buffer.append(line);
		}
		log.info(buffer.toString());
		
		log.info("Complete "+getCurrentMethodName());
	}
	
	private static Header[] getDefaultJsonHeader() {
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
		headers.add(new BasicHeader(HttpHeaders.ACCEPT, "application/json"));
		headers.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"));
		headers.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, sdch, br"));
		headers.add(new BasicHeader("Upgrade-Insecure-Requests", "1"));
//		headers.add(new BasicHeader("Cookie", cookie));
		return headers.toArray(new Header[0]);
	}
	
	@Test
	public void stage2_addCountryTest() throws Exception {
		HttpClient client = builder.build();
		HttpPost post = new HttpPost(baseURL+"/rest/addCountry");
		post.setHeaders(getDefaultJsonHeader());

		ObjectMapper mapper = new ObjectMapper();
		Country country = new Country(0, "Test", 1000);		
		String jsonInString = mapper.writeValueAsString(country);

		post.setEntity(new StringEntity(jsonInString));

		HttpResponse response = client.execute(post);
		log.info("Response Code : " + response.getStatusLine().getStatusCode());
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusLine().getStatusCode());

		HttpEntity entity = response.getEntity();
		BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			buffer.append(line);
		}
		log.info(buffer.toString());
		country = mapper.readValue(buffer.toString(), Country.class);
		countryId = country.getId();
		
		log.info("Complete "+getCurrentMethodName());
	}
	
	@Test
	public void stage3_updateCountryTest() throws Exception {
		HttpClient client = builder.build();
		HttpPut put = new HttpPut(baseURL+"/rest/updateCountry");
		put.setHeaders(getDefaultJsonHeader());

		ObjectMapper mapper = new ObjectMapper();
		Country country = new Country(countryId, "Test", 1500);		
		String jsonInString = mapper.writeValueAsString(country);

		put.setEntity(new StringEntity(jsonInString));

		HttpResponse response = client.execute(put);
		log.info("Response Code : " + response.getStatusLine().getStatusCode());
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusLine().getStatusCode());

		HttpEntity entity = response.getEntity();
		BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			buffer.append(line);
		}
		log.info(buffer.toString());
		
		log.info("Complete "+getCurrentMethodName());
	}
	
	@Test
	public void stage4_getCountryTest() throws Exception {
		HttpClient client = builder.build();
		HttpGet request = new HttpGet(baseURL+"/rest/getCountry"+"/"+countryId);
		request.setHeaders(getDefaultJsonHeader());

		HttpResponse response = client.execute(request);
		log.info("Response Code : " + response.getStatusLine().getStatusCode());
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusLine().getStatusCode());
		
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			buffer.append(line);
		}
		log.info(buffer.toString());
		
		log.info("Complete "+getCurrentMethodName());
	}
	
	@Test
	public void stage5_deleteCountryTest() throws Exception {
		HttpClient client = builder.build();
		HttpDelete delete = new HttpDelete(baseURL+"/rest/deleteCountry"+"/"+countryId);
		delete.setHeaders(getDefaultJsonHeader());

		HttpResponse response = client.execute(delete);
		log.info("Response Code : " + response.getStatusLine().getStatusCode());
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusLine().getStatusCode());
		
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			buffer.append(line);
		}
		log.info(buffer.toString());
		
		log.info("Complete "+getCurrentMethodName());
	}
	
	private static String getCurrentMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

}
