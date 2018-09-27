package com.afnan.harimitti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String welcome() {
		return "Hi, HariMitti API is up and running...!\nCreated by Mohammad Afnan Haseeb on 13-Feb-2018.";
	}
}
