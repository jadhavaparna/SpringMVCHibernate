package com.journaldev.spring.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

import com.journaldev.spring.dao.PersonDAO;
import com.journaldev.spring.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:/WEB-INF/spring/appServlet/servlet-context.xml"})
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class PersonServiceImplTest {

	private static final boolean EXPECTED_RESULT = true;
	private static final String NAME = "shankar";
	private static final String COUNTRY = "Australia";
	private Person p = new Person();
	
	@Mock
	PersonDAO personDAO ;
	
	//@Autowired
	@InjectMocks
	PersonServiceImpl personServiceImpl ;
	

	
	@Before
	public void setUp() throws Exception {
		
		p.setName(NAME);
		p.setCountry(COUNTRY);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddPerson() {

		//when(personDAO.addPerson(any(Person.class)) ).thenReturn(true);
		
		when(personDAO.addPerson(p)).thenReturn(true);
		boolean result = personServiceImpl.addPerson(p);
		Person p1 = personServiceImpl.getPersonById(1);
		assertEquals(result,EXPECTED_RESULT);
	
	}


}
