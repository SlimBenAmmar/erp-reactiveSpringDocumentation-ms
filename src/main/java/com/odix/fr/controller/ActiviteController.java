package com.odix.fr.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.odix.fr.dto.DtoActivite;
import com.odix.fr.model.Activite;
import com.odix.fr.model.EtatActivite;
import com.odix.fr.service.ActiviteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/api/finance/activites")

@Tag(name = "Activité api", description = "Endpoints for CRUD operations on activities	")
public class ActiviteController {

	private final static Logger logger = Logger.getLogger(ActiviteController.class);

	@Autowired
	private ActiviteService activiteService;

	@Operation(
			summary = "Get activities by etat",
	          description = "Use this endpoint to get company's activities by etat ",
	          security = @SecurityRequirement(name = "JWT token", scopes = "write"),
	          responses= {
	              @ApiResponse(responseCode = "200", description = "Success"),
	              @ApiResponse(responseCode = "403", description = "Forbidden"),
	              @ApiResponse(responseCode = "500", description = "Server Error")
	          }
	     )
	@GetMapping("/{uuidCustomer}/{etatActivite}")
	public Flux<DtoActivite> getAllActivites(@PathVariable String uuidCustomer,
			@PathVariable EtatActivite etatActivite) {
		try {
			return activiteService.getAllActivitesByEtatActivite(uuidCustomer, etatActivite);
		} catch (Exception exception) {
			logger.error(exception);
		}
		return null;
	}

	@GetMapping("/paginated/{uuidCustomer}/{etatActivite}/{offset}/{limit}")
	public Flux<DtoActivite> getAllActivitesByEtatActivitePaginated(@PathVariable String uuidCustomer,
			@PathVariable EtatActivite etatActivite, @PathVariable Integer offset, @PathVariable Integer limit,
			@RequestParam(required = false, defaultValue = "nomActivite") String sort) {
		try {
			return activiteService.getAllActivitesByEtatActivitePaginated(uuidCustomer, etatActivite, offset, limit,
					sort);
		} catch (Exception exception) {
			logger.error(exception);
		}
		return null;
	}

	@GetMapping("/{id}")
	public Mono<DtoActivite> getActiviteById(@PathVariable String id) {
		try {
			return activiteService.getActiviteById(id);
		} catch (Exception exception) {
			logger.error(exception);
		}
		return null;
	}

	@GetMapping("/count/{uuidCustomer}/{etatActivite}")
	public Mono<Long> getCountActivitesByEtatActivite(@PathVariable String uuidCustomer,
			@PathVariable EtatActivite etatActivite) {
		try {
			return activiteService.getCountActivitesByEtatActivite(uuidCustomer, etatActivite);
		} catch (Exception exception) {
			logger.error(exception);
		}
		return null;
	}

	@PostMapping()
	public Mono<DtoActivite> addActivite(@RequestBody Activite activite) {
		try {
			return activiteService.addActivite(activite);
		} catch (Exception exception) {
			logger.error(exception);
		}
		return null;
	}

	@PutMapping()
	public Mono<DtoActivite> editActivite(@RequestBody DtoActivite dtoActivite) {
		try {
			return activiteService.editActivite(dtoActivite);
		} catch (Exception exception) {
			logger.error(exception);
		}
		return null;
	}

	@PutMapping("/demand/{etatActivite}")
	public Mono<DtoActivite> handleServiceRequest(@RequestBody DtoActivite dtoActivite,
			@PathVariable EtatActivite etatActivite) {
		try {
			return activiteService.handleActiviteRequest(dtoActivite, etatActivite);
		} catch (Exception exception) {
			logger.error(exception);
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteActivity(@PathVariable String id) {
		try {
			return activiteService.deleteActivite(id);
		} catch (Exception exception) {
			logger.error(exception);
		}
		return null;
	}
}
