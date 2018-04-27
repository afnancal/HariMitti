package com.afnan.harimitti.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.afnan.harimitti.model.Product;
import com.afnan.harimitti.model.ReturnMsg;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Product> getListProduct() {
		// TODO Auto-generated method stub
		// create Criteria
		CriteriaQuery<Product> criteriaQuery = getSession().getCriteriaBuilder().createQuery(Product.class);
		criteriaQuery.from(Product.class);

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Product> searchProductByName(String name) {
		// TODO Auto-generated method stub

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		EntityType<Product> type = getSession().getMetamodel().entity(Product.class);
		Root<Product> root = criteriaQuery.from(Product.class);

		criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.like(
				criteriaBuilder.lower(root.get(type.getDeclaredSingularAttribute("name", String.class))),
				"%" + name.toLowerCase() + "%")));

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public ReturnMsg createProduct(Product product) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Product> root = criteriaQuery.from(Product.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Product.class)));
		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), product.getName())));
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			// System.out.println("present");
			returnMsg.setStatus(false);
			returnMsg.setMsg("Product already existed.");

		} else {
			// System.out.println("absent");
			Product productObj = new Product();
			productObj.setProduct_id(convertTimestamp());
			productObj.setName(product.getName());
			productObj.setDescription(product.getDescription());
			productObj.setImg_url(product.getImg_url());
			productObj.setAction_on(new Date());

			try {
				String count = (String) getSession().save(productObj);
				if (!count.equals(null)) {
					returnMsg.setStatus(true);
					returnMsg.setMsg("Successfully added.");

				} else {
					returnMsg.setStatus(false);
					returnMsg.setMsg("Not added successfully.");
				}

			} catch (Exception e) {
				// TODO: handle exception

				returnMsg.setStatus(false);
				returnMsg.setMsg("Not added successfully.");
				getSession().clear();
			}
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg updateProduct(Product product) {

		ReturnMsg returnMsg = new ReturnMsg();

		try {
			Product productObj = new Product();
			productObj.setProduct_id(product.getProduct_id());
			productObj.setName(product.getName());
			productObj.setDescription(product.getDescription());
			productObj.setImg_url(product.getImg_url());
			productObj.setAction_on(new Date());

			getSession().update(productObj);
			returnMsg.setStatus(true);
			returnMsg.setMsg("Successfully updated.");

		} catch (Exception e) {
			// TODO: handle exception

			returnMsg.setStatus(false);
			returnMsg.setMsg("Not updated successfully.");
			getSession().clear();
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg deleteProduct(String product_id) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		Product product = (Product) getSession().get(Product.class, product_id);
		getSession().delete(product);

		returnMsg.setStatus(true);
		returnMsg.setMsg("Deleted successfully.");

		return returnMsg;
	}

	// -------------------For creating Product Id--------------------------------
	private String convertTimestamp() {

		String newstring = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmssSSSSSSSSS");
		Date date = new Date();
		newstring = "P" + formatter.format(date);

		return newstring;
	}

}
