package com.example.ecommers.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ecommers.Dto.ProductDto;
import com.example.ecommers.entity.EcommerceEntity;
import com.example.ecommers.service.EcommerseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class EcommerceController {
	
	@Autowired
	private EcommerseService ecommerseService;
//create all products
	@PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) throws IOException {
        ProductDto savedProductDto = ecommerseService.createProduct(productDto);
        return ResponseEntity.ok(savedProductDto);
    }
//Get all products	
	@GetMapping("/all")
	public ResponseEntity<List<EcommerceEntity>> getAllProducts() {
	    List<EcommerceEntity> products = ecommerseService.getAllProducts();
	    return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
//get by id
@GetMapping("/{id}")
	public ResponseEntity<EcommerceEntity> getById(@PathVariable Long id )
	{
		Optional<EcommerceEntity> fOptional=ecommerseService.getById(id);
		if(fOptional.isPresent())
		{
		return new ResponseEntity<>(fOptional.get(),HttpStatus.OK);
		}
		else 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}	
	}
//get by category

@GetMapping
public ResponseEntity<List<EcommerceEntity>> getByCategory(@RequestParam String category) {
    List<EcommerceEntity> productsByCategory = ecommerseService.getByCategory(category);

    if (!productsByCategory.isEmpty()) {
        return new ResponseEntity<>(productsByCategory, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

//get by category and availability
@GetMapping("/category")
public ResponseEntity<List<EcommerceEntity>> getByCategoryAndAvailability(
        @RequestParam String category,
        @RequestParam boolean availability) {
    List<EcommerceEntity> productsByCategory = ecommerseService.getByCategoryAndAvailability(category, availability);

    if (!productsByCategory.isEmpty()) {
        return new ResponseEntity<>(productsByCategory, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
//update by id
@PutMapping("/{id}")
public ResponseEntity<String> updateById(
        @PathVariable Long id,
        @RequestParam String name,
        @RequestParam String category,
        @RequestParam double retailPrice,
        @RequestParam double discountedPrice,
        @RequestParam boolean availability
) throws IOException {
    String result = ecommerseService.updateById(id, name, category, retailPrice, discountedPrice, availability);
    return new ResponseEntity<>(result, HttpStatus.OK);
}

}

	