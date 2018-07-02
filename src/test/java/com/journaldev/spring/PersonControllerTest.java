package com.journaldev.spring;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class PersonControllerTest {

	private MockMvc mockMvc;
	
	Person p1 = new Person() ;
	Person p2 = new Person() ;
	Person p3 = new Person() ;
	List<Person> listPersons = new ArrayList<Person>();
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	PersonService personService;

	@Autowired
	@InjectMocks
	PersonController personController ;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		 //this.mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
		 mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


			p1.setId(1);
			p1.setName("Sonam");
			p1.setCountry("India");

			p2.setId(2);
			p2.setName("Rajesh");
			p2.setCountry("USA");
		
			listPersons.add(p1);
			listPersons.add(p2);
	}

	@Test
	public void testListPersons() throws Exception{
		when(personService.listPersons()).thenReturn(listPersons);
		
        mockMvc.perform(get("/persons"))
        .andExpect(status().isOk())
        .andExpect(view().name("person"))
        .andExpect(forwardedUrl("/WEB-INF/views/person.jsp"))
        .andExpect(model().attribute("listPersons", hasSize(2)))
        .andExpect(model().attribute("listPersons", hasItem(
                allOf(
                        hasProperty("id", is(1)),
                        hasProperty("name", is("Sonam")),
                        hasProperty("country", is("India"))
                )
        )))
        .andExpect(model().attribute("listPersons", hasItem(
                allOf(
                        hasProperty("id", is(2)),
                        hasProperty("name", is("Rajesh")),
                        hasProperty("country", is("USA"))
                )
        )));

        verify(personService, times(1)).listPersons();
        verifyNoMoreInteractions(personService);
	}

}
