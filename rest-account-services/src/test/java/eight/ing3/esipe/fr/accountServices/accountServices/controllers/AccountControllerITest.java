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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestAccountServicesApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class AccountControllerITest {
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	
	
	@Test
	public void ShouldReturnExpectedAccountList_withThisToken() throws JSONException{
		
		HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjM0NTYiLCJmaXJzdE5hbWUiOiJwZXJhdWx0IiwibGFzdE5hbWUiOiJhbnRob255IiwiaWF0IjoxNTEyNjU5MjM2fQ.abjq-jI9ar2tvfrJUjPLb2laJywQ2vLXrKBFzvvaHsP8KhDeCF7Kx8Mw17k-FkQmlQha6R83pg5riO_6GJOjPA");
		
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8181/accounts", HttpMethod.GET,new HttpEntity<>(headers),String.class);
		System.out.println(response);

		String expectedJson = "[\n" +
				"    {\n" +
				"        \"accountNumber\": \"123456U\",\n" +
				"        \"type\": \"CCB\",\n" +
				"        \"balance\": 200\n" +
				"    },\n" +
				"    {\n" +
				"        \"accountNumber\": \"123456D\",\n" +
				"        \"type\": \"PEL\",\n" +
				"        \"balance\": 100\n" +
				"    }\n" +
				"]";

		JSONAssert.assertEquals(expectedJson, response.getBody(), false);
		
		
		
	}

}
