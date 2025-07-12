package com.example.sbootporlles.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbootporlles.models.MarcaModels;
import com.example.sbootporlles.services.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaControlles {
    @Autowired
    private MarcaService marcaService;

    @GetMapping ("/GetAll")
    public ArrayList<MarcaModels> listarMarcas() {
        return this.marcaService.listarMarcas();
    }
    
}
