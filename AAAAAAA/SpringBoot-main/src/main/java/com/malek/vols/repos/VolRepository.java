package com.malek.vols.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.malek.vols.entities.Aeroport;
import com.malek.vols.entities.Vol;

@RepositoryRestResource(path="rest")
@CrossOrigin(origins="http://localhost:4200/")
public interface VolRepository extends JpaRepository<Vol, Long> {

	List<Vol> findByNumVol(String num);
	List<Vol> findByNumVolContains(String num);


	@Query("select v from Vol v where v.numVol like %:num and v.destination like %:des")
	List<Vol> findByNomDes (@Param("num") String num,@Param("des") String des);

	@Query("select v from Vol v where v.aeroport = ?1")
	List<Vol> findByAeroport (Aeroport  aeroport);

	List<Vol> findByAeroportIdAir(Long id);
	List<Vol> findByOrderByDestinationAsc();

	@Query("select v from Vol v order by v.destination Asc, v.agence DESC ")
	List<Vol> trierVolDestAgen();

	@Query("select v from Vol v where v.destination = ?1")
	List<Vol> findByDestVolContains(String destination);
}
