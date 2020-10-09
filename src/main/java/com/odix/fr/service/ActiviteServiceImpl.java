package com.odix.fr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.odix.fr.dto.DtoActivite;
import com.odix.fr.dto.DtoActiviteMapper;
import com.odix.fr.model.Activite;
import com.odix.fr.model.EtatActivite;
import com.odix.fr.repository.ActiviteRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class ActiviteServiceImpl implements ActiviteService {

	@Autowired
	ActiviteRepository activiteRepository;

	@Autowired
	DtoActiviteMapper dtoActiviteMapper;

	@Override
	public Flux<DtoActivite> getAllActivitesByEtatActivite(String uuidCustomer, EtatActivite etatActivite) {
		return activiteRepository.findByUuidCustomerAndEtatActivite(uuidCustomer, etatActivite, null)
				.map(activite -> dtoActiviteMapper.activiteToDto(activite));
	}

	@Override
	public Flux<DtoActivite> getAllActivitesByEtatActivitePaginated(String uuidCustomer, EtatActivite etatActivite,
			Integer offset, Integer limit, String sort) {
		return activiteRepository
				.findByUuidCustomerAndEtatActivite(uuidCustomer, etatActivite,
						PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, sort)))
				.map(activite -> dtoActiviteMapper.activiteToDto(activite));
	}

	@Override
	public Mono<Long> getCountActivitesByEtatActivite(String uuidCustomer, EtatActivite etatActivite) {
		return activiteRepository.findByUuidCustomerAndEtatActivite(uuidCustomer, etatActivite, null).count();
	}

	@Override
	public Mono<DtoActivite> getActiviteById(String id) {
		return activiteRepository.findById(id).map(activite -> dtoActiviteMapper.activiteToDto(activite));
	}

	@Override
	public Mono<DtoActivite> addActivite(Activite activite) {
		return activiteRepository.insert(activite).map(activiteToAdd -> dtoActiviteMapper.activiteToDto(activiteToAdd));
	}

	@Override
	public Mono<DtoActivite> editActivite(DtoActivite dtoActivite) {
		return activiteRepository.findById(dtoActivite.getId()).flatMap(activite -> {
			return activiteRepository.save(dtoActiviteMapper.DtoToActivite(dtoActivite));
		}).map(activiteEdited -> dtoActiviteMapper.activiteToDto(activiteEdited));
	}

	@Override
	public Mono<DtoActivite> handleActiviteRequest(DtoActivite dtoActivite, EtatActivite etatActivite) {
		dtoActivite.setEtatActivite(etatActivite);
		return activiteRepository.findById(dtoActivite.getId()).flatMap(activite -> {
			return activiteRepository.save(dtoActiviteMapper.DtoToActivite(dtoActivite));
		}).map(activiteEdited -> dtoActiviteMapper.activiteToDto(activiteEdited));
	}

	@Override
	public Mono<Void> deleteActivite(String id) {
		return activiteRepository.deleteById(id);
	}

}
