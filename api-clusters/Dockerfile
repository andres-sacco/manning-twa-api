FROM openjdk:12-alpine

COPY src/conf application/

COPY target/api-clusters-*.jar application/api-clusters.jar

CMD java -jar application/api-clusters.jar --spring.config.location=application/docker/application.yml

EXPOSE 4070 
