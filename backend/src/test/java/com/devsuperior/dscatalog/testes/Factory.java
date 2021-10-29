package com.devsuperior.dscatalog.testes;

import java.time.Instant;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

public class Factory {

	public static Product createProduct() {
		Product product = new Product(1L,"Phone","Good Phone",800.0,"https://www.foto.com/fotolinda",Instant.parse("2018-11-30T18:35:24.00Z"));
				product.getCategories().add(new Category(2L,"Electronics"));
				return product;
	}
	
	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		return new ProductDTO(product,product.getCategories());
	}
	
	public static Category createCategory() {
		return new Category(2L,"Electronics");
	}

	public static ProductDTO createProductDTOWithoutId() {
		return new ProductDTO(new Product(createProductDTO()));
	}

}
