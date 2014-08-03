package com.glenware.parkrunpb.dao;

import java.util.List;

import com.glenware.parkrunpb.form.PRCourse;
import com.glenware.parkrunpb.form.PRRegion;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ParkrunDAOImpl implements ParkrunDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addPRCourse(PRCourse prCourse) {
		sessionFactory.getCurrentSession().save(prCourse);
	}

	@Override
	public PRCourse getPRCourse(Integer id) {
		return (PRCourse) sessionFactory.getCurrentSession().get(
				PRCourse.class, id);
	}

	@Override
	public List<PRCourse> listPRCourse() {
		return sessionFactory.getCurrentSession()
				.createQuery("from PRCourse order by prName").list();
	}

	@Override
	public void removePRCourse(Integer id) {
		PRCourse prCourse = (PRCourse) sessionFactory.getCurrentSession().load(
				PRCourse.class, id);
		if (prCourse != null) {
			sessionFactory.getCurrentSession().delete(prCourse);
		}
	}

	@Override
	public PRRegion getPRRegion(Integer id) {
		return (PRRegion) sessionFactory.getCurrentSession().get(
				PRRegion.class, id);
	}

	@Override
	public List<PRRegion> listPRRegion() {
		return sessionFactory.getCurrentSession()
				.createQuery("from PRRegion order by regionName").list();
	}

}