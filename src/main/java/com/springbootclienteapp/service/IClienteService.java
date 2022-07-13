package com.springbootclienteapp.service;

import com.springbootclienteapp.entity.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> listrtodos();

    public void guardar(Cliente cliente);

    public Cliente buscarPorId(Long id);

    public void elimina(Long id);
}
