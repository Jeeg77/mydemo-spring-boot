package com.example.demo.repository;

import org.springframework.stereotype.Component;

@Component
public class SalutoRepository {
	
	public SalutoRepository() {
		System.out.println("Saluti da parte di SalutoRepository: istanziato nel service tramite constructor injection! ");
	}
	
	public String saluta(String nome) {
		return "Saluti da parte del Repository: " + "Ciao, " + nome + "! Ma cosa è un saluto?";
	}

}
