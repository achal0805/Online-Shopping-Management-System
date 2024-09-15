package com.example.demo.model;
import jakarta.persistence.*;

	@Entity
	@Table(name="product")
	public class Product {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long product_id;
		private String name;
		
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="id",referencedColumnName="id")
		private Category category; //dependent object

		private double price;
		private double weight;
		public Long getProduct_id() {
			return product_id;
		}
		public void setProduct_id(Long product_id) {
			this.product_id = product_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category category) {
			this.category = category;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public double getWeight() {
			return weight;
		}
		public void setWeight(double weight) {
			this.weight = weight;
		}
}
