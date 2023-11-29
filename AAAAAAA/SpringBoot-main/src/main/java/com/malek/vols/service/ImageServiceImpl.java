package com.malek.vols.service;

import com.malek.vols.entities.Image;
import com.malek.vols.entities.Vol;
import com.malek.vols.repos.ImageRepository;
import com.malek.vols.repos.VolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{
 @Autowired
 ImageRepository imageRepository;
 
 @Autowired
 VolService volService;
 
 @Autowired
 VolRepository volRepository;
 @Override
 public Image uplaodImage(MultipartFile file) throws IOException {
 /*Ce code commenté est équivalent au code utilisant le design pattern Builder
 * Image image = new Image(null, file.getOriginalFilename(), 
 file.getContentType(), file.getBytes(), null);
 return imageRepository.save(image);*/
 return imageRepository.save(Image.builder()
 .name(file.getOriginalFilename())
 .type(file.getContentType())
 .image(file.getBytes()).build() );
 }
 @Override
 public Image getImageDetails(Long id) throws IOException{
 final Optional<Image> dbImage = imageRepository. findById (id);
 return Image.builder()
 .idImage(dbImage.get().getIdImage())
 .name(dbImage.get().getName())
 .type(dbImage.get().getType())
 .image(dbImage.get().getImage()).build() ;
 }
 @Override
 public ResponseEntity<byte[]> getImage(Long id) throws IOException{
 final Optional<Image> dbImage = imageRepository. findById (id);
 return ResponseEntity
 .ok()
 .contentType(MediaType.valueOf(dbImage.get().getType()))
 .body(dbImage.get().getImage());
 }
 @Override
 public void deleteImage(Long id) {
 imageRepository.deleteById(id);
 }
 
 @Override
 public Image uplaodImageProd(MultipartFile file,Long idVol) 
 throws IOException {
 Vol v = new Vol();
 v.setIdVol(idVol);
 return imageRepository.save(Image.builder()
  .name(file.getOriginalFilename())
  .type(file.getContentType())
  .image(file.getBytes())
  .vol(v).build() );
 }
@Override
public List<Image> getImagesParVol(Long volId) {
	Vol v = volRepository.findById(volId).get();
	return v.getImages();

}

 
}

