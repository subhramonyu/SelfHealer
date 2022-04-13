package org.client.api.model;

public class CreateUser {
	private String name ;
	private String job;
	
	
	public CreateUser(){
	}

	public String getName() {
		return name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setName(String name) {
		this.name = name;
	}


}
