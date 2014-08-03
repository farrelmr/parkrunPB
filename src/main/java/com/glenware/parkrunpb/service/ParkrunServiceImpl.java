package com.glenware.parkrunpb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenware.parkrunpb.dao.ParkrunDAO;
import com.glenware.parkrunpb.form.PRCourse;
import com.glenware.parkrunpb.form.PRRegion;

@Service
public class ParkrunServiceImpl implements ParkrunService {

	@Autowired
	private ParkrunDAO parkrunDAO;

	@Override
	public void addPRCourse(PRCourse prCourse) {
		parkrunDAO.addPRCourse(prCourse);
	}

	@Override
	public PRCourse getPRCourse(Integer id) {
		return parkrunDAO.getPRCourse(id);
	}

	@Override
	public List<PRCourse> listPRCourse() {
		return parkrunDAO.listPRCourse();
	}

	@Override
	public void removePRCourse(Integer id) {
		parkrunDAO.removePRCourse(id);
	}

	@Override
	public PRRegion getPRRegion(Integer id) {
		return parkrunDAO.getPRRegion(id);
	}

	@Override
	public List<PRRegion> listPRRegion() {
		return parkrunDAO.listPRRegion();
	}

}