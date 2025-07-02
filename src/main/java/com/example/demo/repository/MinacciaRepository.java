package com.example.demo.repository;

import org.springframework.stereotype.Component;

@Component
public class MinacciaRepository {
	
    public MinacciaRepository() {
    	System.out.println("Insulti da parte di MinacciaRepository: istanziato tramite setter injection! ");
	}
	
	public String minaccia() {
		return "Insulti da parte del Repository! ";
	}

}
