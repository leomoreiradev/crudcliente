package com.desafiods.crudcliente.services;

import com.desafiods.crudcliente.dto.ClientDTO;
import com.desafiods.crudcliente.entity.Client;
import com.desafiods.crudcliente.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> list = clientRepository.findAll(pageRequest);
        return list.map( client -> new ClientDTO(client));
    }
}
