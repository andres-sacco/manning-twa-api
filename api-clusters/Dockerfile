FROM openjdk:21-slim

COPY target/api-clusters-*.jar application/api-clusters.jar

CMD java -jar application/api-clusters.jar --spring.config.location=classpath:/application-docker.yml

EXPOSE 4070 
