package com.malek.vols.repos;


import com.malek.vols.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image , Long> {
}
