package com.malek.vols.service;

import com.malek.vols.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
 Image uplaodImage(MultipartFile file) throws IOException;
 Image getImageDetails(Long id) throws IOException;
 ResponseEntity<byte[]> getImage(Long id) throws IOException;
 void deleteImage(Long id) ;
 Image uplaodImageProd(MultipartFile file,Long idProd) throws IOException;
 List<Image> getImagesParVol(Long prodId);
}
