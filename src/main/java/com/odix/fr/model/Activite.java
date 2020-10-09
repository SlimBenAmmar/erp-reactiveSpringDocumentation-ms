package com.odix.fr.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Activite {

	@Schema(description = "Unique identifier of the Activity.", example = "5f792e7f32656b19e75b776f", required = true)
	@Id
	private String id;
	@Schema(description = "Universally unique identifier of the Activity.", example = "5f3d10a6cf298e3954814b35", required = true)
	private String uuidCustomer;
	@Schema(description = "Name of the activity.", example = "Banque", required = true)
	private String nomActivite;
	@Schema(description = "Etat of the Activity.", example = "VALIDE", required = true)
	private EtatActivite etatActivite = EtatActivite.VALIDE;
	@Schema(description = "Date of creation of the activity.", example = "2020-10-04T02:07:59.750154", required = true)
	private String dateCreationActivite = LocalDateTime.now().toString();
	@Schema(description = "Date of update of the activity.", example = "2020-11-04T02:08:25.750154", required = true)
	private String dateModificationActivite;;
}