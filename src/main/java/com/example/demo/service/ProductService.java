package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;


@Service
public class ProductService {
@Autowired
private ProductRepository productRepository;
public void addProducts(Product product)
{
	productRepository.save(product);
}
public List<Product>getAllProduct()
{
	return productRepository.findAll();
}
public void delProductById(long id)
{
	productRepository.deleteById(id);
}
public Optional<Product> getProductById(long id)
{
	return productRepository.findById(id);
}
public List<Product>getAllProductsByCategoryId(int id)
{
	return productRepository.findAllByCategory_Id(id);
}
}
