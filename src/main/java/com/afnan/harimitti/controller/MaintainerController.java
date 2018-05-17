package com.afnan.harimitti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.Maintainer;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.service.MaintainerService;

@RestController
public class MaintainerController {

	@Autowired
	MaintainerService maintainerService;

	// -------------------Retrieve All Maintainers-------------------------------
	@RequestMapping(value = "/maintainerList", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Maintainer> getListMaintainer() {
		List<Maintainer> maintainers = maintainerService.getListMaintainer();

		return maintainers;
	}

	// -------------------Find Maintainer by name------------------------------
	@RequestMapping(value = "/searchMaintainer/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Maintainer> search(@PathVariable("name") String name) {
		List<Maintainer> maintainers = maintainerService.findMaintainerByName(name);

		return maintainers;
	}

	// -------------------Find Maintainer by Id------------------------------
	@RequestMapping(value = "/searchMaintainerById/{maintainer_id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Maintainer> findMaintainerById(@PathVariable("maintainer_id") String maintainer_id) {
		List<Maintainer> maintainers = maintainerService.findMaintainerById(maintainer_id);

		return maintainers;
	}

	// -------------------Login a Maintainer------------------------------------
	@RequestMapping(value = "/loginMaintainer", method = RequestMethod.POST)
	public @ResponseBody Login loginMaintainer(@RequestBody Maintainer maintainer) {

		return maintainerService.loginMaintainer(maintainer);
	}

	// -------------------Create a Maintainer------------------------------------
	@RequestMapping(value = "/maintainer", method = RequestMethod.POST)
	public @ResponseBody ReturnMsg createMaintainer(@RequestBody Maintainer maintainer) {

		return maintainerService.createMaintainer(maintainer);
	}

	// -------------------validate a Maintainer is exist---------------------------
	@RequestMapping(value = "/maintainerExist/{contact_no}", method = RequestMethod.GET)
	public @ResponseBody ReturnMsg maintainerExist(@PathVariable("contact_no") String contact_no) {

		return maintainerService.maintainerExist(contact_no);
	}

	// ------------------- Forget password of a Maintainer -----------------------
	@RequestMapping(value = "/updateMaintainerPassword", method = RequestMethod.PUT)
	public @ResponseBody ReturnMsg updateMaintainerPassword(@RequestBody Maintainer maintainer) {

		return maintainerService.updateMaintainerPassword(maintainer);
	}

	// ------------------- Update a Maintainer -----------------------------------
	@RequestMapping(value = "/updateMaintainer/{maintainer_id}", method = RequestMethod.PUT)
	public @ResponseBody ReturnMsg update(@PathVariable("maintainer_id") String maintainer_id,
			@RequestBody Maintainer maintainer) {
		maintainer.setMaintainer_id(maintainer_id);

		return maintainerService.updateMaintainer(maintainer);
	}

	// ------------------- Delete a Maintainer ---------------------------------
	@RequestMapping(value = "/deleteMaintainer/{maintainer_id}", method = RequestMethod.DELETE)
	public @ResponseBody ReturnMsg delete(@PathVariable("maintainer_id") String maintainer_id) {

		return maintainerService.deleteMaintainer(maintainer_id);
	}

}
