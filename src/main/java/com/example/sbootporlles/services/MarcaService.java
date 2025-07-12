package com.example.sbootporlles.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sbootporlles.models.MarcaModels;

import com.example.sbootporlles.repositories.MarcaRepository;


@Service
public class MarcaService {
    @Autowired
    MarcaRepository marcaRepository;
    public ArrayList<MarcaModels> listarMarcas(){
        return (ArrayList<MarcaModels>) marcaRepository.findAll();
    }
}
