package com.github.ralmnsk.app.controllers;
import com.mockrunner.mock.web.WebMockObjectFactory;
import com.mockrunner.servlet.ServletTestModule;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IndexServletTest {
    private ServletTestModule tester;
    private WebMockObjectFactory factory;

    @Before
    public void setup() {
        factory = new WebMockObjectFactory();
        tester = new ServletTestModule(factory);

    }

    @Test
    public void doGet(){
        int expectedCode = 200;

         // instantiate the servlet
        tester.createServlet(IndexServlet.class);

        // call doGet
        tester.doGet();

        // assertion: status code should be 200
        assertEquals(expectedCode, factory.getMockResponse().getStatusCode());
    }

    @Test
    public void doPostReq() throws ServletException, IOException {
        tester.createServlet(IndexServlet.class);
        tester.doPost();
        assertEquals(200, factory.getMockResponse().getStatusCode());

    }

}