package com.afnan.harimitti.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.afnan.harimitti.helper.IndiaDateTime;
import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.Member;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.model.User;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Member> getListMember() {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		// create Criteria
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		Root<Member> root = criteriaQuery.from(Member.class);
		criteriaQuery.orderBy(getSession().getCriteriaBuilder().asc(root.get("membership_id")));

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Member> findMemberByName(String name) {
		// TODO Auto-generated method stub

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		EntityType<Member> type = getSession().getMetamodel().entity(Member.class);
		Root<Member> root = criteriaQuery.from(Member.class);

		criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.like(
				criteriaBuilder.lower(root.get(type.getDeclaredSingularAttribute("name", String.class))),
				"%" + name.toLowerCase() + "%")));

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Member> findMemberById(String membership_id) {
		// TODO Auto-generated method stub

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		EntityType<Member> type = getSession().getMetamodel().entity(Member.class);
		Root<Member> root = criteriaQuery.from(Member.class);

		criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.like(
				criteriaBuilder.lower(root.get(type.getDeclaredSingularAttribute("membership_id", String.class))),
				"%" + membership_id.toLowerCase() + "%")));

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Login loginMember(Member member) {

		Login login = new Login();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Member> root = criteriaQuery.from(Member.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Member.class)));
		criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.like(root.get("contact_no"), member.getContact_no())),
				criteriaBuilder.or(criteriaBuilder.like(root.get("password"), member.getPassword())));
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			// For inserting GCM_reg value into table
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<Member> criteriaUpdate = builder.createCriteriaUpdate(Member.class);
			Root<Member> rootUpdate = criteriaUpdate.from(Member.class);
			criteriaUpdate.set(rootUpdate.get("gcm_reg"), member.getGcm_reg());
			criteriaUpdate.where(builder.equal(rootUpdate.get("contact_no"), member.getContact_no()));
			getSession().createQuery(criteriaUpdate).executeUpdate();

			// For getting Login member details
			CriteriaBuilder criteriaBuilder1 = getSession().getCriteriaBuilder();
			CriteriaQuery<Member> criteriaQuery1 = criteriaBuilder1.createQuery(Member.class);
			Root<Member> root1 = criteriaQuery1.from(Member.class);

			criteriaQuery1.where(
					criteriaBuilder1.or(criteriaBuilder1.like(root1.get("contact_no"), member.getContact_no())),
					criteriaBuilder1.or(criteriaBuilder1.like(root1.get("password"), member.getPassword())));
			Member memberGet = getSession().createQuery(criteriaQuery1).getSingleResult();

			login.setStatus(true);
			login.setMsg("Login successful as a Membership.");
			login.setUser_id(memberGet.getMembership_id());
			login.setName(memberGet.getName());
			login.setImg_url(memberGet.getImg_url());
			login.setUsertype("Membership");

		} else {
			/*
			 * login.setStatus(false); login.setMsg("Invalid username password.");
			 */
			login = userLogin(member.getContact_no(), member.getPassword(), member.getGcm_reg());

		}

		return login;

	}

	// For User Login
	private Login userLogin(String contact_no, String password, String gcm_reg) {
		Login login = new Login();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<User> root = criteriaQuery.from(User.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(User.class)));
		criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.like(root.get("contact_no"), contact_no)),
				criteriaBuilder.or(criteriaBuilder.like(root.get("password"), password)));
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			// For inserting GCM_reg value into table
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<User> criteriaUpdate = builder.createCriteriaUpdate(User.class);
			Root<User> rootUpdate = criteriaUpdate.from(User.class);
			criteriaUpdate.set(rootUpdate.get("gcm_reg"), gcm_reg);
			criteriaUpdate.where(builder.equal(rootUpdate.get("contact_no"), contact_no));
			getSession().createQuery(criteriaUpdate).executeUpdate();

			// For getting Login member details
			CriteriaBuilder criteriaBuilder1 = getSession().getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery1 = criteriaBuilder1.createQuery(User.class);
			Root<User> root1 = criteriaQuery1.from(User.class);

			criteriaQuery1.where(criteriaBuilder1.or(criteriaBuilder1.like(root1.get("contact_no"), contact_no)),
					criteriaBuilder1.or(criteriaBuilder1.like(root1.get("password"), password)));
			User userGet = getSession().createQuery(criteriaQuery1).getSingleResult();

			login.setStatus(true);
			login.setMsg("Login successful as a User.");
			login.setUser_id(userGet.getUser_id());
			login.setName(userGet.getName());
			login.setImg_url(userGet.getImg_url());
			login.setUsertype("User");

		} else {
			login.setStatus(false);
			login.setMsg("Invalid username password.");

		}

		return login;
	}

	@Override
	public ReturnMsg createMember(Member member) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Member> root = criteriaQuery.from(Member.class);
		Predicate cond = null;

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Member.class)));
		// If both columns should match the test values
		cond = criteriaBuilder.and(criteriaBuilder.equal(root.get("membership_id"), member.getMembership_id()),
				criteriaBuilder.equal(root.get("contact_no"), member.getContact_no()));

		// If only one of the 2 columns should match the test values
		cond = criteriaBuilder.or(criteriaBuilder.equal(root.get("membership_id"), member.getMembership_id()),
				criteriaBuilder.equal(root.get("contact_no"), member.getContact_no()));

		criteriaQuery.where(cond);
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			// System.out.println("present");
			returnMsg.setStatus(false);
			returnMsg.setMsg("Member already existed, please login.");

		} else {
			// System.out.println("absent");
			Member memberObj = new Member();
			memberObj.setMembership_id(member.getMembership_id());
			memberObj.setName(member.getName());
			memberObj.setAddress(member.getAddress());
			memberObj.setContact_no(member.getContact_no());
			memberObj.setEmail(member.getEmail());
			memberObj.setPassword(member.getPassword());
			memberObj.setGcm_reg(member.getGcm_reg());
			memberObj.setImg_url(member.getImg_url());
			memberObj.setAction_on(IndiaDateTime.getUTCdatetimeAsDate());

			try {
				String count = (String) getSession().save(memberObj);
				if (!count.equals(null)) {
					returnMsg.setStatus(true);
					returnMsg.setMsg("Successfully created.");

				} else {
					returnMsg.setStatus(false);
					returnMsg.setMsg("Not created successfully.");
				}

			} catch (Exception e) {
				// TODO: handle exception

				returnMsg.setStatus(false);
				returnMsg.setMsg("Not created successfully.");
				getSession().clear();
			}
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg memberExist(String contact_no) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Member> root = criteriaQuery.from(Member.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Member.class)));
		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.like(root.get("contact_no"), contact_no)));
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			returnMsg.setStatus(true);
			returnMsg.setMsg("Member existed.");

		} else {
			returnMsg.setStatus(false);
			returnMsg.setMsg("Member not existed.");

		}

		return returnMsg;
	}

	@Override
	public ReturnMsg updateMemberPassword(Member member) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		try {
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<Member> criteria = builder.createCriteriaUpdate(Member.class);
			Root<Member> root = criteria.from(Member.class);
			criteria.set(root.get("password"), member.getPassword());
			criteria.where(builder.equal(root.get("contact_no"), member.getContact_no()));
			getSession().createQuery(criteria).executeUpdate();

			returnMsg.setStatus(true);
			returnMsg.setMsg("Password reset successfully.");

		} catch (Exception e) {
			// TODO: handle exception

			returnMsg.setStatus(false);
			returnMsg.setMsg("Not reset successfully.");
			getSession().clear();
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg updateMember(Member member) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		try {
			Member memberObj = new Member();
			memberObj.setMembership_id(member.getMembership_id());
			memberObj.setName(member.getName());
			memberObj.setAddress(member.getAddress());
			memberObj.setContact_no(member.getContact_no());
			memberObj.setEmail(member.getEmail());
			memberObj.setPassword(member.getPassword());
			memberObj.setGcm_reg(member.getGcm_reg());
			memberObj.setImg_url(member.getImg_url());
			memberObj.setAction_on(IndiaDateTime.getUTCdatetimeAsDate());

			getSession().update(memberObj);
			returnMsg.setStatus(true);
			returnMsg.setMsg("Successfully updated.");

		} catch (Exception e) {
			// TODO: handle exception

			returnMsg.setStatus(false);
			returnMsg.setMsg("Not updated successfully.");
			getSession().clear();
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg deleteMember(String membership_id) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		Member member = (Member) getSession().get(Member.class, membership_id);
		getSession().delete(member);

		returnMsg.setStatus(true);
		returnMsg.setMsg("Deleted successfully.");

		return returnMsg;
	}

}
