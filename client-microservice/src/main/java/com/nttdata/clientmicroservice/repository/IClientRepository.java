package com.nttdata.clientmicroservice.repository;


import com.openapi.gen.springboot.dto.ClientDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends ReactiveMongoRepository<ClientDTO, String> {


}
