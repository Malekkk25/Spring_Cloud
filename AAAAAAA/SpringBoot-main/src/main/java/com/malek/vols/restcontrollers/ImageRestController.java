package com.malek.vols.restcontrollers;

import com.malek.vols.entities.Image;
import com.malek.vols.service.ImageService;
import com.malek.vols.service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")

public class ImageRestController {
 @Autowired
 ImageService imageService ;

 @Autowired
 VolService volService ;

 @RequestMapping(value = "/upload" , method = RequestMethod.POST)
 public Image uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
  return imageService.uplaodImage(file);
 }
 @RequestMapping(value = "/get/info/{id}" , method = RequestMethod.GET)
 public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
  return imageService.getImageDetails(id) ;
 }
 @RequestMapping(value = "/load/{id}" , method = RequestMethod.GET)
 public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException
 {
  return imageService.getImage(id);
 }


 @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
 public void deleteImage(@PathVariable("id") Long id){
  imageService.deleteImage(id);
 }
 @RequestMapping(value="/update",method = RequestMethod.PUT)
 public Image UpdateImage(@RequestParam("image")MultipartFile file) throws IOException {
  return imageService.uplaodImage(file);
 }

 @PostMapping(value = "/uplaodImageProd/{idProd}" )
 public Image uploadMultiImages(@RequestParam("image")MultipartFile file,
                                @PathVariable("idProd") Long idProd)
         throws IOException {
  return imageService.uplaodImageProd(file,idProd);
 }
 @RequestMapping(value = "/getImagesProd/{idProd}" ,
         method = RequestMethod.GET)
 public List<Image> getImagesProd(@PathVariable("idProd") Long idProd)
         throws IOException {
  return imageService.getImagesParVol(idProd);
 }



/*@RequestMapping(value = "/uploadFS/{id}" , method = RequestMethod.POST)
public void uploadImageFS(@RequestParam("image") MultipartFile
file,@PathVariable("id") Long id) throws IOException {
Vol p = volService.getVol(id);
p.setImagePath(id+".jpg");

Files.write(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath())
, file.getBytes());
volService.saveVol(p);

}*/



/*@RequestMapping(value = "/loadfromFS/{id}" ,
method = RequestMethod.GET,
produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {
VolDTO p = volService.getVol(id);
return
Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/images/"+p.getImagePath()));
}*/

}
