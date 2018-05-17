package com.afnan.harimitti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.afnan.harimitti.model.Feedback;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.service.FeedbackService;

@RestController
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;

	// -------------------Search Feedback by Maintainer Id----------------
	@RequestMapping(value = "/searchFeedbackByMaintainerId/{maintainer_id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Feedback> searchFeedbackByMaintainerId(
			@PathVariable("maintainer_id") String maintainer_id) {
		List<Feedback> feedbacks = feedbackService.searchFeedbackByMaintainerId(maintainer_id);

		return feedbacks;
	}

	// -------------------Search Feedback by Member Id----------------------
	@RequestMapping(value = "/searchFeedbackByMemberId/{member_id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Feedback> searchFeedbackByMemberId(@PathVariable("member_id") String member_id) {
		List<Feedback> feedbacks = feedbackService.searchFeedbackByMemberId(member_id);

		return feedbacks;
	}

	// -------------------Create a Feedback------------------------------------
	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	public @ResponseBody ReturnMsg createFeedback(@RequestBody Feedback feedback) {

		return feedbackService.createFeedback(feedback);
	}

	// ------------------- Update a Feedback-----------------------
	@RequestMapping(value = "/updateFeedback/{id}", method = RequestMethod.PUT)
	public @ResponseBody ReturnMsg update(@PathVariable("id") int id, @RequestBody Feedback feedback) {
		feedback.setId(id);

		return feedbackService.updateFeedback(feedback);
	}

}
