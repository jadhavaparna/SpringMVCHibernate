package com.journaldev.spring.service;

import java.util.List;

import com.journaldev.spring.model.Person;

public interface PersonService {

	public boolean addPerson(Person p);
	public boolean updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public boolean removePerson(int id);
	
}
