package com.example.ecommers.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommers.Dto.ProductDto;
import com.example.ecommers.entity.EcommerceEntity;
import com.example.ecommers.repository.EcommerseRepository;

import jakarta.transaction.Transactional;

@Service
public class EcommerseService {
	
	@Autowired
	private EcommerseRepository ecommerseRepository;
	
	//1.Add a product
	public ProductDto createProduct(ProductDto productDto) {
    EcommerceEntity ecommerce = new EcommerceEntity(
            productDto.getId(),
            productDto.getName(),
            productDto.getCategory(),
            productDto.getRetailPrice(),
            productDto.getDiscountedPrice(),
            productDto.isAvailability()
    );
        EcommerceEntity savedProduct = ecommerseRepository.save(ecommerce);

        // Convert the saved entity back to a DTO for response
        ProductDto savedProductDto = new ProductDto(
        		savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getCategory(),
                savedProduct.getRetailPrice(),
                savedProduct.getDiscountedPrice(),
                savedProduct.isAvailability()
        );

        return savedProductDto;
    }
    
    //2.return all
   	public List<EcommerceEntity> getAllProducts() {
   	    return ecommerseRepository.findAll();
   	}
   	
   //3.return by id 
  	public Optional<EcommerceEntity> getById(Long id) {
  	    return ecommerseRepository.findById(id);
  	}
  	
   //4.return by category
  	
  	public List<EcommerceEntity> getByCategory(String category) {
        return ecommerseRepository.findByCategory(category);
    }
  	
  //5.return by category, availability
  	
  	public List<EcommerceEntity> getByCategoryAndAvailability(String category, boolean availability) {
        return ecommerseRepository.findByCategoryAndAvailability(category, availability);
    }
  	
  //6.update by id 
  	public String updateById(Long id, String name, String category, double retailPrice, double discountedPrice, boolean availability) throws IOException {
  	    EcommerceEntity existingProduct = ecommerseRepository.findById(id)
  	            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

  	    existingProduct.setName(name);
  	    existingProduct.setCategory(category);
  	    existingProduct.setRetailPrice(retailPrice);
  	    existingProduct.setDiscountedPrice(discountedPrice);
  	    existingProduct.setAvailability(availability);

  	    ecommerseRepository.save(existingProduct);

  	    return "Product updated successfully";
  	}



}
