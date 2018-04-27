package com.afnan.harimitti.dao;

import java.util.List;

import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.Member;
import com.afnan.harimitti.model.ReturnMsg;

public interface MemberDao {

	public List<Member> getListMember();

	public List<Member> findMemberByName(String name);

	public Login loginMember(Member member);

	public ReturnMsg createMember(Member member);

	public ReturnMsg memberExist(String contact_no);

	public ReturnMsg updateMemberPassword(Member member);

	public ReturnMsg updateMember(Member member);

	public ReturnMsg deleteMember(String member_id);

}