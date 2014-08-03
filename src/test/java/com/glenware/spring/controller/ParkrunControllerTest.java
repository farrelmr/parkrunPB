package com.glenware.spring.controller;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.glenware.parkrunpb.controller.ParkrunController;
import com.glenware.parkrunpb.form.PRCourse;
import com.glenware.parkrunpb.form.PRPredict;
import com.glenware.parkrunpb.form.PRRegion;
import com.glenware.parkrunpb.service.ParkrunService;
import com.glenware.spring.config.UnitTestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestContext.class})
public class ParkrunControllerTest {

    private ParkrunController parkrunController;
    
	private ParkrunService parkrunServiceMock;
	
    @Resource
    private Validator validator;

    @Before
    public void setUp() {
    	parkrunController = new ParkrunController();
    	
    	parkrunServiceMock = mock(ParkrunService.class);
        ReflectionTestUtils.setField(parkrunController, "parkrunService", parkrunServiceMock);
    }
    
    @Test
    public void listPRCourses() {
    	BindingAwareModelMap model = new BindingAwareModelMap();
    	
    	PRRegion prRegion = new PRRegion();
    	prRegion.setId(11);
    	prRegion.setPrName("Scotland");
    	
    	PRCourse prCourse = new PRCourse();
    	prCourse.setId(9);
    	prCourse.setPrregion(prRegion);
    	prCourse.setPrName("Edinburgh");
    	prCourse.setUrl("http://www.parkrun.org.uk/edinburgh/");
    	prCourse.setAverageTime(1523);
    	    	
    	List<PRCourse> prCourseList = new ArrayList<PRCourse> ();
    	prCourseList.add( prCourse );
    	
        when(parkrunServiceMock.listPRCourse()).thenReturn(prCourseList);
    	    	
    	String view = parkrunController.listPRCourses( model );
    	
        verify(parkrunServiceMock, times(1)).listPRCourse();
        verifyNoMoreInteractions(parkrunServiceMock);
    	
    	List<PRCourse> prCourseServiceList = parkrunServiceMock.listPRCourse();
    	List<PRCourse> prCourseListModel = (List<PRCourse>) model.asMap().get("prCourseList");
    	
    	assertTrue(prCourseServiceList.containsAll(prCourseListModel));
    	assertTrue(prCourseServiceList.size() == 1);
    	
    	PRPredict prPredictModel = (PRPredict) model.asMap().get("prPredict");
    	assertTrue(prPredictModel instanceof PRPredict);
    	
    	Boolean prPredictBoolean = (Boolean) model.asMap().get("prPredictBoolean");
    	assertEquals(prPredictBoolean, false);
    	        
        assertEquals("prCourse", view);
    }
    
    
    @Test
    public void predictPRCourse() {
    	PRRegion prRegion = new PRRegion();
    	prRegion.setId(11);
    	prRegion.setPrName("Scotland");
    	
    	PRCourse prCourse = new PRCourse();
    	prCourse.setId(9);
    	prCourse.setPrregion(prRegion);
    	prCourse.setPrName("Edinburgh");
    	prCourse.setUrl("http://www.parkrun.org.uk/edinburgh/");
    	prCourse.setAverageTime(1523);
    	    	
    	List<PRCourse> prCourseList = new ArrayList<PRCourse> ();
    	prCourseList.add( prCourse );
    	
        when( parkrunServiceMock.listPRCourse() ).thenReturn(prCourseList);
        when( parkrunServiceMock.getPRCourse(9) ).thenReturn(prCourse);
    	
    	// Form Object
    	PRPredict prPredictFormObject = new PRPredict();
    	prPredictFormObject.setCourseId(1);
    	prPredictFormObject.setMm("20");
    	prPredictFormObject.setSs("00");
    	
    	BindingAwareModelMap model = new BindingAwareModelMap();
    	
        MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/predict");
        BindingResult result = bindAndValidate(mockRequest, prPredictFormObject);
        
        String view = parkrunController.predictPRCourse(prPredictFormObject, result, model);

        assertEquals("prCourse", view);
        
    	List<PRCourse> prCourseServiceList = parkrunServiceMock.listPRCourse();
    	List<PRCourse> prCourseListModel = (List<PRCourse>) model.asMap().get("prCourseList");
    	
    	assertTrue(prCourseServiceList.containsAll(prCourseListModel));
    	assertTrue(prCourseServiceList.size() == 1);
    	
    	Boolean prPredictBoolean = (Boolean) model.asMap().get("prPredictBoolean");
    	assertEquals(prPredictBoolean, true);
    }
    
    private BindingResult bindAndValidate(HttpServletRequest request, Object formObject) {
        WebDataBinder binder = new WebDataBinder(formObject);
        binder.setValidator(validator);
        binder.bind(new MutablePropertyValues(request.getParameterMap()));
        binder.getValidator().validate(binder.getTarget(), binder.getBindingResult());
        return binder.getBindingResult();
    }

    

//    @Test
//    public void about() {
//        String view = parkrunController.about();
//        assertEquals("about", view);
//    }
    
}