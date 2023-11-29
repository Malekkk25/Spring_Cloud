package com.malek.vols.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.malek.vols.entities.Aeroport;
import com.malek.vols.entities.Vol;

@RepositoryRestResource(path = "air")
@CrossOrigin(origins="http://localhost:4200/") 
public interface AeroportRepository extends JpaRepository<Aeroport, Long> {
	
	

}
