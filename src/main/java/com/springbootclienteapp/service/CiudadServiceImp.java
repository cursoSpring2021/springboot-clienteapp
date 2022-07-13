package com.springbootclienteapp.service;

import com.springbootclienteapp.entity.Ciudad;
import com.springbootclienteapp.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CiudadServiceImp implements ICiudadService{
    @Autowired
    private CiudadRepository ciudadRepository;
    @Override
    public List<Ciudad> listaCiudades() {
        return (List<Ciudad>) ciudadRepository.findAll();
    }
}
