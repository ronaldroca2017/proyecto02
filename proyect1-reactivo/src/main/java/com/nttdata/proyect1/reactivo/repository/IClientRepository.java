package com.nttdata.proyect1.reactivo.repository;

import com.nttdata.proyect1.reactivo.model.Client;
//import io.reactivex.rxjava3.core.Single;
import io.reactivex.Single;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IClientRepository extends ReactiveMongoRepository<Client, String> {

    @Query("{'name' : ?0}")
    Mono<Client> findClientByName(String name);


    @Query("{'name' : ?0, 'dni' : ?1}")
    Single<Client> findClient2(String name, String dni);
}
