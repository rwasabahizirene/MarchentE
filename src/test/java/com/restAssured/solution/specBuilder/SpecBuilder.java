package com.restAssured.solution.specBuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

public class SpecBuilder {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static RequestSpecification requestSpecification(){
        return requestSpec = new RequestSpecBuilder().setBaseUri("http://qainterview.merchante-solutions.com:3030").setContentType("application/json").build();
    }

    @BeforeClass
    public static ResponseSpecification responseSpecification(){
        return responseSpec = new ResponseSpecBuilder().expectContentType("application/json").build();
    }
}
