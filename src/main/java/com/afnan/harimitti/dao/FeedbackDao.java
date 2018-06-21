package com.afnan.harimitti.dao;

import java.util.List;

import com.afnan.harimitti.model.Feedback;
import com.afnan.harimitti.model.ReturnMsg;

public interface FeedbackDao {

	public List<Feedback> searchFeedByMainMembIdDate(String maintainer_id, String member_id, String date);
	
	public List<Feedback> searchFeedbackByMaintainerId(String maintainer_id);

	public List<Feedback> searchFeedbackByMemberId(String member_id);

	public ReturnMsg createFeedback(Feedback feedback);

	public ReturnMsg updateFeedback(Feedback feedback);

}
