package com.odix.fr.dto;

import com.odix.fr.model.EtatActivite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoActivite {

	private String id;
	private String uuidCustomer;
	private String nomActivite;
	private EtatActivite etatActivite;
}
