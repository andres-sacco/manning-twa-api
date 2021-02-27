FROM openjdk:12-alpine

COPY target/api-clusters-*.jar application/api-clusters.jar

CMD java -jar application/api-clusters.jar --spring.config.location=classpath:/application-docker.yml

EXPOSE 4070 
