package com.afnan.harimitti.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.afnan.harimitti.helper.IndiaDateTime;
import com.afnan.harimitti.model.Maintainer;
import com.afnan.harimitti.model.MaintainerAllotment;
import com.afnan.harimitti.model.ReturnMsg;

@Repository
public class MaintainerAllotmentDaoImpl implements MaintainerAllotmentDao {

	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
	public final static String applicationID = "AIzaSyA5Iv7lDUZUtHNKiqZBGM4ykockZoUxjDI";
	private final static String senderId = "harimitti-operator";
	private static String title = "Maintainer Allotted";
	private static String message = "Created Allotment successfully.";
	private static String imageUrl = "";

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<MaintainerAllotment> getListMaintainerAllotment() {
		// TODO Auto-generated method stub
		// create Criteria
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MaintainerAllotment> criteriaQuery = criteriaBuilder.createQuery(MaintainerAllotment.class);
		criteriaQuery.from(MaintainerAllotment.class);

		return getSession().createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<MaintainerAllotment> findMaintainerAllotmentByMainId(String maintainer_id, String dateFrom,
			String dateTo) {
		// TODO Auto-generated method stub
		String startDate = dateFrom + " 00:00:00";
		String endDate = dateTo + " 23:59:59";

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MaintainerAllotment> criteriaQuery = criteriaBuilder.createQuery(MaintainerAllotment.class);
		Root<MaintainerAllotment> root = criteriaQuery.from(MaintainerAllotment.class);

		// This list will contain all Predicates (where clauses)
		List<Predicate> predicates = new ArrayList<Predicate>();

		Predicate predicate1 = criteriaBuilder.like(root.get("maintainer_id"), maintainer_id);
		predicates.add(predicate1);

		Predicate predicate2 = criteriaBuilder.greaterThanOrEqualTo(root.get("schedule"), startDate);
		predicates.add(predicate2);

		Predicate predicate3 = criteriaBuilder.lessThanOrEqualTo(root.get("schedule"), endDate);
		predicates.add(predicate3);

		// Pass the criteria list to the where method of criteria query
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		//criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("maintainer_id"), maintainer_id)));
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("schedule")));

		List<MaintainerAllotment> maintainerAllotments = getSession().createQuery(criteriaQuery).getResultList();

