package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.testes.Factory;

@DataJpaTest
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository repository;
	private long existingId;
	private long nomExistingId;
	private long countTotalProducts;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nomExistingId = 1000L;
		countTotalProducts = 25L;
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		repository.deleteById(existingId);
		var product = repository.findById(existingId);
		Assertions.assertFalse(product.isPresent());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdNotExists() {
		

		Assertions.assertThrows(EmptyResultDataAccessException.class, ()->{
			repository.deleteById(nomExistingId);
		});
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Product product = Factory.createProduct();
		product.setId(null);
		
		product = repository.save(product);
		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalProducts + 1, product.getId());
	}
	
	@Test
	public void findByIdShouldNotReturnEmptyIdIfExists() {
		Optional<Product> test = repository.findById(existingId);
		Assertions.assertTrue(test.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmptyIdIfNotExists() {
		Optional<Product> test = repository.findById(nomExistingId);
		Assertions.assertEquals(Optional.empty(),test);
	}

}
