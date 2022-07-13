package com.springbootclienteapp.service;

import com.springbootclienteapp.entity.Cliente;
import com.springbootclienteapp.repository.ClienteReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    private ClienteReposiory clienteReposiory;


    @Override
    public List<Cliente> listrtodos() {
        return (List<Cliente>) clienteReposiory.findAll();
    }

    @Override
    public void guardar(Cliente cliente) {
        clienteReposiory.save(cliente);

    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteReposiory.findById(id).orElse(null);
    }

    @Override
    public void elimina(Long id) {

        clienteReposiory.deleteById(id);

    }
}