		return maintainerAllotments;
	}

	@Override
	public List<MaintainerAllotment> findTodaysMainAllotByMainId(String maintainer_id, String date) {
		// TODO Auto-generated method stub
		String startDate = (date + " 00:00:00");
		String endDate = (date + " 23:59:59");

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MaintainerAllotment> criteriaQuery = criteriaBuilder.createQuery(MaintainerAllotment.class);
		Root<MaintainerAllotment> root = criteriaQuery.from(MaintainerAllotment.class);

		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("maintainer_id"), maintainer_id)),
				criteriaBuilder.or(criteriaBuilder.greaterThanOrEqualTo(root.get("schedule"), startDate)),
				criteriaBuilder.or(criteriaBuilder.lessThanOrEqualTo(root.get("schedule"), endDate)));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("schedule")));

		List<MaintainerAllotment> maintainerAllotments = getSession().createQuery(criteriaQuery).getResultList();

		return maintainerAllotments;
	}

	@Override
	public List<MaintainerAllotment> findPreviousMainAllotByMainId(String maintainer_id, String date) {
		// TODO Auto-generated method stub
		Date d = IndiaDateTime.stringDateToDate(date + " 00:00:00"); // Initialize your date to any date
		int n = 7;
		Date dateBefore = new Date(d.getTime() - n * 24 * 3600 * 1000); // Subtract n days
		String startDate = IndiaDateTime.DateToStringDate(dateBefore);
		String endDate = date + " 00:00:00";

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MaintainerAllotment> criteriaQuery = criteriaBuilder.createQuery(MaintainerAllotment.class);
		Root<MaintainerAllotment> root = criteriaQuery.from(MaintainerAllotment.class);

		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("maintainer_id"), maintainer_id)),
				criteriaBuilder.or(criteriaBuilder.greaterThanOrEqualTo(root.get("schedule"), startDate)),
				criteriaBuilder.or(criteriaBuilder.lessThanOrEqualTo(root.get("schedule"), endDate)));
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("schedule")));

		int limit = 10; // Set limit gives only 10 data
		List<MaintainerAllotment> maintainerAllotments = getSession().createQuery(criteriaQuery).setMaxResults(limit)
				.getResultList();

		return maintainerAllotments;
	}

	@Override
	public List<MaintainerAllotment> findComingMainAllotByMainId(String maintainer_id, String date) {
		// TODO Auto-generated method stub
		Date d = IndiaDateTime.stringDateToDate(date + " 23:59:59"); // Initialize your date to any date
		int n = 7;
		Date dateAfter = new Date(d.getTime() + n * 24 * 3600 * 1000); // Subtract n days
		String endDate = IndiaDateTime.DateToStringDate(dateAfter);
		String startDate = (date + " 23:59:59");

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MaintainerAllotment> criteriaQuery = criteriaBuilder.createQuery(MaintainerAllotment.class);
		Root<MaintainerAllotment> root = criteriaQuery.from(MaintainerAllotment.class);

		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("maintainer_id"), maintainer_id)),
				criteriaBuilder.or(criteriaBuilder.greaterThanOrEqualTo(root.get("schedule"), startDate)),
				criteriaBuilder.or(criteriaBuilder.lessThanOrEqualTo(root.get("schedule"), endDate)));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("schedule")));

		int limit = 10; // Set limit gives only 10 data
		List<MaintainerAllotment> maintainerAllotments = getSession().createQuery(criteriaQuery).setMaxResults(limit)
				.getResultList();

		return maintainerAllotments;
	}

	@Override
	public List<MaintainerAllotment> findMaintainerAllotmentByMembId(String member_id) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<MaintainerAllotment> criteriaQuery = criteriaBuilder.createQuery(MaintainerAllotment.class);
		Root<MaintainerAllotment> root = criteriaQuery.from(MaintainerAllotment.class);

		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("membership_id"), member_id)));
		List<MaintainerAllotment> maintainerLocation = getSession().createQuery(criteriaQuery).getResultList();

		return maintainerLocation;
	}

	@Override
	public ReturnMsg createMaintainerAllotment(MaintainerAllotment maintainerAllotment) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		// For getting Maintainer Allotment gcm_reg
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
		Root<Maintainer> root = criteriaQuery.from(Maintainer.class);

		criteriaQuery.multiselect(root.get("gcm_reg"));
		criteriaQuery.where(criteriaBuilder
				.or(criteriaBuilder.like(root.get("maintainer_id"), maintainerAllotment.getMaintainer_id())));
		String gcm_reg = getSession().createQuery(criteriaQuery).getSingleResult();

		MaintainerAllotment maintainerAllotmentObj = new MaintainerAllotment();
		maintainerAllotmentObj.setMaintainer_id(maintainerAllotment.getMaintainer_id());
		maintainerAllotmentObj.setMembership_id(maintainerAllotment.getMembership_id());
		maintainerAllotmentObj.setStatus(maintainerAllotment.getStatus());
		maintainerAllotmentObj.setSchedule(maintainerAllotment.getSchedule());
		maintainerAllotmentObj.setAction_on(IndiaDateTime.getUTCdatetimeAsDate());

		try {
			int count = (int) getSession().save(maintainerAllotmentObj);
			if (count != 0) {
				returnMsg.setStatus(true);
				returnMsg.setMsg("Successfully allotted.");
				sendPushNotification(gcm_reg);

			} else {
				returnMsg.setStatus(false);
				returnMsg.setMsg("Not allotted successfully.");
			}

		} catch (Exception e) {
			// TODO: handle exception

			returnMsg.setStatus(false);
			returnMsg.setMsg("Not allotted successfully.");
			getSession().clear();
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg updateMaintainerAllotment(MaintainerAllotment maintainerAllotment) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		try {
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaUpdate<MaintainerAllotment> criteria = builder.createCriteriaUpdate(MaintainerAllotment.class);
			Root<MaintainerAllotment> root = criteria.from(MaintainerAllotment.class);
			criteria.set(root.get("maintainer_id"), maintainerAllotment.getMaintainer_id());
			criteria.set(root.get("membership_id"), maintainerAllotment.getMembership_id());
			criteria.set(root.get("status"), maintainerAllotment.getStatus());
			criteria.set(root.get("schedule"), maintainerAllotment.getSchedule());
			criteria.set(root.get("action_on"), IndiaDateTime.getUTCdatetimeAsDate());
			criteria.where(builder.equal(root.get("id"), maintainerAllotment.getId()));
			getSession().createQuery(criteria).executeUpdate();

			returnMsg.setStatus(true);
			returnMsg.setMsg("Updated successfully.");

		} catch (Exception e) {
			// TODO: handle exception

			returnMsg.setStatus(false);
			returnMsg.setMsg("Not updated successfully.");
			getSession().clear();
		}

		return returnMsg;
	}

	@Override
	public ReturnMsg maintainerAllotmentExist(String maintainer_id) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<MaintainerAllotment> root = criteriaQuery.from(MaintainerAllotment.class);

		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(MaintainerAllotment.class)));
		criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.like(root.get("maintainer_id"), maintainer_id)));
		long countL = getSession().createQuery(criteriaQuery).getSingleResult();

		if (countL != 0) {
			returnMsg.setStatus(true);
			returnMsg.setMsg("Maintainer existed.");

		} else {
			returnMsg.setStatus(false);
			returnMsg.setMsg("Maintainer not existed.");

		}

		return returnMsg;
	}

	// HTTP Post request
	@SuppressWarnings("unchecked")
	public static String sendPushNotification(String deviceToken) throws IOException {

		String result = "";
		URL url = new URL(API_URL_FCM);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + applicationID);
		conn.setRequestProperty("Sender", "id=" + senderId);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject json = new JSONObject();

		JSONObject info = new JSONObject();
		info.put("\"timestamp\"", "\"" + IndiaDateTime.getUTCdatetimeAsString() + "\"");
		info.put("\"title\"", "\"" + title + "\""); // Notification title
		info.put("\"message\"", "\"" + message + "\""); // Notification
		info.put("\"image\"", "\"" + imageUrl + "\"");
		JSONObject jsonPayload = new JSONObject();
		info.put("\"payload\"", jsonPayload);
		// body
		json.put("data", info);
		json.put("to", deviceToken.trim());
		/*
		 * json.put("priority", "high"); json.put("sound", "enabled");
		 */
		System.out.println(json.toJSONString());
		try {
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			result = "OK";
		} catch (Exception e) {
			e.printStackTrace();
			result = "BAD";
		}
		System.out.println("GCM Notification is sent successfully");

		return result;

	}

}
