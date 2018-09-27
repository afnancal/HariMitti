package com.afnan.harimitti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String welcome() {
		return "Hi,<Br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HariMitti API is up and running...!<Br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Created by Mohammad Afnan Haseeb on 13-Feb-2018.";
	}
}
