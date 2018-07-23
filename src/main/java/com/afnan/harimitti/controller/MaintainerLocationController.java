package com.afnan.harimitti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.afnan.harimitti.model.MaintainerLocation;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.service.MaintainerLocationService;

@RestController
public class MaintainerLocationController {

	@Autowired
	MaintainerLocationService maintainerLocationService;

	// -------------------Search by Maintainer Id------------------------------
	@RequestMapping(value = "/searchMaintainerLocation/{maintainer_id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<MaintainerLocation> search(@PathVariable("maintainer_id") String maintainer_id) {
		List<MaintainerLocation> maintainerLocations = maintainerLocationService
				.findMaintainerLocationById(maintainer_id);

		return maintainerLocations;
	}

	// -------------------Create a MaintainerLocation------------------------
	@RequestMapping(value = "/maintainerLocation", method = RequestMethod.POST)
	public @ResponseBody ReturnMsg createMaintainerLocation(@RequestBody MaintainerLocation maintainerLocation) {

		return maintainerLocationService.createMaintainerLocation(maintainerLocation);
	}

	// -------------------validate a MaintainerLocation is exist----------------
	@RequestMapping(value = "/maintainerLocationExist/{maintainer_id}", method = RequestMethod.GET)
	public @ResponseBody ReturnMsg maintainerLocationExist(@PathVariable("maintainer_id") String maintainer_id) {

		return maintainerLocationService.maintainerLocationExist(maintainer_id);
	}

	// ------------------- Update a MaintainerLocation -----------------------
	@RequestMapping(value = "/updateMaintainerLocation/{date}", method = RequestMethod.PUT)
	public @ResponseBody ReturnMsg updateMaintainerLocation(@PathVariable("date") String date, @RequestBody MaintainerLocation maintainerLocation) {

		return maintainerLocationService.updateMaintainerLocation(date, maintainerLocation);
	}

	// ------------------- Delete a MaintainerLocation --------------------------
	@RequestMapping(value = "/deleteMaintainerLocation/{maintainer_id}", method = RequestMethod.DELETE)
	public @ResponseBody ReturnMsg deleteMaintainerLocation(@PathVariable("maintainer_id") String maintainer_id) {

		return maintainerLocationService.deleteMaintainerLocation(maintainer_id);
	}

}
