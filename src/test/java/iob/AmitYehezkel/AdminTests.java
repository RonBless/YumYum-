package iob.AmitYehezkel;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminTests 
{
	/*
	private int port;
	private String url;
	private RestTemplate restTemplate;


	@LocalServerPort
	public void setPort(int port) {
		this.port = port;
	}

	@PostConstruct
	public void testsInit() {
		this.url = "http://localhost:" + this.port + "/hello"; //??
		this.restTemplate = new RestTemplate();
	}

	//	@BeforeEach
	@AfterEach
	public void tearDown() {
		System.err.println("**** tearDown()");
		this.restTemplate.delete(this.url);
	}

	@Test
	public void testContext() {
	}

	@Test
	public void testDeleteAllUseres() throws Exception {
		// GIVEN the server is up
		// AND the server contains 3 users
		for (int i = 0; i < 3; i++) {
			this.restTemplate
			.postForObject(
					this.url, 
					new NewUserBoundary("admin"+ i+"@gmail.com","MANAGER","admin"+i, "image"+i),
					NewUserBoundary.class);
		}

		// WHEN I DELETE /hello
		this.restTemplate
		.delete(this.url);

		// THEN the server contains no users
		NewUserBoundary[] useresOnServer = this.restTemplate
				.getForObject(
						this.url, 
						NewUserBoundary[].class);

		assertThat(useresOnServer)
		.isEmpty();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDeleteAllInstances() throws Exception {
		// GIVEN the server is up
		// AND the server contains 3 instances
		for (int i = 0; i < 3; i++) {
			Map<String, Object> instanceAttributes = new HashMap<String, Object>();
			this.restTemplate
			.postForObject(
					this.url, 
					new InstanceBoundary(new InstanceIDBoundary("domain"+i,i+""),"dummyType","instance"+i,
							true, new Date(2022,5,3), new UserIDWrapperBoundary(new UserIDBoundary("domain"+i, "instance"+ i+"@gmail.com")),
							new LocationBoundary(4.4, 4.4),instanceAttributes),
					InstanceBoundary.class);
		}

		// WHEN I DELETE /hello
		this.restTemplate
		.delete(this.url);

		// THEN the server contains no instances
		InstanceBoundary[] instancesOnServer = this.restTemplate
				.getForObject(
						this.url, 
						InstanceBoundary[].class);

		assertThat(instancesOnServer)
		.isEmpty();
	}

	@Test
	public void testDeleteAllActivities() throws Exception {
		// GIVEN the server is up
		// AND the server contains 3 Activities
		for (int i = 0; i < 3; i++) {
			Map<String, Object> instanceAttributes = new HashMap<String, Object>();
			this.restTemplate
			.postForObject(
					this.url, 
					new ActivityBoundary(new ActivityIDBoundary("domain"+i, i+""), "demoActivityType", 
							new InstanceIDWrapperBoundary(new InstanceIDBoundary("domain"+i,i+"")),
							new Date(2022,5,3), new UserIDWrapperBoundary(new UserIDBoundary("domain"+i, "instance"+ i+"@gmail.com")),
							instanceAttributes),
					InstanceBoundary.class);
		}

		// WHEN I DELETE /hello
		this.restTemplate
		.delete(this.url);

		// THEN the server contains no Activities
		ActivityBoundary[] ActivitiesOnServer = this.restTemplate
				.getForObject(
						this.url, 
						ActivityBoundary[].class);

		assertThat(ActivitiesOnServer)
		.isEmpty();
	}

	@Test
	@DisplayName("Test GET /hello responds with all users stored in the database")
	public void testGetAllUsers() throws Exception{
		// GIVEN the server is up 
		//   AND the server contains 100 messages in the database
		List<NewUserBoundary> allUsers = 
		  IntStream.range(0, 100) // Stream<Integer>
			.mapToObj(value->{
				NewUserBoundary newUser = new NewUserBoundary("admin"+value +"@gmail.com","MANAGER",
						"admin"+value, "image"+value);
				return newUser;
			}) // Stream <NewUserBoundary>
			.map(usr->this.restTemplate
				.postForObject(this.url, usr, NewUserBoundary.class)) // Stream<NewUserBoundary>
			.collect(Collectors.toList());
		
		
		// WHEN I GET /hello 
		NewUserBoundary[] actualResult = this.restTemplate
			.getForObject(this.url +"/iob/admin/users", NewUserBoundary[].class,
				100, 0);
		
		// THEN the server returns 100 users
		//   AND the response status is 2xx
		assertThat(actualResult)
			.hasSize(100);
	}
	
	@Test
	@DisplayName("Test GET /hello responds with all activities stored in the database")
	public void testGetAllActivities() throws Exception{
		// GIVEN the server is up 
		//   AND the server contains 100 messages in the database
		Map<String, Object> instanceAttributes = new HashMap<String, Object>();
		List<ActivityBoundary> allActivities = 
		  IntStream.range(0, 100) // Stream<Integer>
			.mapToObj(value->{
				ActivityBoundary newactivity = new ActivityBoundary(new ActivityIDBoundary("domain"+value, value+""),"demoActivityType", 
						new InstanceIDWrapperBoundary(new InstanceIDBoundary("domain"+value,value+"")),
						new Date(2022,5,3), new UserIDWrapperBoundary(new UserIDBoundary("domain"+value, "instance"+ value+"@gmail.com")),
						instanceAttributes);
				return newactivity;
			}) // Stream <ActivityBoundary>
			.map(act->this.restTemplate
				.postForObject(this.url, act, ActivityBoundary.class)) // Stream<ActivityBoundary>
			.collect(Collectors.toList());
		
		
		// WHEN I GET /hello 
		ActivityBoundary[] actualResult = this.restTemplate
			.getForObject(this.url +"/iob/admin/activities", ActivityBoundary[].class,
				100, 0);
		
		// THEN the server returns 100 users
		//   AND the response status is 2xx
		assertThat(actualResult)
			.hasSize(100);
	}
	*/
}