package org.client.Factory.utils;

import com.github.javafaker.Faker;

public class UserProfile extends FactoryUtils {
	static Faker random;

	public UserProfile() {
		random = new Faker();

	}

}
