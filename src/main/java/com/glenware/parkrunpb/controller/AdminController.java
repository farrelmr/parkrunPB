package com.glenware.parkrunpb.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.glenware.parkrunpb.form.PRCourse;
import com.glenware.parkrunpb.form.PRRegion;
import com.glenware.parkrunpb.service.ParkrunService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("prRegionMap")
public class AdminController {
	
	@Autowired
	private ParkrunService parkrunService;	

	@RequestMapping("/admin")
	public String admin( Map<String, Object> map ) {
		map.put("prCourse", new PRCourse());
		List<PRCourse> prCourseList = parkrunService.listPRCourse();
	    map.put("prCourseList", prCourseList);
	    return "admin";
	}

	@ModelAttribute("prRegionMap")
	public Map <Integer, String> createPrRegionMap() {
	    List<PRRegion> prRegionList = parkrunService.listPRRegion();
		Map <Integer, String> prRegionMap = new LinkedHashMap<Integer, String> ();
		for ( PRRegion prRegion : prRegionList ) {
			prRegionMap.put(prRegion.getId(), prRegion.getRegionName());
		}
		return prRegionMap;
	}
	
	
	@RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
	public String addPRCourse(@Valid @ModelAttribute("prCourse") PRCourse prCourse,
			                  BindingResult result,
			                  Map<String, Object> map ) {
		
		if ( result.hasErrors() ) {
			List<PRCourse> prCourseList = parkrunService.listPRCourse();
		    map.put("prCourseList", prCourseList);
//		    return "admin";
		} else {
			prCourse.setPrregion( parkrunService.getPRRegion( prCourse.getRegionId() ) );
			parkrunService.addPRCourse(prCourse);
//			return "admin";
		}
		return "redirect:/admin";
	}

	@RequestMapping("/deleteAdmin/{prCourseId}")
	public String removePRCourse(@PathVariable("prCourseId") Integer prCourseId) {
		parkrunService.removePRCourse(prCourseId);
		return "redirect:/admin";
	}
	
}