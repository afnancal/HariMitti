package com.afnan.harimitti.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.afnan.harimitti.helper.IndiaDateTime;
import com.afnan.harimitti.model.Admin;
import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.Maintainer;
import com.afnan.harimitti.model.ReturnMsg;

@Repository
public class MaintainerDaoImpl implements MaintainerDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Maintainer> getListMaintainer() {
		// TODO Auto-generated method stub
		// create Criteria
		CriteriaQuery<Maintainer> criteriaQuery = getSession().getCriteriaBuilder().createQuery(Maintainer.class);
		criteriaQuery.from(Maintainer.class);

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Maintainer> findMaintainerByName(String name) {
		// TODO Auto-generated method stub

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Maintainer> criteriaQuery = criteriaBuilder.createQuery(Maintainer.class);
		EntityType<Maintainer> type = getSession().getMetamodel().entity(Maintainer.class);
		Root<Maintainer> root = criteriaQuery.from(Maintainer.class);

		criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.like(
				criteriaBuilder.lower(root.get(type.getDeclaredSingularAttribute("name", String.class))),
				"%" + name.toLowerCase() + "%")));

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Maintainer> findMaintainerById(String maintainer_id) {
		// TODO Auto-generated method stub

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Maintainer> criteriaQuery = criteriaBuilder.createQuery(Maintainer.class);
		EntityType<Maintainer> type = getSession().getMetamodel().entity(Maintainer.class);
		Root<Maintainer> root = criteriaQuery.from(Maintainer.class);

		criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.like(
				criteriaBuilder.lower(root.get(type.getDeclaredSingularAttribute("maintainer_id", String.class))),
				"%" + maintainer_id.toLowerCase() + "%")));

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	public Login loginMaintainer(Maintainer maintainer) {

		Login login = new Login();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Maintainer> root = criteriaQuery.from(Maintainer.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Maintainer.class)));
		criteriaQuery.where(
				criteriaBuilder.or(criteriaBuilder.like(root.get("contact_no"), maintainer.getContact_no())),
				criteriaBuilder.or(criteriaBuilder.like(root.get("password"), maintainer.getPassword())));
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			// For inserting GCM_reg value into table
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<Maintainer> criteriaUpdate = builder.createCriteriaUpdate(Maintainer.class);
			Root<Maintainer> rootUpdate = criteriaUpdate.from(Maintainer.class);
			criteriaUpdate.set(rootUpdate.get("gcm_reg"), maintainer.getGcm_reg());
			criteriaUpdate.where(builder.equal(rootUpdate.get("contact_no"), maintainer.getContact_no()));
			getSession().createQuery(criteriaUpdate).executeUpdate();

			// For getting Login Maintainer details
			CriteriaBuilder criteriaBuilder1 = getSession().getCriteriaBuilder();
			CriteriaQuery<Maintainer> criteriaQuery1 = criteriaBuilder1.createQuery(Maintainer.class);
			Root<Maintainer> root1 = criteriaQuery1.from(Maintainer.class);

			criteriaQuery1.where(
					criteriaBuilder1.or(criteriaBuilder1.like(root1.get("contact_no"), maintainer.getContact_no())),
					criteriaBuilder1.or(criteriaBuilder1.like(root1.get("password"), maintainer.getPassword())));
			Maintainer maintainerGet = getSession().createQuery(criteriaQuery1).getSingleResult();

			login.setStatus(true);
			login.setMsg("Login successful as a Maintainer.");
			login.setUser_id(maintainerGet.getMaintainer_id());
			login.setName(maintainerGet.getName());
			login.setImg_url(maintainerGet.getImg_url());
			login.setUsertype("Maintainer");

		} else {
			/*
			 * login.setStatus(false); login.setMsg("Invalid username password.");
			 */
			login = adminLogin(maintainer.getContact_no(), maintainer.getPassword(), maintainer.getGcm_reg());

		}

		return login;

	}

	// For Admin Login
	private Login adminLogin(String contact_no, String password, String gcm_reg) {
		Login login = new Login();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Admin> root = criteriaQuery.from(Admin.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Admin.class)));
		criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.like(root.get("contact_no"), contact_no)),
				criteriaBuilder.or(criteriaBuilder.like(root.get("password"), password)));
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			// For inserting GCM_reg value into table
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<Admin> criteriaUpdate = builder.createCriteriaUpdate(Admin.class);
			Root<Admin> rootUpdate = criteriaUpdate.from(Admin.class);
			criteriaUpdate.set(rootUpdate.get("gcm_reg"), gcm_reg);
			criteriaUpdate.where(builder.equal(rootUpdate.get("contact_no"), contact_no));
			getSession().createQuery(criteriaUpdate).executeUpdate();

			// For getting Login member details
			CriteriaBuilder criteriaBuilder1 = getSession().getCriteriaBuilder();
			CriteriaQuery<Admin> criteriaQuery1 = criteriaBuilder1.createQuery(Admin.class);
			Root<Admin> root1 = criteriaQuery1.from(Admin.class);

			criteriaQuery1.where(criteriaBuilder1.or(criteriaBuilder1.like(root1.get("contact_no"), contact_no)),
					criteriaBuilder1.or(criteriaBuilder1.like(root1.get("password"), password)));
			Admin adminGet = getSession().createQuery(criteriaQuery1).getSingleResult();

			login.setStatus(true);
			login.setMsg("Login successful as an Admin.");
			login.setUser_id(adminGet.getAdmin_id());
			login.setName(adminGet.getName());
			login.setImg_url(adminGet.getImg_url());
			login.setUsertype("Admin");

		} else {
			login.setStatus(false);
			login.setMsg("Invalid username password.");

		}

		return login;
	}

	@Override
	public ReturnMsg createMaintainer(Maintainer maintainer) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Maintainer> root = criteriaQuery.from(Maintainer.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Maintainer.class)));
		criteriaQuery
				.where(criteriaBuilder.and(criteriaBuilder.like(root.get("contact_no"), maintainer.getContact_no())));
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			// System.out.println("present");
			returnMsg.setStatus(false);
			returnMsg.setMsg("Maintainer already existed.");

		} else {
			// System.out.println("absent");
			Maintainer maintainerObj = new Maintainer();
			maintainerObj.setMaintainer_id(createMaintainerId());
			maintainerObj.setName(maintainer.getName());
			maintainerObj.setAddress(maintainer.getAddress());
			maintainerObj.setContact_no(maintainer.getContact_no());
			maintainerObj.setEmail(maintainer.getEmail());
			maintainerObj.setPassword(maintainer.getPassword());
			maintainerObj.setGcm_reg(maintainer.getGcm_reg());
			maintainerObj.setImg_url(maintainer.getImg_url());
			maintainerObj.setAction_on(IndiaDateTime.getUTCdatetimeAsDate());

			try {
				String count = (String) getSession().save(maintainerObj);
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
	public ReturnMsg maintainerExist(String contact_no) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Maintainer> root = criteriaQuery.from(Maintainer.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Maintainer.class)));
		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.like(root.get("contact_no"), contact_no)));
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			returnMsg.setStatus(true);
			returnMsg.setMsg("Maintainer existed.");

		} else {
			returnMsg.setStatus(false);
			returnMsg.setMsg("Maintainer not existed.");

		}

		return returnMsg;
	}

	@Override
	public ReturnMsg updateMaintainerPassword(Maintainer maintainer) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		try {
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<Maintainer> criteria = builder.createCriteriaUpdate(Maintainer.class);
			Root<Maintainer> root = criteria.from(Maintainer.class);
			criteria.set(root.get("password"), maintainer.getPassword());
			criteria.where(builder.equal(root.get("contact_no"), maintainer.getContact_no()));
			getSession().createQuery(criteria).executeUpdate();

			returnMsg.setStatus(true);
			returnMsg.setMsg("Password reset successfully.");

		} catch (Exception e) {
			// TODO: handle exception

			returnMsg.setStatus(false);
			returnMsg.setMsg("Not reset successfully.");
			getSession().clear();
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg updateMaintainer(Maintainer maintainer) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		try {
			Maintainer maintainerObj = new Maintainer();
			maintainerObj.setMaintainer_id(maintainer.getMaintainer_id());
			maintainerObj.setName(maintainer.getName());
			maintainerObj.setAddress(maintainer.getAddress());
			maintainerObj.setContact_no(maintainer.getContact_no());
			maintainerObj.setEmail(maintainer.getEmail());
			maintainerObj.setPassword(maintainer.getPassword());
			maintainerObj.setGcm_reg(maintainer.getGcm_reg());
			maintainerObj.setImg_url(maintainer.getImg_url());
			maintainerObj.setAction_on(IndiaDateTime.getUTCdatetimeAsDate());

			getSession().update(maintainerObj);
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
	public ReturnMsg deleteMaintainer(String maintainer_id) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		Maintainer maintainer = (Maintainer) getSession().get(Maintainer.class, maintainer_id);
		getSession().delete(maintainer);

		returnMsg.setStatus(true);
		returnMsg.setMsg("Deleted successfully.");

		return returnMsg;
	}

	// -------------------For creating Maintainer Id------------------------------
	private String createMaintainerId() {

		String maintainerId = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSSSSSSSS");
		Date date = IndiaDateTime.getUTCdatetimeAsDate();
		maintainerId = "Main" + formatter.format(date);

		return maintainerId;
	}

}
