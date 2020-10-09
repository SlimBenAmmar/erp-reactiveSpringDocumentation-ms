package com.odix.fr.service;

import com.odix.fr.dto.DtoActivite;
import com.odix.fr.model.Activite;
import com.odix.fr.model.EtatActivite;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActiviteService {

	public Flux<DtoActivite> getAllActivitesByEtatActivite(String uuidCustomer, EtatActivite etatActivite);

	public Flux<DtoActivite> getAllActivitesByEtatActivitePaginated(String uuidCustomer, EtatActivite etatActivite,
			Integer offset, Integer limit, String sort);

	public Mono<Long> getCountActivitesByEtatActivite(String uuidCustomer, EtatActivite etatActivite);

	public Mono<DtoActivite> handleActiviteRequest(DtoActivite dtoActivite, EtatActivite etatActivite);

	public Mono<DtoActivite> getActiviteById(String id);

	public Mono<DtoActivite> addActivite(Activite activite);

	public Mono<DtoActivite> editActivite(DtoActivite dtoActivity);

	public Mono<Void> deleteActivite(String id);
}
