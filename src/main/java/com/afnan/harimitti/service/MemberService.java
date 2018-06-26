package com.afnan.harimitti.service;

import java.util.List;

import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.Member;
import com.afnan.harimitti.model.ReturnMsg;

public interface MemberService {

	public List<Member> getListMember();

	public List<Member> findMemberByName(String name);

	public List<Member> findMemberById(String membership_id);

	public Login loginMember(Member member);

	public ReturnMsg createMember(Member member);

	public ReturnMsg memberExist(String contact_no);

	public ReturnMsg updateMemberPassword(Member member);

	public ReturnMsg updateMember(Member member);

	public ReturnMsg deleteMember(String membership_id);

}
