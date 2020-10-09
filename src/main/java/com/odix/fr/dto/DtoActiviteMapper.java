package com.odix.fr.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.odix.fr.model.Activite;

@Component
public class DtoActiviteMapper {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
	
	@Autowired
	private ModelMapper modelMapper;

	public DtoActivite activiteToDto(Activite activite) {

		DtoActivite map = modelMapper.map(activite, DtoActivite.class);
		return map;

	}

	public Activite DtoToActivite(DtoActivite dtoActivite) {

		Activite map = modelMapper.map(dtoActivite, Activite.class);
		return map;
	}
}
