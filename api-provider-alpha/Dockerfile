FROM openjdk:21-slim

COPY target/api-provider-alpha-*.jar application/api-provider-alpha.jar

CMD java -jar application/api-provider-alpha.jar --spring.config.location=classpath:/application-docker.yml

EXPOSE 8070 
