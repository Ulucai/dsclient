package com.devsuperior.DSClient.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.DSClient.dto.ClientDTO;
import com.devsuperior.DSClient.entities.Client;
import com.devsuperior.DSClient.repositories.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly=true)
	public List<ClientDTO> findAll(){
		List<Client> list = repository.findAll(); 
		return list.stream().map(x-> new ClientDTO(x)).toList();
	}
	
	@Transactional(readOnly=true)
	public Page<ClientDTO> findAllPaged(PageRequest pageResquest) {
		Page<Client> list = repository.findAll(pageResquest);
		return list.map(x->new ClientDTO(x));
	}
	
	@Transactional(readOnly=true)
	public ClientDTO findById(Long id) {
		Optional<Client> result = repository.findById(id); 
		return new ClientDTO(result.get());
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto)
	{
		Client entity = new Client();
		copyDtoToEntity(entity, dto);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto)
	{
		Client entity = repository.getOne(id);
		copyDtoToEntity(entity, dto);		
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}
	
	private void copyDtoToEntity(Client entity, ClientDTO dto) {
		entity.setName(dto.getName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
	}
}
