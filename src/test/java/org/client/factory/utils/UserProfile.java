package org.client.factory.utils;

import com.github.javafaker.Faker;

public class UserProfile extends CommonUtils {
	static Faker random;

	public UserProfile() {
		random = new Faker();

	}

}
