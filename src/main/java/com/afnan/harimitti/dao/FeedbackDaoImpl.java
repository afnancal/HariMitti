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

import com.afnan.harimitti.model.Feedback;
import com.afnan.harimitti.model.MaintainerAllotment;
import com.afnan.harimitti.model.ReturnMsg;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Feedback> searchFeedbackByMaintainerId(String maintainer_id) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Feedback> criteriaQuery = criteriaBuilder.createQuery(Feedback.class);
		Root<Feedback> root = criteriaQuery.from(Feedback.class);

		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("maintainer_id"), maintainer_id)));
		List<Feedback> feedbacks = getSession().createQuery(criteriaQuery).getResultList();

		return feedbacks;
	}

	@Override
	public List<Feedback> searchFeedbackByMemberId(String member_id) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Feedback> criteriaQuery = criteriaBuilder.createQuery(Feedback.class);
		Root<Feedback> root = criteriaQuery.from(Feedback.class);

		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("membership_id"), member_id)));
		List<Feedback> feedbacks = getSession().createQuery(criteriaQuery).getResultList();

		return feedbacks;
	}

	@Override
	public ReturnMsg createFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		Feedback feedbackObj = new Feedback();
		feedbackObj.setMaintainers_allotment_id(feedback.getMaintainers_allotment_id());
		feedbackObj.setMaintainer_id(feedback.getMaintainer_id());
		feedbackObj.setMembership_id(feedback.getMembership_id());
		feedbackObj.setPlant_img1(feedback.getPlant_img1());
		feedbackObj.setPlant_img2(feedback.getPlant_img2());
		feedbackObj.setPlant_img3(feedback.getPlant_img3());
		feedbackObj.setPlant_img4(feedback.getPlant_img4());
		feedbackObj.setPlant_img5(feedback.getPlant_img5());
		feedbackObj.setDescription(feedback.getDescription());
		feedbackObj.setAudio_file_url(feedback.getAudio_file_url());
		feedbackObj.setStatus(feedback.getStatus());
		feedbackObj.setPayment_method(feedback.getPayment_method());
		feedbackObj.setChk_img_url(feedback.getChk_img_url());
		feedbackObj.setAction_on(new Date());

		try {
			int count = (int) getSession().save(feedbackObj);

			// -----For Updating Maintainer Allotment Table----------
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<MaintainerAllotment> criteria = builder.createCriteriaUpdate(MaintainerAllotment.class);
			Root<MaintainerAllotment> root = criteria.from(MaintainerAllotment.class);
			criteria.set(root.get("status"), feedback.getStatus());
			criteria.set(root.get("action_on"), new Date());
			criteria.where(builder.equal(root.get("id"), feedback.getMaintainers_allotment_id()));
			getSession().createQuery(criteria).executeUpdate();

			if (count != 0) {
				returnMsg.setStatus(true);
				returnMsg.setMsg("Successfully submitted.");

			} else {
				returnMsg.setStatus(false);
				returnMsg.setMsg("Not submitted successfully.");
			}

		} catch (Exception e) {
			// TODO: handle exception

			returnMsg.setStatus(false);
			returnMsg.setMsg("Not submitted successfully.");
			getSession().clear();
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg updateFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		try {
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<Feedback> criteria = builder.createCriteriaUpdate(Feedback.class);
			Root<Feedback> root = criteria.from(Feedback.class);
			criteria.set(root.get("plant_img1"), feedback.getPlant_img1());
			criteria.set(root.get("plant_img2"), feedback.getPlant_img2());
			criteria.set(root.get("plant_img3"), feedback.getPlant_img3());
			criteria.set(root.get("plant_img4"), feedback.getPlant_img4());
			criteria.set(root.get("plant_img5"), feedback.getPlant_img5());
			criteria.set(root.get("description"), feedback.getDescription());
			criteria.set(root.get("audio_file_url"), feedback.getAudio_file_url());
			criteria.set(root.get("status"), feedback.getStatus());
			criteria.set(root.get("payment_method"), feedback.getPayment_method());
			criteria.set(root.get("chk_img_url"), feedback.getChk_img_url());
			criteria.set(root.get("action_on"), new Date());
			criteria.where(builder.equal(root.get("id"), feedback.getId()));
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

}
