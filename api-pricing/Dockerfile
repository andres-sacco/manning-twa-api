FROM openjdk:21-slim

COPY target/api-pricing-*.jar application/api-pricing.jar

CMD java -jar application/api-pricing.jar --spring.config.location=classpath:/application-docker.yml

EXPOSE 5070 
