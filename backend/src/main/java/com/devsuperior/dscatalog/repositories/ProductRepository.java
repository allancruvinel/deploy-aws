package com.devsuperior.dscatalog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT DISTINCT obj "
			+ "FROM Product obj "
			+ "INNER JOIN obj.categories cats "
			+ "WHERE (COALESCE(:listCat) IS NULL OR cats in :listCat) "
			+ "AND (UPPER(obj.name) LIKE UPPER('%'||:name||'%'))")
	Page<Product> find(List<Category> listCat,String name, Pageable pageable);
	
	@Query("SELECT obj FROM Product obj JOIN FETCH obj.categories"
			+ " Where obj in :listCat")
	List<Product> findProductsWithCategories(List<Product> listCat);

}
