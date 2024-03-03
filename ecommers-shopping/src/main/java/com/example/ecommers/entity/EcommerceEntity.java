package com.example.ecommers.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="ecommerce")
public class EcommerceEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message="name should not be empty")
	@Size(min = 2, max = 150, message = "Name length must be between 2 and 150 characters")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters")
	private String name;
	
	@NotBlank (message="category should not be empty")
	private String category;
	
	@DecimalMin(value = "0.00", inclusive = false, message = "retailPrice must be greater than 0 with up to 2 decimal places")
	private double retailPrice;
	
	@DecimalMin(value = "0.00", inclusive = false, message = "discountedPrice must be greater than 0 with up to 2 decimal places")
	private double discountedPrice;
	
	private boolean availability;

}
