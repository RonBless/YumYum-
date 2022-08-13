package iob.AmitYehezkel;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import iob.boundary.InstanceBoundary;
import iob.boundary.NewUserBoundary;
import iob.boundary.UserBoundary;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
	private int port;
	private RestTemplate restTemplate;
	private String userEmail = "ron@test.com";
	private String userDomain = "2022B.Amit.Yehezkel";
	private String url;

	
	@LocalServerPort
	public void setPort(int port) {
		this.port = port;
	}
	
	@PostConstruct
	public void testsInit() {
		this.restTemplate = new RestTemplate();
	}
	
//	@BeforeEach
//	@AfterEach
//	public void tearDown() {
//		System.err.println("**** tearDown()");
//		this.restTemplate
//			.delete(this.url);
//	}
//	
	@Test
	public void testContext() {
	}
	
	@Test
	public void testPostUserMethod() throws Exception {
		// GIVEN the server is up
		String userName = "ron";
		String userAvatar = "Avatares";
		String userRole = "PLAYER";
		
		NewUserBoundary input = new NewUserBoundary(userEmail, userRole, userName, userAvatar);

		
		this.url = "http://localhost:" + this.port + "/iob/users";
				
		// HTTP GET 
		UserBoundary actual = this.restTemplate.postForObject(this.url, input, UserBoundary.class);
				
		
		assertThat(actual.getUsername().equals(userName));
		assertThat(actual.getAvatar().equals(userAvatar));
		assertThat(actual.getRole().equals(userRole));
		assertThat(actual.getUserId().getDomain().equals(userDomain));
		assertThat(actual.getUserId().getEmail().equals(userEmail));

	}
	
	@Test
	public void testGetUserMethod() throws Exception {
		// GIVEN the server is up
		//  AND the server contains a message with a known id
		this.url = "http://localhost:" + this.port + "/iob/users/login/" + userDomain + "/" + userEmail;

		String userName = "ron";
		String userAvatar = "Avatares";
		String userRole = "PLAYER";
		
		// HTTP GET 
		UserBoundary actual = this.restTemplate.getForObject(this.url, UserBoundary.class);
				
		assertThat(actual.getUsername().equals(userName));
		assertThat(actual.getAvatar().equals(userAvatar));
		assertThat(actual.getRole().equals(userRole));
		assertThat(actual.getUserId().getDomain().equals(userDomain));
		assertThat(actual.getUserId().getEmail().equals(userEmail));

}

	@Test
	public void testUpdateUserMethod() throws Exception {
		// GIVEN the server is up
		//First we will get the current user
		//HTTP GET
		this.url = "http://localhost:" + this.port + "/iob/users/login/" + userDomain + "/" + userEmail;
		
		UserBoundary before = this.restTemplate.getForObject(this.url, UserBoundary.class);

		//the values we will update
		String updatedAvatar = "changed Avatar";
		String updatedUsername = "changed Username";
		before.setAvatar(updatedAvatar);
		before.setUsername(updatedUsername);
			
		// HTTP PUT 
		this.url = "http://localhost:" + this.port + "/iob/users/"  + userDomain + "/" + userEmail;
		this.restTemplate.put(this.url, before);
		this.url = "http://localhost:" + this.port + "/iob/users/login/" + userDomain + "/" + userEmail;
		UserBoundary updated = this.restTemplate.getForObject(this.url, UserBoundary.class);
		
		assertThat(updated.getUsername().equals(updatedUsername));
		assertThat(updated.getAvatar().equals(updatedAvatar));
		assertThat(updated.getRole().equals(before.getRole()));
		assertThat(updated.getUserId().getDomain().equals(before.getUserId().getDomain()));
		assertThat(updated.getUserId().getEmail().equals(before.getUserId().getEmail()));
	}
	
	@Test
	public void testSearchSpceficInstance() throws Exception {
		// GIVEN the server is up
		//  AND the server contains a message with a known id
		String expectedInstanceId = "150b5577-f415-4171-9057-9aa91b2f352a";
		String expectedName = "thegarage";
		String expectedType = "resturaunt";
		
		
		this.url = "http://localhost:" + this.port + "/iob/instances/" + userDomain + "/" + expectedInstanceId + "?userDomain=" + userDomain + "&userEmail=" + userEmail;
		// HTTP GET
		InstanceBoundary actual = this.restTemplate.getForObject(this.url, InstanceBoundary.class);
		
				
		assertThat(actual.getInstanceId().getId().equals(expectedInstanceId));
		assertThat(actual.getInstanceId().getDomain().equals(userDomain));
		assertThat(actual.getName().equals(expectedName));
		assertThat(actual.getType().equals(expectedType));

	}
	
	@Test
	public void testInstanceUpdate() throws Exception {
		// GIVEN the server is up
		//  AND the server contains a message with a known id
		String expectedInstanceId = "09d76e1b-a5ec-4e99-a6e5-f9a159ea5a7d";
		this.url = "http://localhost:" + this.port + "/iob/instances/" + userDomain + "/" + expectedInstanceId + "?userDomain=" + userDomain + "&userEmail=" + userEmail;
		
		// HTTP GET
		InstanceBoundary beforeUpdate = this.restTemplate.getForObject(this.url, InstanceBoundary.class);
		
		// HTTP PUT 
		this.url = "http://localhost:" + this.port + "/iob/instances/"  + userDomain + "/" + expectedInstanceId;
		try {
			this.restTemplate.put(this.url, beforeUpdate);
		}
		catch(HttpClientErrorException.NotFound e) {
			//No User -> User not found Exception
			assertThat(true);
		}
		
		// HTTP PUT 
		this.url = "http://localhost:" + this.port + "/iob/instances/" + userDomain + "/" + expectedInstanceId + "?userDomain=" + userDomain + "&userEmail=" + userEmail;
		try {
			this.restTemplate.put(this.url, beforeUpdate);
		}
		catch(HttpClientErrorException.Forbidden e) {
			//User is not a manager-> User is not valid for updating instances 
			assertThat(true);
		}

	}
}






