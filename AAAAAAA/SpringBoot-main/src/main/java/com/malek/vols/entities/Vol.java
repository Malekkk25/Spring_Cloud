package com.malek.vols.entities;
import java.util.Date;
import java.util.List;



import javax.persistence.*;

@Entity
public class Vol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVol;
	private String numVol,agence,destination,etat;
	private Date HeureDepart;
	private String imagePath;
	@ManyToOne
	private Aeroport aeroport;


	/*@OneToOne
	private Image image;*/

	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	@OneToMany(mappedBy = "vol", cascade = CascadeType.ALL)
	private List<Image> images;

	public Vol() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Vol [idVol=" + idVol + ", numVol=" + numVol + ", agence=" + agence + ", destination=" + destination
				+ ", etat=" + etat + ", HeureDepart=" + HeureDepart + "]";
	}


	public Vol(String numVol, String agence, String destination, String etat,  Date heureDepart) {
		super();
		this.numVol = numVol;
		this.agence = agence;
		this.destination = destination;
		this.etat = etat;
		HeureDepart = heureDepart;
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


	public List<Image> getImages() {
		return images;
	}


	public void setImages(List<Image> images) {
		this.images = images;
	}


	/*public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}*/


}
