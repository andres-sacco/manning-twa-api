FROM openjdk:12-alpine

COPY target/api-provider-beta-*.jar application/api-provider-beta.jar

CMD java -jar application/api-provider-beta.jar --spring.config.location=classpath:/application-docker.yml

EXPOSE 9070 
