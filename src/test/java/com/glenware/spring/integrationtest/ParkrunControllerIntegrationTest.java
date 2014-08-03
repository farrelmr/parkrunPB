package com.glenware.spring.integrationtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.glenware.parkrunpb.form.PRPredict;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class ParkrunControllerIntegrationTest {
 
    @Autowired 
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before 
    public void setUp() {
        this.mockMvc = webAppContextSetup(ctx).build();
    }

    @Test 
    public void about() throws Exception {
        mockMvc.perform(get("/about"))
        .andExpect(status().isOk())
        .andExpect(view().name("about"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/about.jsp"));
    }
    
    @Test 
    public void home() throws Exception {
        mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("prCourse"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/prCourse.jsp"))
        .andExpect(model().size(4))
        .andExpect(model().attributeExists("prCourseList"))
        .andExpect(model().attributeExists("prPredict"))
        .andExpect(model().attribute("prPredict", hasProperty("courseId", isEmptyOrNullString())))
        .andExpect(model().attribute("prPredict", hasProperty("mm", isEmptyOrNullString())))
        .andExpect(model().attribute("prPredict", hasProperty("ss", isEmptyOrNullString())))
        .andExpect(model().attribute("prPredict", hasProperty("prCourse", nullValue())))
        .andExpect(model().attributeExists("prPredictBoolean"))
        .andExpect(model().attributeExists("prCourseMap"));
    }
    
    @Test
    public void predict() throws Exception {
    	PRPredict prPredict = new PRPredict();
    	prPredict.setCourseId(11);
    	prPredict.setMm("20");
    	prPredict.setSs("00");
    	
    	this.mockMvc.perform( post("/predict").param("courseId", "11").param("mm", "20").param("ss", "00") )
    	.andExpect(status().isOk())
        .andExpect(view().name("prCourse"))
        .andExpect(model().size(4))
        .andExpect(model().attributeExists("prCourseList"))
        .andExpect(model().attributeExists("prPredict"))
        .andExpect(model().attribute("prPredict", hasProperty("courseId", equalTo(11) )))
        .andExpect(model().attribute("prPredict", hasProperty("mm", equalTo("20") )))
        .andExpect(model().attribute("prPredict", hasProperty("ss", equalTo("00")  )))
        .andExpect(model().attributeExists("prPredictBoolean"))
        .andExpect(model().attributeExists("prCourseMap"))
        .andExpect(forwardedUrl("/WEB-INF/jsp/prCourse.jsp"));
    }

    @Test
    public void predictErrors() throws Exception {
    	PRPredict prPredict = new PRPredict();
    	prPredict.setCourseId(11);
    	prPredict.setMm("20");
    	prPredict.setSs("00");
    	
    	this.mockMvc.perform( post("/predict") ) //.param("courseId", "11").param("mm", "20").param("ss", "00") )
    	.andExpect(status().isOk())
    	.andExpect(model().attributeHasFieldErrors("prPredict", "mm"))
    	.andExpect(model().attributeHasFieldErrors("prPredict", "courseId"))
    	.andExpect(model().attributeExists("prCourseMap"));
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testGraphJSON() throws Exception {
       MvcResult result = this.mockMvc.perform(get("/api/prcourse/9"))
       .andExpect(status().isOk())
       .andExpect(content().contentType("application/json;charset=UTF-8"))
//       .andExpect(jsonPath("$.id", is(9)))
//       .andExpect(MockMvcResultMatchers.jsonPath("$.id"), is(9))
       .andReturn();
       
       
//       {"id":9,"prName":"Edinburgh","url":"http://www.parkrun.org.uk/edinburgh/","averageTime":1523,"prregion":{"id":11,"regionName":"Scotland"},"prPredict":null,"averageTimeString":"25:23","estimatedAverageTime":null,"regionId":0}

       System.out.println(result.getResponse().getContentAsString());
       
    }
    
    
}