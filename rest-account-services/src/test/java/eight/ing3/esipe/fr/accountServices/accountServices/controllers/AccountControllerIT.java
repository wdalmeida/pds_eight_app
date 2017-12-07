package eight.ing3.esipe.fr.accountServices.accountServices.controllers;

import eight.ing3.esipe.fr.accountServices.RestAccountServicesApplication;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestAccountServicesApplication.class)
@ActiveProfiles("test")
public class AccountControllerIT {
	
	TestRestTemplate restTemplate = new TestRestTemplate();
/*	@Autowired
	private TestRestTemplate restTemplate;
	*/
	
	
	@Test
	public void test() throws JSONException{
		
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZmlyc3ROYW1lIjoiYW50aG9ueSIsImxhc3ROYW1lIjoicGVyYXVsdCIsImlhdCI6MTUxMjYwMTE1M30._CE4PvFBpQ76V3V3ybyCTA6cZOMLqx4bHteM23v8YUAGzYCORti_NBS74i6weQrlPPfM6Ehc8f2IjZ-WePYkpw");
		
		//HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8181/accounts", HttpMethod.GET,new HttpEntity<>(headers),String.class);
		System.out.println(response);
		
		JSONAssert.assertEquals("dummy", response.getBody(), false);
		
		
		
	}

}
