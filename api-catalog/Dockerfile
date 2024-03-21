FROM openjdk:21-slim

COPY target/api-catalog-*.jar application/api-catalog.jar

CMD java -jar application/api-catalog.jar --spring.config.location=classpath:/application-docker.yml

EXPOSE 6070 
