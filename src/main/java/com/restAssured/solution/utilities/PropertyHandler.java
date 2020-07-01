package com.restAssured.solution.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {

    private static final PropertyHandler instance = new PropertyHandler();
    private Properties props = null;

    private PropertyHandler() {

        // load the properties file of the system under test
        File propsFile = new File("interview.properties");
        String propsFileName = propsFile.getName();

        InputStream inputStream;
        inputStream = getClass().getResourceAsStream("/"+propsFileName);
        Properties applicationProps = new Properties();
        try {
            applicationProps.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            System.out.println(propsFileName+": can not load the properties file");
        }

        this.props = applicationProps;
    }

    public static synchronized PropertyHandler getInstance() {
        return instance;
    }

    /**
     * Get the value of a property
     *
     * @param propKey the property key
     * @return the value
     */
    public String getValue(String propKey) {
        return this.props.getProperty(propKey);
    }
}
