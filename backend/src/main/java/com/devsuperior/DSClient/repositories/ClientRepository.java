package com.devsuperior.DSClient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.DSClient.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
