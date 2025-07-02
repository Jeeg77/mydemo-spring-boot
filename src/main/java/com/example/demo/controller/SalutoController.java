package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.ReazioneService;

@PreAuthorize("hasAnyRole('ADMIN', 'EDIT', 'VIEW')")
@RestController
@RequestMapping("/saluto")
public class SalutoController {

    private final ReazioneService reazioneService;

    // Dependency Injection tramite costruttore (se c'è un solo costruttore allora @Autowired è opzionale).
    public SalutoController(ReazioneService reazioneService) {
        this.reazioneService = reazioneService;
    }

    @GetMapping("/{nome}")
    public String saluta(@PathVariable String nome) {
        return reazioneService.saluta(nome);
    }
}
