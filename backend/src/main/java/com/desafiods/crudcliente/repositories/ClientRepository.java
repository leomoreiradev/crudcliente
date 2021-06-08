package com.desafiods.crudcliente.repositories;

import com.desafiods.crudcliente.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface ClientRepository  extends JpaRepository<Client, Long> {
}
