package com.example.ecommers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommers.entity.EcommerceEntity;

public interface EcommerseRepository extends JpaRepository<EcommerceEntity, Long> {
	
    List<EcommerceEntity> findByCategory(String category);
    List<EcommerceEntity> findByCategoryAndAvailability(String category, boolean availability);

	//Optional<EcommerceEntity> findByCategoryAvailability(String category, boolean availability);

}
