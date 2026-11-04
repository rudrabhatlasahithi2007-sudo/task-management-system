package com.example.demo.service;

public class TaskNotFoundException
	  extends RuntimeException {

		    public TaskNotFoundException(String message) {
		        super(message);
		    }
}
