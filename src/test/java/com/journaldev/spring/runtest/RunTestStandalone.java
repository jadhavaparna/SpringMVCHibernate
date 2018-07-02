package com.journaldev.spring.runtest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestSuiteOne.class, TestSuiteSecond.class} )
public class RunTestStandalone {

	/*
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuiteOne.class);
 
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
            failure.getException().printStackTrace();
        }
        System.out.println("Test successful? " + result.wasSuccessful());
    }
*/	     
}
