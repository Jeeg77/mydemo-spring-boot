package com.example.demo.repository;

import org.springframework.stereotype.Component;

@Component
public class CongratulazioneRepository {

	public CongratulazioneRepository() {
		System.out.println("Congratulazioni da parte di CongratulazioneRepository: istanziato tramite field injection! ");
	}

	public String congratulati() {
		return "Congratulazioni da parte del Repository! ";
	}

}
