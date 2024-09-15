package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;


@Controller
public class AdminController {
 	@Autowired
	CategoryService categoryService;
 	@Autowired
	ProductService productService;
 	@Autowired
	UserDetailsService userDetailsService;
 	@Autowired
	private UserService userService;
	

 	@RequestMapping("/admin")
	public String adminHome() {	
				
		return "adminHome";
	}
	@GetMapping("/admin/categories")
	public String categories(Model model) {	
				
		model.addAttribute("categories", categoryService.dispCategory());
		return "categories";
	}
	@GetMapping("/admin/categories/addCategory")
	public String addCategory(Model model) {	
				
		model.addAttribute("category" ,new Category());
		return "addCategory";
	}
	@RequestMapping("/admin/categories/save")
	public String saveCategory(@ModelAttribute("category") Category category)
	{
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	@RequestMapping("/admin/categories/delete/{id}")
	public String delCategory(@PathVariable int id)
	{
		categoryService.delCategoryById(id);
		return "redirect:/admin/categories";
	}
	@RequestMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id,Model model)
	{
		Optional<Category> category=categoryService.getCategoryById(id);
		if(category.isPresent())
		{
			model.addAttribute("category", category.get());
			return "addCategory";
		}
		else return "404";
	} 
	//Product section 
	@GetMapping("/admin/products")
	public String products(Model model) {	
				
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}
	@GetMapping("/admin/products/addProducts")
	public String addProducts(Model model) {	
				
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories",categoryService.dispCategory());
		return "addProducts";
	}
	@RequestMapping("/admin/products/save")
	public String saveProduct(@ModelAttribute("productDTO") ProductDTO productDTO)
	{
		Product product=new Product();
		product.setProduct_id(productDTO.getProduct_id());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		productService.addProducts(product);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/delete/{id}")
	public String delProduct(@PathVariable long id)
	{
		productService.delProductById(id);
		return "redirect:/admin/products";
	}
	@RequestMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable long id,Model model)
	{
	ProductDTO productDTO=new ProductDTO();
	Product product=productService.getProductById(id).get(); //Optional value return
	productDTO.setProduct_id(product.getProduct_id());
	productDTO.setName(product.getName());
	productDTO.setCategoryId(product.getCategory().getId());
	productDTO.setPrice(product.getPrice());
	productDTO.setWeight(product.getWeight());
	//productDTO.setImgName(product.getImgName());
	
	model.addAttribute("categories",categoryService.dispCategory());
	model.addAttribute("productDTO",productDTO);
	return "addProducts";
	}
	@GetMapping("/admin/registration")
	public String getRegistration()
	{
		return "register";
		
	}
	@PostMapping("/admin/registration")
	public String saveUser(@ModelAttribute("user")UserDTO userDTO,Model model)
	{
		userDTO.setRole("USER");
		userService.save(userDTO);
		model.addAttribute("message","Registered Successfully");
		return "register";
	}
}
