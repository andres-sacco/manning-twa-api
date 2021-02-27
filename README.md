# Manning TWA - Optimizing Cost
[![Build Status](https://travis-ci.com/andres-sacco/manning-twa-api.svg?branch=main)](https://travis-ci.com/andres-sacco/manning-twa-api) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This project contains all the code related with "Optimize costs in Microservices"

## Requirements
To use these microservices you need to have in your machine the following things:
- [Java 11](https://www.oracle.com/ar/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/)

## Architecture
The microservices are an abstraction of all the possible flow of the flight's team. These microservices only contain all endpoints related with do a search. 

#### Microservices
The system consists of the following microserices:

* **api-catalog** - a microservice contaning all information about which are the valid cities to find flights.
* **api-clusters** - a microservice contaning all the validations about the parameters of the search. Also call to **api-pricing** to obtain the final price of each itinerary.
* **api-pricing** - a microservice contaning all the rules to add markup of each itinerary and calculate the final price of each of them.
* **api-itineraries-search** - a microservices contaning all logic of which provider need to call to obtain all the possible itineraries. Also this microservice remove the possible duplicates.
* **api-provider-alpha** - a microservice which simulate to obtain the information of an external provider.
* **api-provider-beta** - a microservice which simulate to obtain the information of an external provider.

The following picture illustrates the architecture described above.
![Architecture](.images/Microservices-Architecture.png)

#### Model
Most of the microservices uses a common model of classes, to prevent duplicate code in each of them exists a library [Flights Common DTO](https://github.com/andres-sacco/manning-twa-libs-dto).

The common model consists of the following group of classes/enums:

* **AvailabilityRequestDTO** - This class contain all the parameters uses to do a search.
* **Provider** - This enum contain all the possible providers (Alpha, Beta).
* **FlightType** - This enum contain all the possible types of flights (One-way, Return). 
* **PassengerType** - This enum contain all the types of passengers.
* **ItineraryDTO** & **SegmentDTO**  & **LegDTO** - These classes have all the common information about one group of flights. Also contain a reference to the price.
* **PriceInfoDTO** - This class contain all the information about the price of all the type of passengers.
* **PaxPriceDTO** - This class refer to the price of one type of passenger.
* **MarkupDTO** - This class have all the information about the markup of one itinerary. 


## Run the APIs
In order to run API, please follow these steps:
- Clone the repository using this command **git clone https://github.com/andres-sacco/manning-twa-api.git**
- Open a terminal in the directory of the API and run **mvn clean install** , this command compile all the code and generate the jars. After do that, run **docker-compose build** and  **docker-compose up** all the components run together.
- If everything works fine, open a web browser in the URL which appears in the documentation section.

Other option is open each project in the IDE (Eclipse, IntelliJ) and run it. Take in consideration that the repository have a file **docker-compose-infrastructure.yml** which have the containers to run the databases that **Catalog** and **Pricing** uses to obtain the information, so compile and run this file before run all the microservices in your IDE.

## Documentation of APIs
Each API have documentation to understand which parameters are required and the URL to invoke it. To see the API documentation is necessary to run the each project and access to:
- [Swagger - Clusters](http://localhost:4070/api/flights/clusters/documentation)
- [Swagger - Pricing](http://localhost:5070/api/flights/pricing/documentation)
- [Swagger - Catalog](http://localhost:6070/api/flights/catalog/documentation)
- [Swagger - Itineraries Search](http://localhost:7070/api/flights/itineraries-search/documentation)
- [Swagger - Provider Alpha](http://localhost:8070/api/flights/provider/alpha/documentation)
- [Swagger - Provider Beta](http://localhost:9070/api/flights/provider/beta/documentation)

Optionally you can use **Postman** or **Insomia** to do the request from the different microservices, the repository include a collection of each tool with all the endpoints.
