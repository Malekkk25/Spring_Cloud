package com.malek.vols.restcontrollers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.malek.vols.entities.Aeroport;
import com.malek.vols.repos.AeroportRepository;


@RestController
@RequestMapping("/api/air")
@CrossOrigin("http://localhost:4200/")
public class AeroportRESTController {
	@Autowired
		AeroportRepository aeroportRepository;
	@RequestMapping(method=RequestMethod.GET)
	public List<Aeroport> getAllAeroports()
	{
	return aeroportRepository.findAll();
	}
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Aeroport getAeroportById(@PathVariable("id") Long id) {
	return aeroportRepository.findById(id).get();
	}

}
