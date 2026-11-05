package com.example.demo.repository;

import org.springframework.stereotype.Component;

@Component
public class MinacciaRepository {
	
    public MinacciaRepository() {
    	System.out.println("Minacce da parte di MinacciaRepository: istanziato nel service tramite setter injection! ");
	}
	
	public String minaccia() {
		return "Minacce da parte del Repository! ";
	}

}
