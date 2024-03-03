package com.example.ecommers.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	private long id;
	private String name;
	private String category;
	private double retailPrice;
	private double discountedPrice;
	private boolean availability;

}
