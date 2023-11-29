package com.malek.vols.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malek.vols.dto.VolDTO;
import com.malek.vols.entities.Aeroport;
import com.malek.vols.entities.Vol;
import com.malek.vols.entities.Image;
import com.malek.vols.repos.ImageRepository;
import com.malek.vols.repos.VolRepository;

@Service
public class VolServiceImpl implements VolService {

	@Autowired
	VolRepository volRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ModelMapper modelMapper;

	/*@Override
	public Vol saveVol(Vol v) {
		// TODO Auto-generated method stub
		return volRepository.save(v);
	}*/

	/*@Override
	public Vol updateVol(Vol v) {
		// TODO Auto-generated method stub
		return volRepository.save(v);
	}*/
	@Override
	public VolDTO updateVol(VolDTO v) {
		//Long oldProdImageId =
		//this.getVol(v.getIdVol()).getImage().getIdImage();
		/*Long newVolImageId = v.getImage().getIdImage();*/

		//if (oldProdImageId != newVolImageId) //si l'image a été modifiée
		//imageRepository.deleteById(oldProdImageId);
		return  convertEntityToDto(volRepository.save(convertDtoToEntity(v)));
	}


	@Override
	public void deleteVol(Vol v) {
		// TODO Auto-generated method stub
		volRepository.delete(v);

	}

	@Override
	public void deleteVolById(Long id) {
		// TODO Auto-generated method stub
		volRepository.deleteById(id);
	}
	/*@Override
	public void deleteVolById(Long id) {
	 VolDTO p = getVol(id);
	 //suuprimer l'image avant de supprimer le produit
	try {
	Files.delete(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));
	} catch (IOException e) {
	e.printStackTrace();
	}
	volRepository.deleteById(id);
	}*/

	/*@Override
	public Vol getVol(Long id) {
		// TODO Auto-generated method stub
		 return volRepository.findById(id).get();
	}*/

	/*@Override
	public List<Vol> getAllVols() {
		// TODO Auto-generated method stub
		return volRepository.findAll();
	}*/

	@Override
	public List<Vol> findByNumVol(String num) {
		// TODO Auto-generated method stub
		return volRepository.findByNumVol(num);
	}

	@Override
	public List<Vol> findByNumVolContains(String num) {
		// TODO Auto-generated method stub
		return volRepository.findByNumVolContains(num);
	}

	@Override
	public List<Vol> findByDestAgen(String destination, String agence) {
		// TODO Auto-generated method stub
		return volRepository.findByNomDes(agence, destination);
	}

	@Override
	public List<Vol> findByAeroport(Aeroport aeroport) {
		// TODO Auto-generated method stub
		return volRepository.findByAeroport(aeroport);
	}

	@Override
	public List<Vol> findByAeroportIdAir(Long id) {
		// TODO Auto-generated method stub
		return volRepository.findByAeroportIdAir(id);
	}

	@Override
	public List<Vol> findByOrderByDestinationAsc() {
		// TODO Auto-generated method stub
		return volRepository.findByOrderByDestinationAsc();
	}

	@Override
	public List<Vol> trierVolDestAgen() {
		// TODO Auto-generated method stub
		return volRepository.trierVolDestAgen();
	}

	@Override
	public List<Vol> findByDestVolContains(String destination) {
		// TODO Auto-generated method stub
		return volRepository.findByDestVolContains(destination);
	}

	@Override
	public VolDTO convertEntityToDto(Vol vol) {
		/*VolDTO volDTO = new VolDTO();
		 volDTO.setIdVol(vol.getIdVol());
		 volDTO.setNumVol(vol.getNumVol());
		 volDTO.setAgence(vol.getAgence());
		 volDTO.setDestination(vol.getDestination());
		 volDTO.setEtat(vol.getEtat());
		 volDTO.setHeureDepart(vol.getHeureDepart());
		 volDTO.setAeroport(vol.getAeroport());
		 return volDTO;

		 /*return ProduitDTO.builder()
		.idProduit(produit.getIdProduit())
		.nomProduit(produit.getNomProduit())
		.prixProduit(produit.getPrixProduit())
		.dateCreation(p.getDateCreation())
		.categorie(produit.getCategorie())
		.build();*/
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		VolDTO volDTO =modelMapper.map(vol, VolDTO.class);
		return volDTO;

	}




	@Override
	public VolDTO saveVol(VolDTO v) {
		return convertEntityToDto( volRepository.save(convertDtoToEntity(v)));

	}


	@Override
	public VolDTO getVol(Long id) {
		return convertEntityToDto( volRepository.findById(id).get());

	}


	@Override
	public List<VolDTO> getAllVols() {
		return volRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());

	}

	@Override
	public Vol convertDtoToEntity(VolDTO volDto) {
		 /*Vol vol = new  Vol();
		vol.setIdVol(volDto.getIdVol());
		 vol.setNumVol(volDto.getNumVol());
		 vol.setAgence(volDto.getAgence());
		 vol.setDestination(volDto.getDestination());
		 vol.setEtat(volDto.getEtat());
		 vol.setHeureDepart(volDto.getHeureDepart());
		 vol.setAeroport(volDto.getAeroport());
		 return vol;*/
		Vol vol = new  Vol();
		vol=modelMapper.map(volDto, Vol.class);
		return vol;
	}

}
