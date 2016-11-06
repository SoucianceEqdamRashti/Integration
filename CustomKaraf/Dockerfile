#Run using docker command
# docker run -d --name CamelWithKaraf  SoucianceEqdamRashti/CamelWithKaraf:latest
FROM  java:8u66
MAINTAINER souciance <souciance.eqdam.rashti@gmail.com>
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV KARAF_VERSION=4.0.2
RUN mkdir /opt/karaf
COPY /target/CamelWithKaraf /opt/karaf/
WORKDIR /opt/karaf/
RUN tar --strip-components=1 -C /opt/karaf -xzf CamelWithKaraf
COPY /src/main/resources/org/apache/karaf/branding/branding.properties /opt/karaf/etc/
WORKDIR /opt/karaf/
ENTRYPOINT ["/opt/karaf/bin/karaf", "start"]

