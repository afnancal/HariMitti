package com.afnan.harimitti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afnan.harimitti.dao.FeedbackDao;
import com.afnan.harimitti.model.Feedback;
import com.afnan.harimitti.model.ReturnMsg;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

	FeedbackDao feedbackDao;

	@Autowired(required = true)
	public void setFeedbackDao(FeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}

	@Override
	public List<Feedback> searchFeedByMainMembIdDate(String maintainer_id, String member_id, String date) {
		// TODO Auto-generated method stub
		return feedbackDao.searchFeedByMainMembIdDate(maintainer_id, member_id, date);
	}

	@Override
	public List<Feedback> searchFeedbackByMaintainerId(String maintainer_id) {
		// TODO Auto-generated method stub
		return feedbackDao.searchFeedbackByMaintainerId(maintainer_id);
	}

	@Override
	public List<Feedback> searchFeedbackByMemberId(String member_id) {
		// TODO Auto-generated method stub
		return feedbackDao.searchFeedbackByMemberId(member_id);
	}

	@Override
	public ReturnMsg createFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return feedbackDao.createFeedback(feedback);
	}

	@Override
	public ReturnMsg updateFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return feedbackDao.updateFeedback(feedback);
	}

}
