FROM openjdk:12-alpine

COPY target/api-itineraries-search-*.jar application/api-itineraries-search.jar

CMD java -jar application/api-itineraries-search.jar --spring.config.location=classpath:/application-docker.yml

EXPOSE 7070 
