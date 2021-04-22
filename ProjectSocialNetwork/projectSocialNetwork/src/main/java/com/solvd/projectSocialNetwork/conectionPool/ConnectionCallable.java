package com.solvd.projectSocialNetwork.conectionPool;

import java.util.concurrent.Callable;

public class ConnectionCallable implements Callable<String> {

	private String someString;
	
	public ConnectionCallable(String someString) {
		super();
		this.someString = someString;
	}

	@Override
	public String call() throws Exception {
		return someString;
	}

}
