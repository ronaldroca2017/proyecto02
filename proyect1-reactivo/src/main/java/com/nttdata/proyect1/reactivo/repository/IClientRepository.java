package com.nttdata.proyect1.reactivo.repository;

import com.nttdata.proyect1.reactivo.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface IClientRepository extends ReactiveMongoRepository<Client, String> {


}
