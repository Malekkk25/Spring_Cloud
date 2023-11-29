package com.malek.vols.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;


import javax.persistence.*;


@Entity
@Builder
@Data
public class Image {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long idImage ;
 private String name ;
 private String type ;
 @Column( name = "IMAGE" , length = 4048576 )
 @Lob
 private byte[] image; 
 @ManyToOne()
 @JoinColumn (name="VOL_ID")
 @JsonIgnore
 private Vol vol;
public Image(Long idImage, String name, String type, byte[] image, Vol vol) {
	super();
	this.idImage = idImage;
	this.name = name;
	this.type = type;
	this.image = image;
	this.vol = vol;
}
public Image() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getIdImage() {
	return idImage;
}
public void setIdImage(Long idImage) {
	this.idImage = idImage;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public byte[] getImage() {
	return image;
}
public void setImage(byte[] image) {
	this.image = image;
}
public Vol getVol() {
	return vol;
}
public void setVol(Vol vol) {
	this.vol = vol;
}
public static ImageBuilder builder() {
    return new ImageBuilder();
}

public static class ImageBuilder {
    private Long idImage;
    private String name;
    private String type;
    private byte[] image;
    private Vol vol;

    private ImageBuilder() {
    }

    public ImageBuilder idImage(Long idImage) {
        this.idImage = idImage;
        return this;
    }

    public ImageBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ImageBuilder type(String type) {
        this.type = type;
        return this;
    }

    public ImageBuilder image(byte[] image) {
        this.image = image;
        return this;
    }

    public ImageBuilder vol(Vol vol) {
        this.vol = vol;
        return this;
    }

    public Image build() {
        return new Image(idImage, name, type, image, vol);
    }
}
 
}
