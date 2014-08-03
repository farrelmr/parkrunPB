package com.glenware.parkrunpb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glenware.parkrunpb.form.PRCourse;
import com.glenware.parkrunpb.form.PRPredict;
import com.glenware.parkrunpb.service.ParkrunService;

@Controller
@RequestMapping("api")
public class JSONController {
	
	@Autowired
	private ParkrunService parkrunService;	
	
	@RequestMapping("/listcourses")
	@ResponseBody
	public List<PRCourse> admin( Map<String, Object> map ) {
		map.put("prCourse", new PRCourse());
		List<PRCourse> prCourseList = parkrunService.listPRCourse();
		return prCourseList;
	}
	
	/* same as above method, but is mapped to
	 * /api/prcourse?id= rather than /api/prcourse/{id}
	 */
	@RequestMapping("prcourse/{id}")
	@ResponseBody
	public PRCourse getById(@PathVariable Integer id) {
		return parkrunService.getPRCourse(id);
	}

	/* same as above method, but is mapped to
	 * /api/prcourse?id= rather than /api/prcourse/{id}
	 */
	@RequestMapping(value="prcourse", params="id")
	@ResponseBody
	public PRCourse getByIdFromParam(@RequestParam Integer id) {
		return parkrunService.getPRCourse(id);
	}
	
	/* same as above method, but is mapped to
	 * /api/prcourse/{id}/{time}
	 */
	@RequestMapping("prcourse/{id}/{timeMM}/{timeSS}")
	@ResponseBody
	public List<PRCourse> predictTimes(@PathVariable Integer id, @PathVariable int timeMM, @PathVariable int timeSS) {
		
		PRPredict prPredict = new PRPredict();
		prPredict.setCourseId(id);
		prPredict.setPrCourse( parkrunService.getPRCourse( prPredict.getCourseId() ) );
		prPredict.setMmInt(timeMM);
		prPredict.setSsInt(timeSS);
		
		List<PRCourse> prCourseList = parkrunService.listPRCourse();
		for (PRCourse prCourse : prCourseList) {
			prCourse.setPrPredict(prPredict);
		}
		
		return prCourseList;
	}
	
}