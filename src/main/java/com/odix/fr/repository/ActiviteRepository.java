package com.odix.fr.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.odix.fr.model.Activite;
import com.odix.fr.model.EtatActivite;

import reactor.core.publisher.Flux;

public interface ActiviteRepository extends ReactiveMongoRepository<Activite, String> {

	public Flux<Activite> findByUuidCustomerAndEtatActivite(String uuidCustomer, EtatActivite etatActivite,
			Pageable pageable);
}
