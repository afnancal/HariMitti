package com.afnan.harimitti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.afnan.harimitti.model.Product;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	// -------------------Retrieve All Products-------------------------------
	@RequestMapping(value = "/productList", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Product> getListProduct() {
		List<Product> products = productService.getListProduct();

		return products;
	}

	// -------------------Search Products by name------------------------------
	@RequestMapping(value = "/searchProduct/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Product> search(@PathVariable("name") String name) {
		List<Product> products = productService.searchProductByName(name);

		return products;
	}

	// -------------------Create a Product------------------------------------
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public @ResponseBody ReturnMsg add(@RequestBody Product product) {

		return productService.createProduct(product);
	}

	// ------------------- Update a Product -----------------------------------
	@RequestMapping(value = "/updateProduct/{product_id}", method = RequestMethod.PUT)
	public @ResponseBody ReturnMsg update(@PathVariable("product_id") String product_id, @RequestBody Product product) {
		product.setProduct_id(product_id);

		return productService.updateProduct(product);
	}

	// ------------------- Delete a Product ---------------------------------
	@RequestMapping(value = "/deleteProduct/{product_id}", method = RequestMethod.DELETE)
	public @ResponseBody ReturnMsg delete(@PathVariable("product_id") String product_id) {

		return productService.deleteProduct(product_id);
	}

}
