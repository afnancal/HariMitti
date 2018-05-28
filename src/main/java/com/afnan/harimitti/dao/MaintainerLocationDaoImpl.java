package com.afnan.harimitti.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public List<MaintainerLocation> findMaintainerLocationById(String maintainer_id) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MaintainerLocation> criteriaQuery = criteriaBuilder.createQuery(MaintainerLocation.class);
		Root<MaintainerLocation> root = criteriaQuery.from(MaintainerLocation.class);

		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("maintainer_id"), maintainer_id)));
		List<MaintainerLocation> maintainerLocation = getSession().createQuery(criteriaQuery).getResultList();

		return maintainerLocation;
	}

	@Override
	public ReturnMsg createMaintainerLocation(MaintainerLocation maintainerLocation) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		MaintainerLocation maintainerLocationObj = new MaintainerLocation();
		maintainerLocationObj.setMaintainer_id(maintainerLocation.getMaintainer_id());
		maintainerLocationObj.setLatitude(maintainerLocation.getLatitude());
		maintainerLocationObj.setLongitude(maintainerLocation.getLongitude());
		maintainerLocationObj.setAction_on(new Date());

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
