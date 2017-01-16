package org.souciance.integration.validate;

import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.io.IOUtils;

import java.nio.charset.Charset;


public class CamelValidateJson extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String schema = IOUtils.toString(classLoader.getResourceAsStream("jsonvalidate/schema.json"), Charset.defaultCharset());
        String json = IOUtils.toString(classLoader.getResourceAsStream("jsonvalidate/data.json"), Charset.defaultCharset());
        from("timer://cameldemoValidateJson?repeatCount=1").routeId("ValidateJson")
            .setBody(constant(json))
            .setProperty("Schema", constant(schema))
            .bean(ValidateJson.class, "isValidJson")
            .choice()
                .when(header("isValid")
                    .isEqualTo(true))
                    .log("Valid json!")
                .when(header("isValid")
                    .isEqualTo(false))
                    .log("Invalid json!")
            .end()
        .end();
    }
}
