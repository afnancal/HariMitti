package com.afnan.harimitti.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<User> getListUser() {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(User.class);

		return (List<User>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<User> findUserByName(String name) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));

		return (List<User>) criteria.list();
	}

	public Login login(String contact_no, String password) {

		Login login = new Login();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<User> root = criteriaQuery.from(User.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(User.class)));
		criteriaQuery.where(criteriaBuilder.or(criteriaBuilder.like(root.get("contact_no"), contact_no)),
				criteriaBuilder.or(criteriaBuilder.like(root.get("password"), password)));
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			CriteriaBuilder criteriaBuilder1 = getSession().getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery1 = criteriaBuilder1.createQuery(User.class);
			Root<User> root1 = criteriaQuery1.from(User.class);

			criteriaQuery1.where(criteriaBuilder1.or(criteriaBuilder1.like(root1.get("contact_no"), contact_no)),
					criteriaBuilder1.or(criteriaBuilder1.like(root1.get("password"), password)));
			User user = getSession().createQuery(criteriaQuery1).getSingleResult();

			login.setStatus(true);
			login.setMsg("Login successful.");
			login.setUser_id(user.getUser_id());
			login.setName(user.getName());
			login.setImg_url(user.getImg_url());

		} else {
			login.setStatus(false);
			login.setMsg("Invalid username password.");

		}

		return login;

	}

	public ReturnMsg createUser(User user) {

		ReturnMsg returnMsg = new ReturnMsg();

		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("contact_no", user.getContact_no()));
		criteria.setProjection(Projections.rowCount());
		long countL = (Long) criteria.uniqueResult();
		if (countL != 0) {
			// System.out.println("present");
			returnMsg.setStatus(false);
			returnMsg.setMsg("User already existed, please login.");

		} else {
			// System.out.println("absent");
			User userObj = new User();
			userObj.setUser_id(convertTimestamp());
			userObj.setName(user.getName());
			userObj.setAddress(user.getAddress());
			userObj.setContact_no(user.getContact_no());
			userObj.setEmail(user.getEmail());
			userObj.setPassword(user.getPassword());
			userObj.setGcm_reg(user.getGcm_reg());
			userObj.setImg_url(user.getImg_url());
			userObj.setAction_on(new Date());

			// getSession().save(userObj);
			try {
				String count = (String) getSession().save(userObj);
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

	public ReturnMsg userExist(String contact_no) {

		ReturnMsg returnMsg = new ReturnMsg();

		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("contact_no", contact_no));
		criteria.setProjection(Projections.rowCount());

		long countL = (Long) criteria.uniqueResult();
		if (countL != 0) {
			returnMsg.setStatus(true);
			returnMsg.setMsg("User existed.");

		} else {
			returnMsg.setStatus(false);
			returnMsg.setMsg("User not existed.");

		}

		return returnMsg;
	}

	public ReturnMsg updatePassword(User user) {

		ReturnMsg returnMsg = new ReturnMsg();

		try {
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<User> criteria = builder.createCriteriaUpdate(User.class);
			Root<User> root = criteria.from(User.class);
			criteria.set(root.get("password"), user.getPassword());
			criteria.where(builder.equal(root.get("contact_no"), user.getContact_no()));
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

	public ReturnMsg updateUser(User user) {

		ReturnMsg returnMsg = new ReturnMsg();

		try {
			User userObj = new User();
			userObj.setUser_id(user.getUser_id());
			userObj.setName(user.getName());
			userObj.setAddress(user.getAddress());
			userObj.setContact_no(user.getContact_no());
			userObj.setEmail(user.getEmail());
			userObj.setPassword(user.getPassword());
			userObj.setAction_on(new Date());

			getSession().update(userObj);
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

	public ReturnMsg deleteUser(String user_id) {

		ReturnMsg returnMsg = new ReturnMsg();

		User user = (User) getSession().get(User.class, user_id);
		getSession().delete(user);

		returnMsg.setStatus(true);
		returnMsg.setMsg("Deleted successfully.");

		return returnMsg;
	}

	// -------------------For creating User Id--------------------------------------
	private String convertTimestamp() {

		String newstring = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmssSSSSSSSSS");
		Date date = new Date();
		newstring = "U" + formatter.format(date);

		return newstring;
	}

}
