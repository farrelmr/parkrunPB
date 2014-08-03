package com.glenware.parkrunpb.service;

import java.util.List;

import com.glenware.parkrunpb.form.PRCourse;
import com.glenware.parkrunpb.form.PRRegion;

public interface ParkrunService {
	public void addPRCourse(PRCourse prCourse);

	public PRCourse getPRCourse(Integer id);

	public List<PRCourse> listPRCourse();

	public void removePRCourse(Integer id);

	public PRRegion getPRRegion(Integer id);

	public List<PRRegion> listPRRegion();
}
