package com.afnan.harimitti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afnan.harimitti.dao.ProductDao;
import com.afnan.harimitti.model.Product;
import com.afnan.harimitti.model.ReturnMsg;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	ProductDao productDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> getListProduct() {
		// TODO Auto-generated method stub
		return productDao.getListProduct();
	}

	public List<Product> searchProductByName(String name) {
		return productDao.searchProductByName(name);
	}

	public ReturnMsg createProduct(Product product) {
		return productDao.createProduct(product);
	}

	public ReturnMsg updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	public ReturnMsg deleteProduct(String product_id) {
		return productDao.deleteProduct(product_id);
	}

}
