package org.souciance.integration.validate;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import org.apache.camel.Exchange;

import java.io.IOException;

/**
 * Created by moeed on 2017-01-15.
 */
public class ValidateJson {
    /**
     * Method to jsonvalidate some json data based on a json schema
     * @throws IOException
     * @throws ProcessingException
     */
    public static void isValidJson(Exchange exchange) throws IOException, ProcessingException {
        final JsonNode data = JsonLoader.fromString(exchange.getIn().getBody().toString());
        final JsonNode schema = JsonLoader.fromString(exchange.getProperty("Schema").toString());

        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonValidator validator = factory.getValidator();

        ProcessingReport report = validator.validate(schema, data);
        System.out.println(report);
        if (!report.toString().contains("success")) {
            report.toString();
            exchange.getIn().setHeader("isValid", false);
        }
       else {
            exchange.getIn().setHeader("isValid", true);
        }

    }
}
