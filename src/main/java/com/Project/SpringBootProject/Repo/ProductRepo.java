package com.Project.SpringBootProject.Repo;

import com.Project.SpringBootProject.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository <Product, Integer> {
    //now to search an object by brand or name or category, we can use JPQL(JPA query language).
    //BE CAREFULL WITH QUERY, SPACES,UPPERCASE, LOWER CASE, VARIABLE NAME ETC.
    @Query("SELECT p from Product p WHERE "+"LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR"
            +" LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR"+
            " LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR"+
            " LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR"+
            " LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword);
}