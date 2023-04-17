package org.client.api.test;

import static org.client.factory.core.AllureManager.step;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.client.api.config.ContentType;
import org.client.api.core.PostAdapter;
import org.client.api.core.RestAdapter;
import org.client.api.model.CreateUser;
import org.client.api.model.CreateUserResponse;
import org.client.api.utils.EndPoint;
import org.client.ui.utils.TestGroup;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

@Story("Create  User ")
@Feature("Create the Users through API")
public class DummyApiTest {
	private static final Logger Log = Logger.getLogger(DummyApiTest.class);

	private CreateUserResponse response;
	private CreateUser user;

	@BeforeTest(groups = { TestGroup.API }, description = "Initializing the Test")
	public void init() throws IOException {
		response = new CreateUserResponse();
		user = new CreateUser();

	}

	@TmsLink("Demo-001")
	@Parameters({ "name", "job" })
	@Description("User can be created by POST Call ")
	@Test(groups = { TestGroup.API }, description = "Create User by POST call Test")
	public void createUser(String name, String job) {

		user.setName(name);
		user.setJob(job);

		RestAdapter request = PostAdapter.builder().setContentType(ContentType.JSON).setRequestObject(user)
				.setBaseURL(EndPoint.BASEURL.getAttribute()).setEndPoint(EndPoint.Create.getAttribute()).build();
		Log.info("Creating request" + request);
		response = request.execute().as(CreateUserResponse.class);
		Log.info("executing request " + response);
	}

	@TmsLink("Demo-002")
	@Parameters({ "name", "job" })
	@Description("Verify the Created User by Post call ")
	@Test(groups = { TestGroup.API }, description = "Verify the Created User Test")
	public void verifyCreatedUser(String name, String job) {

		step("Verifying the name and Job title from the Response ");
		Assert.assertTrue(response.getName().equalsIgnoreCase(name) && response.getJob().equalsIgnoreCase(job));

	}

}
