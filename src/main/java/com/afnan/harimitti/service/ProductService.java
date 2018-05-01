package com.afnan.harimitti.service;

import java.util.List;

import com.afnan.harimitti.model.Product;
import com.afnan.harimitti.model.ReturnMsg;

public interface ProductService {

	public List<Product> getListProduct();

	public List<Product> searchProductByName(String name);

	public ReturnMsg createProduct(Product product);

	public ReturnMsg updateProduct(Product product);

	public ReturnMsg deleteProduct(String product_id);

}
