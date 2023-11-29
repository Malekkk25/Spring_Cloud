package com.malek.vols.service;

import java.util.List;

import com.malek.vols.dto.VolDTO;
import com.malek.vols.entities.Aeroport;
import com.malek.vols.entities.Vol;

public interface VolService {
	VolDTO saveVol(VolDTO v);
	VolDTO updateVol(VolDTO v);
	void deleteVol(Vol v);
	void deleteVolById(Long id);
	//Vol getVol(Long id);
	//List<Vol> getAllVols();
	List<Vol> findByNumVol(String num);
	List<Vol> findByNumVolContains(String num);
	List<Vol> findByDestVolContains(String destination);
	List<Vol> findByDestAgen (String destination, String agence);
	List<Vol> findByAeroport (Aeroport aeroport);
	List<Vol> findByAeroportIdAir(Long id);
	List<Vol> findByOrderByDestinationAsc();
	List<Vol> trierVolDestAgen();
	VolDTO convertEntityToDto (Vol vol);
	Vol convertDtoToEntity(VolDTO volDto);



	VolDTO getVol(Long id);
	List<VolDTO> getAllVols();


}
