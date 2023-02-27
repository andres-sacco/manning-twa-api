FROM openjdk:12-alpine

COPY src/conf application/

COPY target/api-pricing-*.jar application/api-pricing.jar

CMD java -jar application/api-pricing.jar --spring.config.location=application/docker/application.yml

EXPOSE 5070 
