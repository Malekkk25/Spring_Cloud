package com.malek.vols.dto;

import com.malek.vols.entities.Aeroport;
import com.malek.vols.entities.Image;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public class VolDTO {
	private Long idVol;
	private String numVol,agence,destination,etat;
	private Date HeureDepart;
	private Aeroport aeroport;
	private String imagePath;
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	private List<Image>images;
	
	
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public VolDTO() {
		super();
	}

	public VolDTO(Long idVol, String numVol, String agence, String destination, String etat, Date heureDepart, Aeroport aeroport, String imagePath, List<Image> images) {
		this.idVol = idVol;
		this.numVol = numVol;
		this.agence = agence;
		this.destination = destination;
		this.etat = etat;
		this.HeureDepart = heureDepart;
		this.aeroport = aeroport;
		this.imagePath = imagePath;
		this.images = images;
	}
	public Long getIdVol() {
		return idVol;
	}
	public void setIdVol(Long idVol) {
		this.idVol = idVol;
	}
	public String getNumVol() {
		return numVol;
	}
	public void setNumVol(String numVol) {
		this.numVol = numVol;
	}
	public String getAgence() {
		return agence;
	}
	public void setAgence(String agence) {
		this.agence = agence;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Date getHeureDepart() {
		return HeureDepart;
	}
	public void setHeureDepart(Date heureDepart) {
		HeureDepart = heureDepart;
	}
	public Aeroport getAeroport() {
		return aeroport;
	}
	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}

	
}
