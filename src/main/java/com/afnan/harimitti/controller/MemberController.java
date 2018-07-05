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
import com.afnan.harimitti.model.Member;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService memberService;

	// -------------------Retrieve All Members-------------------------------
	@RequestMapping(value = "/memberList", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Member> getListMember() {
		List<Member> members = memberService.getListMember();

		return members;
	}

	// -------------------Find Member by name------------------------------
	@RequestMapping(value = "/searchMemberByName/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Member> search(@PathVariable("name") String name) {
		List<Member> members = memberService.findMemberByName(name);

		return members;
	}

	// -------------------Find Member by Id------------------------------
	@RequestMapping(value = "/searchMemberById/{membership_id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Member> findMemberById(@PathVariable("membership_id") String membership_id) {
		List<Member> members = memberService.findMemberById(membership_id);

		return members;
	}

	// -------------------Login a Member------------------------------------
	@RequestMapping(value = "/loginMember", method = RequestMethod.POST)
	public @ResponseBody Login loginMember(@RequestBody Member member) {

		return memberService.loginMember(member);
	}

	// -------------------Create a Member------------------------------------
	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public @ResponseBody ReturnMsg add(@RequestBody Member member) {

		return memberService.createMember(member);
	}

	// -------------------validate a Member is exist---------------------------
	@RequestMapping(value = "/memberExist/{contact_no}", method = RequestMethod.GET)
	public @ResponseBody ReturnMsg memberExist(@PathVariable("contact_no") String contact_no) {

		return memberService.memberExist(contact_no);
	}

	// ------------------- Forget password of a Member -----------------------
	@RequestMapping(value = "/updateMemberPassword", method = RequestMethod.PUT)
	public @ResponseBody ReturnMsg updateMemberPassword(@RequestBody Member member) {

		return memberService.updateMemberPassword(member);
	}

	// ------------------- Update a Member -----------------------------------
	@RequestMapping(value = "/updateMember/{membership_id}", method = RequestMethod.PUT)
	public @ResponseBody ReturnMsg update(@PathVariable("membership_id") String membership_id, @RequestBody Member member) {
		member.setMembership_id(membership_id);

		return memberService.updateMember(member);
	}

	// ------------------- Delete a Member ---------------------------------
	@RequestMapping(value = "/deleteMember/{membership_id}", method = RequestMethod.DELETE)
	public @ResponseBody ReturnMsg delete(@PathVariable("membership_id") String membership_id) {

		return memberService.deleteMember(membership_id);
	}

}
