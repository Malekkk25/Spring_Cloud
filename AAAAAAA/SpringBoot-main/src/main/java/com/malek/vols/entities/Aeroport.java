package com.malek.vols.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aeroport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAir;
	private String nomAir;
	private String adresseAir;
	
	@JsonIgnore
	@OneToMany (mappedBy ="aeroport")
	private List<Vol> vols;
	

	
	
}
