FROM openjdk:12-alpine

COPY target/api-pricing-*.jar application/api-pricing.jar

CMD java -jar application/api-pricing.jar --spring.config.location=classpath:/application-docker.yml

EXPOSE 5080 
