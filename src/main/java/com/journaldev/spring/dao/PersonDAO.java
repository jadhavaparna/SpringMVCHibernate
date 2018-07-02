package com.journaldev.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.journaldev.spring.model.Person;

public interface PersonDAO {

	public void setSessionFactory(SessionFactory sf);
	public boolean addPerson(Person p);
	public boolean updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public boolean removePerson(int id);
	public SessionFactory getSessionFactory();
}
