package com.springbootclienteapp.repository;

import com.springbootclienteapp.entity.Ciudad;
import org.springframework.data.repository.CrudRepository;

public interface CiudadRepository  extends CrudRepository<Ciudad, Long> {
}
