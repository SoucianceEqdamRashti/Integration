Spring Boot IIB Admin App Example
===================
This simple Spring Boot App together with Apache Camel shows how you can connect to a IBM Integration Bus admin port
and traverse the admin REST API to get a list of all execution groups and the applications and REST APIs deployed onto them.
Furthermore two properties such as lastdeployeddatetime and lastdeployedpath are also extracted.

Finally, the output is converted to a json and save as a file.

You can off course modify this to send the json to a logic app which parses the json and creates a sharepoint list so you always
have the latest list documented. You could send it to a wiki page or to a database if you wanted to.

You can also extract further properties if you want to such as the state of the deployed artefact.

This app works basically as follows:
1) You run the app by typing java -jar iibartefacts.jar
    -Dcom.sun.net.ssl.checkRevocation=false
    -Dspring.config.location=<path-to-your-application-props-file>
    -Diib.endpoint=<url-to-your-iib-endpoint-including-port>
    -Denvironment=<specify-environment>

    The first jvm property tells the app ignore ssl verification. If you need ssl validation to remove this parameter.
    The second parameter is the path to your spring boot application.yml/properties file.
    The third parameter specifies the url to your Integration Bus endpoint such as http://test-myiib.com:4414
    The fourth parameter specifies the environment type such as test, preprod and prod.

2.  Once the app starts it connects to your IIB endpoint and calls the admin REST API to get a list of all execution groups.
    It saves the list of execution groups in an in-memory database called H2 provided by spring boot and specified in the
    application properties file. The schema.sql in the resources folder creates the tables when spring boot starts.
    See the file in the resources folder for examples.

3.  After getting the list of execution groups it then calls the admin API again and this time it gets a list of all
    deployed applications and restapis and saves them to the table in H2.

4.  After getting the list of execution groups and applications/restapis it then calls the admin API again and retrieves
    the properties of each deployed application/restapi. It saves this in the H2 database.

5.  Finally it generates an output as a json and in this example saves it to a file. After this a separate thread initiates
    a shutdown of the CamelContext and the app shuts down completely.

This was just a quick app to get the list. There are off course several ways you can improve the code and add more
sophisticated error handling. But if you are just interested in getting the list of IIB artefacts for documentation
purposes this will do the job.

To run this in maven or in your IDE:
  mvn install
  mvn springboot:run