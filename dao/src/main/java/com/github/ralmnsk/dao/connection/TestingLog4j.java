package com.github.ralmnsk.dao.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//ONLY FOR TESTING WHETHER LOG4J WORKS OR NOT
public class TestingLog4j {
    private static Logger logger= LoggerFactory.getLogger(TestingLog4j.class);
    public static void main(String [] a){
        System.out.println("example");
        logger.info("testing");
    }
}
