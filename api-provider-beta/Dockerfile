FROM openjdk:12-alpine

COPY src/conf application/

COPY target/api-provider-beta-*.jar application/api-provider-beta.jar

CMD java -jar application/api-provider-beta.jar --spring.config.location=application/docker/application.yml

EXPOSE 9070 
