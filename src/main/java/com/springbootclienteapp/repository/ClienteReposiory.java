package com.springbootclienteapp.repository;

import com.springbootclienteapp.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteReposiory extends CrudRepository<Cliente,Long> {
}
