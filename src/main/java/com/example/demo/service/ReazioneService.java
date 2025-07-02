package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.CongratulazioneRepository;
import com.example.demo.repository.MinacciaRepository;
import com.example.demo.repository.SalutoRepository;

@Service
public class ReazioneService {
	
	private final SalutoRepository salutoRepository;
	private MinacciaRepository minacciaRepository;
	
	// FIELD INJECTION (di CongratulazioniRepository)
	@Autowired
	private CongratulazioneRepository congratulazioneRepository;
	
	// CONSTRUCTOR INJECTION (di SalutoService, @Autowired opzionale con un solo costruttore).
	public ReazioneService(SalutoRepository salutoRepository) {
		System.out.println("Reazione di ReazioneService: non sono ancora state ricevute ne minacce ne congratulazioni, soltanto saluti! ");
		this.salutoRepository = salutoRepository;
	}
	
	// SETTER INJECTION (di MinacciaRepository)
	@Autowired
	public void setMinacciaRepository(MinacciaRepository minacciaRepository) {
		System.out.println("Saluti, insulti e congratulazioni da parte di ReazioneService: minacciaRepository e congratulazioneRepository sono stati finalmente iniettati! ");
		this.minacciaRepository = minacciaRepository;
	}
	
	// Flusso Controller -> Service -> Repository
    public String saluta(String nome) {
    	return salutoRepository.saluta(nome);
    }
    
    // Aggiunti solo per eliminare il warning...
    public String minaccia() {
    	return minacciaRepository.minaccia();
    }
    
    public String congratulati() {
    	return congratulazioneRepository.congratulati();
    }
}
