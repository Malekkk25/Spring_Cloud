package com.malek.vols;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.malek.vols.entities.Aeroport;
import com.malek.vols.entities.Vol;
import com.malek.vols.repos.VolRepository;

@SpringBootTest
class VolsApplicationTests {

	@Autowired
	private VolRepository volRepository;
	
	@Test
	public void testCreateVol() {
		Vol v1=new Vol("TU518","Tunisair","Istanbul","atteri",new Date());
		volRepository.save(v1);
	}
	
	@Test
	public void testFindVol() {
		Vol v=volRepository.findById(1L).get();
		System.out.println(v);
	}
	
	@Test
	public void testUpdateVol() {
		Vol v=volRepository.findById(1L).get();
		v.setDestination("Barcelone");
		volRepository.save(v);
		System.out.println(v);
	}
	
	@Test
	public void testDeleteVol() {
		volRepository.deleteById(1L);
	}
	
	@Test
	public void testFindAllVol() {
		List<Vol> v=volRepository.findAll();
		for(Vol v2:v)
			System.out.println(v2);
		}
	
	@Test
	public void testFindVolByNum() {
		List<Vol> v=(List<Vol>) volRepository.findByNumVol("TU518");
		for(Vol v2:v)
			System.out.println(v2);
	}
	
	@Test
	public void testFindVolByNumContains() {
		List<Vol> v=(List<Vol>) volRepository.findByNumVolContains("51");
		for(Vol v2:v)
			System.out.println(v2);
	}
	
	@Test
	public void testfindByNomDes()
	{
	List<Vol> v1 = volRepository.findByNomDes("TU515", "Nice");
	for (Vol v : v1)
	{
	System.out.println(v);
	}
	}
	
	@Test
	public void testfindByCategorie()
	{
	Aeroport air = new Aeroport();
	air.setIdAir(123L);
	List<Vol> v1 = volRepository.findByAeroport(air);
	for (Vol v : v1)
	{
	System.out.println(v);
	}
	}

	@Test 
	public void findByAeroportIdAir() {
		List<Vol> v=volRepository.findByAeroportIdAir(123L);
		for(Vol v1:v)
		{
			System.out.println(v1);
		}
	}
	
	
	@Test
	public void testfindByOrderByDestinationAsc() {
		List<Vol> v=volRepository.findByOrderByDestinationAsc();
		for (Vol v1:v) {
			System.out.println(v1);
		}
	}
	
	@Test
	public void testTrierVolDestAgen() {
		List<Vol>v= volRepository.trierVolDestAgen();
		for(Vol v1 : v) {
			System.out.println(v1);
		}
	}

}
