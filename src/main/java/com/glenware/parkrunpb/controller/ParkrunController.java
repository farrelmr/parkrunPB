package com.glenware.parkrunpb.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.glenware.parkrunpb.form.PRCourse;
import com.glenware.parkrunpb.form.PRPredict;
import com.glenware.parkrunpb.form.PRRegionValidator;
import com.glenware.parkrunpb.service.ParkrunService;

@Controller
@SessionAttributes("prCourseMap")
public class ParkrunController {

	@Autowired
	private ParkrunService parkrunService;

	@InitBinder("prPredict")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new PRRegionValidator());
	}

	@RequestMapping(value = { "/", "/index" })
	public String listPRCourses(Model model) {
		resetPage(model);
		return "prCourse";
	}

	@RequestMapping(value = "/predict", method = RequestMethod.POST)
	public String predictPRCourse(
			@Valid @ModelAttribute("prPredict") PRPredict prPredict,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			resetPage(model);
			return "prCourse";
		} else {
			List<PRCourse> prCourseList = parkrunService.listPRCourse();
			model.addAttribute("prCourseList", prCourseList);

			prPredict.setPrCourse(parkrunService.getPRCourse(prPredict
					.getCourseId()));

			List<PRCourse> prCoursePredictList = new ArrayList<PRCourse>();
			for (PRCourse prCourse : prCourseList) {
				prCourse.setPrPredict(prPredict);
				prCoursePredictList.add(prCourse);
			}

			model.addAttribute("prCourseList", prCoursePredictList);
			model.addAttribute("prPredictBoolean", true);

			return "prCourse";
		}
	}

	@ModelAttribute("prCourseMap")
	public Map<Integer, String> createPrCourseMap() {
		List<PRCourse> prCourseList = parkrunService.listPRCourse();
		Map<Integer, String> prCourseMap = new LinkedHashMap<Integer, String>();
		for (PRCourse prCourse : prCourseList) {
			prCourseMap.put(prCourse.getId(), prCourse.getPrName());
		}
		return prCourseMap;
	}

	private void resetPage(Model model) {
		List<PRCourse> prCourseList = parkrunService.listPRCourse();
		model.addAttribute("prCourseList", prCourseList);

		model.addAttribute("prPredict", new PRPredict());
		model.addAttribute("prPredictBoolean", false);

		Map<Integer, String> prCourseMap = new LinkedHashMap<Integer, String>();
		for (PRCourse prCourse : prCourseList) {
			prCourseMap.put(prCourse.getId(), prCourse.getPrName());
		}
		model.addAttribute("prCourseMap", prCourseMap);
	}
}