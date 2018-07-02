package com.journaldev.spring.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

import com.journaldev.spring.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class PersonDAOImplTest {

//	private static final int ID = 1;
//	private static final String NAME = "shankar";
//	private static final String COUNTRY = "Australia";
	
	Person p1 = new Person() ;
	Person p2 = new Person() ;
	Person p3 = new Person() ;
	
	@Mock
	SessionFactory sessionFactory;

	@Mock
	private Session session;

	@Mock
	Criteria criteria;
	  
	@Mock
	Query query ;
	
	@Autowired
	@InjectMocks
	PersonDAO personDAO;
	
	@Spy
	List<Person> persons = new ArrayList<Person>();
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		personDAO = new PersonDAOImpl();
		personDAO.setSessionFactory(sessionFactory);
		/*
		p.setId(ID);
		p.setName(NAME);
		p.setCountry(COUNTRY);
		*/
		

		p1.setId(1);
		p1.setName("Sonam");
		p1.setCountry("India");

		p2.setId(2);
		p2.setName("Rajesh");
		p2.setCountry("USA");

		p3.setId(3);
		p3.setName("Aparna");
		p3.setCountry("China");

		persons.add(p1);
		persons.add(p2);
		persons.add(p3);

		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);

	}

	@Test
	public void testAddPerson(){
		
		Person personnew = new Person();
		personnew.setName("new_name");
		personnew.setCountry("new_country");

		doNothing().when(session).persist(personnew);
		boolean actual_result = personDAO.addPerson(personnew);
		assertTrue(actual_result);
		verify(session, times(1)).persist(personnew);
		
	}
	@Test
	public void testUpdatePerson(){
		
		p1.setName("updated_name");
		p1.setCountry("updated_country");

		doNothing().when(session).update(p1);
		boolean actual_result = personDAO.updatePerson(p1);
		assertTrue(actual_result);
		verify(session, times(1)).update(p1); 
	}
	@Test
    public void testListPersons()
    {
		// Using createCriteria
		Mockito.when(session.createCriteria(Person.class)).thenReturn(criteria);
		Mockito.when(criteria.list()).thenReturn(persons);
		
		// Using createQuery 
	    // Mockito.when(session.createQuery("from Person")).thenReturn(query);
	    // Mockito.when(query.list()).thenReturn(persons);

	    List<Person> personList = new ArrayList<Person>();
	    personList =  personDAO.listPersons();

	    assertThat(personList,is(persons));
    }

	@Test
	public void testGetPersonById(){
		Person p = new Person();
		Mockito.when(session.load(Person.class, new Integer(1))).thenReturn(p1);
		p = personDAO.getPersonById(1);
		//assertEquals(p,p1);
		assertThat(p,is(p1));
	}
	
	@Test
	public void testRemovePerson(){
		Mockito.when(session.load(Person.class, new Integer(1))).thenReturn(p1);
		doNothing().when(session).delete(p1);
		
		boolean actual_result = personDAO.removePerson(1);
		assertTrue(actual_result);
		verify(session, times(1)).delete(p1); 
	}

}



