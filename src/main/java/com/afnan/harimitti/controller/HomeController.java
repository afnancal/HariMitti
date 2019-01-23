package com.afnan.harimitti.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	/*@GetMapping("/")
	public String welcome() {
		return "Hi,<Br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HariMitti API is up and running...!<Br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Created by Mohammad Afnan Haseeb on 13-Feb-2018.";
	}*/
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody Status welcome() {
		Status status = new Status();
		status.setStatus("Hi,      HariMitti API is up and running...!");
		status.setAuthor("Mohammad Afnan Haseeb");
		status.setDate("13-Feb-2018");
		//return "Hi,<Br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HariMitti API is up and running...!<Br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Created by Mohammad Afnan Haseeb on 13-Feb-2018.";
		return status;
	}
	
	public class Status {

		private String status;
		private String author;
		private String date;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		public String getAuthor() {
			return author;
		}
		
		public void setAuthor(String author) {
			this.author = author;
		}
		
		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

	}
	
}
