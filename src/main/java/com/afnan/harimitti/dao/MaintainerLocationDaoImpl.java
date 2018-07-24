package com.afnan.harimitti.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.afnan.harimitti.helper.IndiaDateTime;
import com.afnan.harimitti.model.MaintainerLocation;
import com.afnan.harimitti.model.ReturnMsg;

@Repository
public class MaintainerLocationDaoImpl implements MaintainerLocationDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<MaintainerLocation> findMaintainerLocationById(String maintainer_id, String membership_id,
			String date) {
		// TODO Auto-generated method stub
		Date startDate = IndiaDateTime.stringDateToDate(date + " 00:00:00");
		Date endDate = IndiaDateTime.stringDateToDate(date + " 23:59:59");

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MaintainerLocation> criteriaQuery = criteriaBuilder.createQuery(MaintainerLocation.class);
		Root<MaintainerLocation> root = criteriaQuery.from(MaintainerLocation.class);

		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("maintainer_id"), maintainer_id)),
				criteriaBuilder.and(criteriaBuilder.equal(root.get("membership_id"), membership_id)),
				criteriaBuilder.and(criteriaBuilder.between(root.get("action_on"), startDate, endDate)));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("action_on")));
		List<MaintainerLocation> maintainerLocation = getSession().createQuery(criteriaQuery).getResultList();

		return maintainerLocation;
	}

	@Override
	public ReturnMsg createMaintainerLocation(MaintainerLocation maintainerLocation) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		MaintainerLocation maintainerLocationObj = new MaintainerLocation();
		maintainerLocationObj.setMaintainer_id(maintainerLocation.getMaintainer_id());
		maintainerLocationObj.setMembership_id(maintainerLocation.getMembership_id());
		maintainerLocationObj.setImage_number(maintainerLocation.getImage_number());
		maintainerLocationObj.setLatitude(maintainerLocation.getLatitude());
		maintainerLocationObj.setLongitude(maintainerLocation.getLongitude());
		maintainerLocationObj.setAction_on(IndiaDateTime.getUTCdatetimeAsDate());

		try {
			int count = (int) getSession().save(maintainerLocationObj);
			if (count != 0) {
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

		return returnMsg;
	}

	@Override
	public ReturnMsg maintainerLocationExist(String maintainer_id) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<MaintainerLocation> root = criteriaQuery.from(MaintainerLocation.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(MaintainerLocation.class)));
		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.like(root.get("maintainer_id"), maintainer_id)));
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
	public ReturnMsg updateMaintainerLocation(String date, MaintainerLocation maintainerLocation) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();
		Date startDate = IndiaDateTime.stringDateToDate(date + " 00:00:00");
		Date endDate = IndiaDateTime.stringDateToDate(date + " 23:59:59");

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<MaintainerLocation> root = criteriaQuery.from(MaintainerLocation.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(MaintainerLocation.class)));
		criteriaQuery.where(
				criteriaBuilder
						.and(criteriaBuilder.equal(root.get("maintainer_id"), maintainerLocation.getMaintainer_id())),
				criteriaBuilder
						.and(criteriaBuilder.equal(root.get("membership_id"), maintainerLocation.getMembership_id())),
				criteriaBuilder
						.and(criteriaBuilder.equal(root.get("image_number"), maintainerLocation.getImage_number())),
				criteriaBuilder.and(criteriaBuilder.between(root.get("action_on"), startDate, endDate)));
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			// Maintainer Location existed
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<MaintainerLocation> criteria = builder.createCriteriaUpdate(MaintainerLocation.class);
			Root<MaintainerLocation> root1 = criteria.from(MaintainerLocation.class);
			criteria.set(root1.get("latitude"), maintainerLocation.getLatitude());
			criteria.set(root1.get("longitude"), maintainerLocation.getLongitude());
			criteria.where(
					builder.and(builder.equal(root1.get("maintainer_id"), maintainerLocation.getMaintainer_id())),
					builder.and(builder.equal(root1.get("membership_id"), maintainerLocation.getMembership_id())),
					builder.and(builder.equal(root1.get("image_number"), maintainerLocation.getImage_number())),
					builder.and(builder.between(root1.get("action_on"), startDate, endDate)));
			getSession().createQuery(criteria).executeUpdate();

			returnMsg.setStatus(true);
			returnMsg.setMsg("Successfully updated.");

		} else {
			// Maintainer Location not existed
			MaintainerLocation maintainerLocationObj = new MaintainerLocation();
			maintainerLocationObj.setMaintainer_id(maintainerLocation.getMaintainer_id());
			maintainerLocationObj.setMembership_id(maintainerLocation.getMembership_id());
			maintainerLocationObj.setImage_number(maintainerLocation.getImage_number());
			maintainerLocationObj.setLatitude(maintainerLocation.getLatitude());
			maintainerLocationObj.setLongitude(maintainerLocation.getLongitude());
			maintainerLocationObj.setAction_on(IndiaDateTime.getUTCdatetimeAsDate());

			int count = (int) getSession().save(maintainerLocationObj);
			if (count != 0) {
				returnMsg.setStatus(true);
				returnMsg.setMsg("Successfully created.");

			} else {
				returnMsg.setStatus(false);
				returnMsg.setMsg("Not created successfully.");
			}

		}

		return returnMsg;
	}

	@Override
	public ReturnMsg deleteMaintainerLocation(String maintainer_id) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		MaintainerLocation maintainerLocation = (MaintainerLocation) getSession().get(MaintainerLocation.class,
				maintainer_id);
		getSession().delete(maintainerLocation);

		returnMsg.setStatus(true);
		returnMsg.setMsg("Deleted successfully.");

		return returnMsg;
	}

}
