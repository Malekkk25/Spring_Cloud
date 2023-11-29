package com.malek.vols.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.malek.vols.dto.VolDTO;
import com.malek.vols.entities.Vol;
import com.malek.vols.service.VolService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class VolRESTControl {

	@Autowired
	VolService volService;

	@RequestMapping(path="all",method=RequestMethod.GET)
	public List<VolDTO> getAllVol(){
		return volService.getAllVols();
	}

	@RequestMapping(value="/getbyid/{idVol}",method=RequestMethod.GET)
	public VolDTO getVolById(@PathVariable("idVol") Long idVol) {
		return volService.getVol(idVol);
	}

	@RequestMapping(value="/addvol",method=RequestMethod.POST)
	public VolDTO createVol(@RequestBody VolDTO vol) {
		return volService.saveVol(vol);
	}

	@RequestMapping(value="/updatevol",method=RequestMethod.PUT)
	public VolDTO updateVol(@RequestBody VolDTO vol) {
		return volService.updateVol(vol);
	}

	@RequestMapping(value="/delvol/{id}",method=RequestMethod.DELETE)
	public void deleteVol(@PathVariable("id") Long id) {
		volService.deleteVolById(id);
	}

	@RequestMapping(value="/vair/{idAir}",method=RequestMethod.GET)
	public List<Vol> getVolsByAirId(@PathVariable("idAir") Long idAir){
		return volService.findByAeroportIdAir(idAir);
	}

	@RequestMapping(value="/vByDestination/{destination}",method = RequestMethod.GET)
	public List<Vol> findByDestVolContains(@PathVariable("destination") String destination) {
		return volService.findByDestVolContains(destination);
	}
}
