package com.afnan.harimitti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afnan.harimitti.dao.MemberDao;
import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.Member;
import com.afnan.harimitti.model.ReturnMsg;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	MemberDao memberDao;

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public List<Member> getListMember() {
		// TODO Auto-generated method stub
		return memberDao.getListMember();
	}

	@Override
	public List<Member> findMemberByName(String name) {
		// TODO Auto-generated method stub
		return memberDao.findMemberByName(name);
	}

	@Override
	public Login loginMember(Member member) {
		// TODO Auto-generated method stub
		return memberDao.loginMember(member);
	}

	@Override
	public ReturnMsg createMember(Member member) {
		// TODO Auto-generated method stub
		return memberDao.createMember(member);
	}

	@Override
	public ReturnMsg memberExist(String contact_no) {
		// TODO Auto-generated method stub
		return memberDao.memberExist(contact_no);
	}

	@Override
	public ReturnMsg updateMemberPassword(Member member) {
		// TODO Auto-generated method stub
		return memberDao.updateMemberPassword(member);
	}

	@Override
	public ReturnMsg updateMember(Member member) {
		// TODO Auto-generated method stub
		return memberDao.updateMember(member);
	}

	@Override
	public ReturnMsg deleteMember(String member_id) {
		// TODO Auto-generated method stub
		return memberDao.deleteMember(member_id);
	}

}
