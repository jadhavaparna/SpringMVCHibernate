package com.journaldev.spring.runtest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;		

@RunWith(Suite.class)
@Suite.SuiteClasses({com.journaldev.spring.dao.PersonDAOImplTest.class,
    com.journaldev.spring.service.PersonServiceImplTest.class})
public class TestSuiteOne {

}
