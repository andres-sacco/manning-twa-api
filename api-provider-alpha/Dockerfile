FROM openjdk:12-alpine

COPY src/conf application/

COPY target/api-provider-alpha-*.jar application/api-provider-alpha.jar

CMD java -jar application/api-provider-alpha.jar --spring.config.location=application/docker/application.yml

EXPOSE 8070 
