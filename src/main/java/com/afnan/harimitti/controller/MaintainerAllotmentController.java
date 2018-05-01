package com.afnan.harimitti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.afnan.harimitti.model.MaintainerAllotment;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.service.MaintainerAllotmentService;

@RestController
public class MaintainerAllotmentController {

	@Autowired
	MaintainerAllotmentService maintainerAllotmentService;

	// -------------------Search by Maintainer Id------------------------------
	@RequestMapping(value = "/searchMaintainerAllotmentMain/{maintainer_id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<MaintainerAllotment> search(@PathVariable("maintainer_id") String maintainer_id) {
		List<MaintainerAllotment> maintainerAllotments = maintainerAllotmentService
				.findMaintainerAllotmentByMainId(maintainer_id);

		return maintainerAllotments;
	}

	// -------------------Search by Membership Id------------------------------
	@RequestMapping(value = "/searchMaintainerAllotmentMemb/{member_id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<MaintainerAllotment> findMaintainerAllotmentByMembId(
			@PathVariable("member_id") String member_id) {
		List<MaintainerAllotment> maintainerAllotments = maintainerAllotmentService
				.findMaintainerAllotmentByMembId(member_id);

		return maintainerAllotments;
	}

	// -------------------Create a Maintainer Allotment------------------------
	@RequestMapping(value = "/maintainerAllotment", method = RequestMethod.POST)
	public @ResponseBody ReturnMsg createMaintainerAllotment(@RequestBody MaintainerAllotment maintainerAllotment) {

		return maintainerAllotmentService.createMaintainerAllotment(maintainerAllotment);
	}
	
	// ------------------- Update a User -----------------------------------
		@RequestMapping(value = "/updateMaintainerAllotment/{id}", method = RequestMethod.PUT)
		public @ResponseBody ReturnMsg update(@PathVariable("id") int id, @RequestBody MaintainerAllotment maintainerAllotment) {
			maintainerAllotment.setId(id);

			return maintainerAllotmentService.updateMaintainerAllotment(maintainerAllotment);
		}

	// -------------------validate a Maintainer Allotment is exist----------------
	@RequestMapping(value = "/maintainerAllotmentExist/{maintainer_id}", method = RequestMethod.GET)
	public @ResponseBody ReturnMsg maintainerAllotmentExist(@PathVariable("maintainer_id") String maintainer_id) {

		return maintainerAllotmentService.maintainerAllotmentExist(maintainer_id);
	}

}
