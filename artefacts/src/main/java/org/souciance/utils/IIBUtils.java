package org.souciance.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.camel.Exchange;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class IIBUtils {

  private static final String CONTENT_TYPE = "application/json";
  private static final String EXECUTION_GROUP = "EXECUTION_GROUP";
  private static final String APPLICATION = "APPLICATION";
  private static final String BASE_URI = "/apiv1/executiongroups";

    public static String getContentType() {
        return CONTENT_TYPE;
    }

    public static String getExecutiongroup() {
        return EXECUTION_GROUP;
    }

    public static String getApplication() {
        return APPLICATION;
    }

    public static String getBaseUri() {
        return BASE_URI;
    }

    public static void getExecutiongroupName(Exchange exchange) {
      String execution_group_uri = exchange.getIn().getBody().toString();
      String execution_group = execution_group_uri.substring(execution_group_uri.lastIndexOf("/") + 1, execution_group_uri.length());
      exchange.getIn().setBody(execution_group);
    }

    public static void getNamesOnly(Exchange exchange) {
      String application_uri = exchange.getIn().getBody().toString();
      String execution_group, application_name;
      String paths[] = application_uri.split("/");
      execution_group = paths[3];
      application_name = paths[5];
      exchange.getIn().setHeader(EXECUTION_GROUP, execution_group);
      exchange.getIn().setHeader(APPLICATION, application_name);
    }

    public static void getApplicationProperties(Exchange exchange) {
      String lastDeployedPath = exchange.getIn().getHeader("lastDeployPath").toString();
      String lastDeployedDate = exchange.getIn().getHeader("lastDeployDateTime").toString();
      exchange.getIn().setHeader("lastDeployPath", lastDeployedPath.replace("[","").replace("]","").replace("\"",""));
      exchange.getIn().setHeader("lastDeployDateTime", lastDeployedDate.replace("[","").replace("]","").replace("\"",""));
    }

    public static void getListAsJson(Exchange exchange) throws IOException {
      List apps_properties = exchange.getIn().getBody(List.class);
      final ByteArrayOutputStream out = new ByteArrayOutputStream();
      final ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(out, apps_properties);
      final byte[] data = out.toByteArray();
      String json = new String(data);
      String formattedJson = getFormattedFields(json);
      final ObjectMapper outputMapper = new ObjectMapper();
      JsonNode node = outputMapper.readTree(formattedJson);
      ArrayNode array = outputMapper.createArrayNode();
      array.add(node);
      JsonNode items = outputMapper.createObjectNode();
      ((ObjectNode) items).set("items", node);
      ((ObjectNode) items).put("environment", exchange.getProperty("ENVIRONMENT").toString());

      exchange.getIn().setBody(outputMapper.writeValueAsString(items));
    }

    private static String getFormattedFields(String jsonBody) {
      return jsonBody.replace("EXECUTIONGROUP", "executionGroup").replace("INTEGRATIONNAME", "integrationname")
          .replace("LASTDEPLOYDATETIME", "lastDeployDateTime").replace("LASTDEPLOYPATH", "lastDeployPath");
    }

  public static String getInsertExecutiongroupsQuery() {
    return "sql:insert into EXECUTION_GROUPS (executionGroup, integrationname) values(:#${body}, '')?dataSource=#dataSource";
  }

  public static String getAllExecutiongroupsQuery() {
   return "sql:select executionGroup from EXECUTION_GROUPS?dataSource=#dataSource";
  }

  public static String getDeleteAllExecutiongroupsQuery() {
      return "sql:DELETE FROM EXECUTION_GROUPS?dataSource=#dataSource";
  }

  public static String getInsertApplicationsQuery() {
      return "sql:INSERT INTO EXECUTION_GROUPS (executionGroup, integrationname) values(:#${headers." + EXECUTION_GROUP + "}, :#${headers." + APPLICATION + "})?dataSource=#dataSource";
  }

  public static String getAllApplicationsQuery() {
      return "sql: SELECT executionGroup, integrationname FROM EXECUTION_GROUPS?dataSource=#dataSource";
  }

  public static String getInsertApplicationPropertiesQuery() {
      return "sql: INSERT INTO APPLICATIONS (executionGroup, integrationname, lastDeployDateTime, lastDeployPath) values('${exchangeProperty." + EXECUTION_GROUP + "}','${exchangeProperty." + APPLICATION + "}','${headers.lastDeployDateTime}', '${headers.lastDeployPath}')?dataSource=#dataSource";
  }

  public static String getAllApplicationsAndPropertiesQuery() {
      return "sql:SELECT * FROM APPLICATIONS?dataSource=#dataSource";
  }
}
