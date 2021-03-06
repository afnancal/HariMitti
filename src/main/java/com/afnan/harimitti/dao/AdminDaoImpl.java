package com.afnan.harimitti.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.afnan.harimitti.helper.IndiaDateTime;
import com.afnan.harimitti.model.Admin;
import com.afnan.harimitti.model.ReturnMsg;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Admin> getListAdmin() {
		// TODO Auto-generated method stub
		// create Criteria
		CriteriaQuery<Admin> criteriaQuery = getSession().getCriteriaBuilder().createQuery(Admin.class);
		criteriaQuery.from(Admin.class);

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Admin> findAdminById(String admin_id) {
		// TODO Auto-generated method stub

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Admin> criteriaQuery = criteriaBuilder.createQuery(Admin.class);
		EntityType<Admin> type = getSession().getMetamodel().entity(Admin.class);
		Root<Admin> root = criteriaQuery.from(Admin.class);

		criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.like(
				criteriaBuilder.lower(root.get(type.getDeclaredSingularAttribute("admin_id", String.class))),
				"%" + admin_id.toLowerCase() + "%")));

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Admin> findAdminByName(String name) {
		// TODO Auto-generated method stub

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Admin> criteriaQuery = criteriaBuilder.createQuery(Admin.class);
		EntityType<Admin> type = getSession().getMetamodel().entity(Admin.class);
		Root<Admin> root = criteriaQuery.from(Admin.class);

		criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.like(
				criteriaBuilder.lower(root.get(type.getDeclaredSingularAttribute("name", String.class))),
				"%" + name.toLowerCase() + "%")));

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public ReturnMsg createAdmin(Admin admin) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Admin> root = criteriaQuery.from(Admin.class);
		Predicate cond = null;

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Admin.class)));
		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.like(root.get("contact_no"), admin.getContact_no())));
		// If both columns should match the test values
		cond = criteriaBuilder.and(criteriaBuilder.equal(root.get("admin_id"), admin.getAdmin_id()),
				criteriaBuilder.equal(root.get("contact_no"), admin.getContact_no()));

		// If only one of the 2 columns should match the test values
		cond = criteriaBuilder.or(criteriaBuilder.equal(root.get("admin_id"), admin.getAdmin_id()),
				criteriaBuilder.equal(root.get("contact_no"), admin.getContact_no()));

		criteriaQuery.where(cond);
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			// System.out.println("present");
			returnMsg.setStatus(false);
			returnMsg.setMsg("Admin already existed, please login.");

		} else {
			// System.out.println("absent");
			Admin adminObj = new Admin();
			adminObj.setAdmin_id(admin.getAdmin_id());
			adminObj.setName(admin.getName());
			adminObj.setAddress(admin.getAddress());
			adminObj.setContact_no(admin.getContact_no());
			adminObj.setEmail(admin.getEmail());
			adminObj.setPassword(admin.getPassword());
			adminObj.setGcm_reg(admin.getGcm_reg());
			adminObj.setImg_url(admin.getImg_url());
			adminObj.setAction_on(IndiaDateTime.getUTCdatetimeAsDate());

			try {
				String count = (String) getSession().save(adminObj);
				if (!count.equals(null)) {
					returnMsg.setStatus(true);
					returnMsg.setMsg("Successfully created.");

				} else {
					returnMsg.setStatus(false);
					returnMsg.setMsg("Not created successfully.");
				}

			} catch (Exception e) {
				// TODO: handle exception

				returnMsg.setStatus(false);
				returnMsg.setMsg("Not created successfully.");
				getSession().clear();
			}
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		try {
			Admin adminObj = new Admin();
			adminObj.setAdmin_id(admin.getAdmin_id());
			adminObj.setName(admin.getName());
			adminObj.setAddress(admin.getAddress());
			adminObj.setContact_no(admin.getContact_no());
			adminObj.setEmail(admin.getEmail());
			adminObj.setPassword(admin.getPassword());
			adminObj.setGcm_reg(admin.getGcm_reg());
			adminObj.setImg_url(admin.getImg_url());
			adminObj.setAction_on(IndiaDateTime.getUTCdatetimeAsDate());

			getSession().update(adminObj);
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
	public ReturnMsg deleteAdmin(String admin_id) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		Admin admin = (Admin) getSession().get(Admin.class, admin_id);
		getSession().delete(admin);

		returnMsg.setStatus(true);
		returnMsg.setMsg("Deleted successfully.");

		return returnMsg;
	}

}
