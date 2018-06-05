package com.afnan.harimitti.dao;

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
import com.afnan.harimitti.model.MaintainerAllotment;
import com.afnan.harimitti.model.ReturnMsg;

@Repository
public class MaintainerAllotmentDaoImpl implements MaintainerAllotmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<MaintainerAllotment> getListMaintainerAllotment() {
		// TODO Auto-generated method stub
		// create Criteria
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MaintainerAllotment> criteriaQuery = criteriaBuilder.createQuery(MaintainerAllotment.class);
		criteriaQuery.from(MaintainerAllotment.class);

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<MaintainerAllotment> findMaintainerAllotmentByMainId(String maintainer_id) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MaintainerAllotment> criteriaQuery = criteriaBuilder.createQuery(MaintainerAllotment.class);
		Root<MaintainerAllotment> root = criteriaQuery.from(MaintainerAllotment.class);

		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("maintainer_id"), maintainer_id)));
		List<MaintainerAllotment> maintainerAllotments = getSession().createQuery(criteriaQuery).getResultList();

		return maintainerAllotments;
	}

	@Override
	public List<MaintainerAllotment> findMaintainerAllotmentByMembId(String member_id) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MaintainerAllotment> criteriaQuery = criteriaBuilder.createQuery(MaintainerAllotment.class);
		Root<MaintainerAllotment> root = criteriaQuery.from(MaintainerAllotment.class);

		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("membership_id"), member_id)));
		List<MaintainerAllotment> maintainerLocation = getSession().createQuery(criteriaQuery).getResultList();

		return maintainerLocation;
	}

	@Override
	public ReturnMsg createMaintainerAllotment(MaintainerAllotment maintainerAllotment) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		MaintainerAllotment maintainerAllotmentObj = new MaintainerAllotment();
		maintainerAllotmentObj.setMaintainer_id(maintainerAllotment.getMaintainer_id());
		maintainerAllotmentObj.setMembership_id(maintainerAllotment.getMembership_id());
		maintainerAllotmentObj.setStatus(maintainerAllotment.getStatus());
		maintainerAllotmentObj.setSchedule(maintainerAllotment.getSchedule());
		maintainerAllotmentObj.setAction_on(IndiaDateTime.getUTCdatetimeAsDate());

		try {
			int count = (int) getSession().save(maintainerAllotmentObj);
			if (count != 0) {
				returnMsg.setStatus(true);
				returnMsg.setMsg("Successfully allotted.");

			} else {
				returnMsg.setStatus(false);
				returnMsg.setMsg("Not allotted successfully.");
			}

		} catch (Exception e) {
			// TODO: handle exception

			returnMsg.setStatus(false);
			returnMsg.setMsg("Not allotted successfully.");
			getSession().clear();
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg updateMaintainerAllotment(MaintainerAllotment maintainerAllotment) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		try {
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<MaintainerAllotment> criteria = builder.createCriteriaUpdate(MaintainerAllotment.class);
			Root<MaintainerAllotment> root = criteria.from(MaintainerAllotment.class);
			criteria.set(root.get("maintainer_id"), maintainerAllotment.getMaintainer_id());
			criteria.set(root.get("membership_id"), maintainerAllotment.getMembership_id());
			criteria.set(root.get("status"), maintainerAllotment.getStatus());
			criteria.set(root.get("schedule"), maintainerAllotment.getSchedule());
			criteria.set(root.get("action_on"), IndiaDateTime.getUTCdatetimeAsDate());
			criteria.where(builder.equal(root.get("id"), maintainerAllotment.getId()));
			getSession().createQuery(criteria).executeUpdate();

			returnMsg.setStatus(true);
			returnMsg.setMsg("Updated successfully.");

		} catch (Exception e) {
			// TODO: handle exception

			returnMsg.setStatus(false);
			returnMsg.setMsg("Not updated successfully.");
			getSession().clear();
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg maintainerAllotmentExist(String maintainer_id) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<MaintainerAllotment> root = criteriaQuery.from(MaintainerAllotment.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(MaintainerAllotment.class)));
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

	/*
	 * private Date convertStringToDate(String date) { Date scheduleDate = null; try
	 * { scheduleDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss",
	 * Locale.ENGLISH).parse(date); } catch (ParseException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); } return scheduleDate; }
	 */

}
