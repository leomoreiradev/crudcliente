package com.desafiods.crudcliente.services;

import com.desafiods.crudcliente.dto.ClientDTO;
import com.desafiods.crudcliente.entity.Client;
import com.desafiods.crudcliente.repositories.ClientRepository;
import com.desafiods.crudcliente.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> list = clientRepository.findAll(pageRequest);
        return list.map( entity -> new ClientDTO(entity));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        Client entity = client.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
        return  new ClientDTO(entity);
    }


    @Transactional
    public ClientDTO insert(ClientDTO clientDTO) {
        Client entity = new Client();
        entity.setName(clientDTO.getName());
        entity.setBirthDate(clientDTO.getBirthDate());
        entity.setChildren(clientDTO.getChildren());
        entity.setCpf(clientDTO.getCpf());
        entity.setIncome(clientDTO.getIncome());
        entity = clientRepository.save(entity);

        return new ClientDTO(entity);

    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        try {
            Client entity = clientRepository.getOne(id);
            entity.setName(clientDTO.getName());
            entity.setBirthDate(clientDTO.getBirthDate());
            entity.setChildren(clientDTO.getChildren());
            entity.setCpf(clientDTO.getCpf());
            entity.setIncome(clientDTO.getIncome());
            entity = clientRepository.save(entity);

            return new ClientDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw  new ResourceNotFoundException("Id not found " + id);

        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            clientRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);

        }

    }
}
