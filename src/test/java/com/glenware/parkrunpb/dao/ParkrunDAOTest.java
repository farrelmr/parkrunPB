package com.glenware.parkrunpb.dao;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
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

import org.hibernate.classic.Session;
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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ReflectionTestUtils;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.*;

import com.glenware.parkrunpb.dao.ParkrunDAO;
import com.glenware.parkrunpb.form.PRCourse;
import com.glenware.parkrunpb.form.PRRegion;

// http://www.javahotchocolate.com/tutorials/mockito.html
// http://bitbybitblog.com/unit-testing-data-access-components/
public class ParkrunDAOTest {

	private ParkrunDAOImpl parkrunDAOImpl;

	private SessionFactory sessionFactory;

	private Session session;

	private Query query;

	@Before
	public void setUp() {
		parkrunDAOImpl = new ParkrunDAOImpl();

		sessionFactory = mock(SessionFactory.class);
		session = mock(Session.class);
		query = mock(Query.class);

		ReflectionTestUtils.setField(parkrunDAOImpl, "sessionFactory",
				sessionFactory);
	}

	@Test
	public void addPRCourse() {

		PRRegion prRegion = new PRRegion();
		prRegion.setId(1);
		prRegion.setPrName("TestRegion");

		PRCourse prCourse = new PRCourse();
		prCourse.setId(1);
		prCourse.setPrName("Test");
		prCourse.setAverageTime(1200);
		prCourse.setUrl("http://test");
		prCourse.setPrregion(prRegion);

		when(sessionFactory.getCurrentSession()).thenReturn(session);

		// call the method we wish to test...
		parkrunDAOImpl.addPRCourse(prCourse);

		// verify the method was called...
		verify(sessionFactory.getCurrentSession()).save(prCourse);

		// because there's no state to examine, we're done

	}

	@Test
	public void getPRCourse() {

		PRRegion prRegion = new PRRegion();
		prRegion.setId(1);
		prRegion.setPrName("TestRegion");

		PRCourse prCourse = new PRCourse();
		prCourse.setId(1);
		prCourse.setPrName("Test");
		prCourse.setAverageTime(1200);
		prCourse.setUrl("http://test");
		prCourse.setPrregion(prRegion);

		// http://bitbybitblog.com/unit-testing-data-access-components/
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(sessionFactory.getCurrentSession().get(PRCourse.class, 1))
				.thenReturn(prCourse);

		PRCourse fromDAO = parkrunDAOImpl.getPRCourse(1);

		// THEN
		assertNotNull(fromDAO);
		assertSame(fromDAO, prCourse);
	}

}