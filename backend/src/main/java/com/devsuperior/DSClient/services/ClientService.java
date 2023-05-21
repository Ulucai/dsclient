package com.devsuperior.DSClient.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devsuperior.DSClient.dto.ClientDTO;
import com.devsuperior.DSClient.entities.Client;
import com.devsuperior.DSClient.repositories.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repository;
	
	public List<ClientDTO> findAll(){
		List<Client> list = repository.findAll(); 
		return list.stream().map(x-> new ClientDTO(x)).toList();
	}

	public Page<ClientDTO> findAllPaged(PageRequest pageResquest) {
		Page<Client> list = repository.findAll(pageResquest);
		return list.map(x->new ClientDTO(x));
	}
}
