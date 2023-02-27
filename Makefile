start:
	mvn clean install -P PK -Dmaven.test.skip=true
	docker-compose build
	docker-compose up

stop:
	docker-compose down

start-infra:
	docker-compose -f docker-compose-infrastructure.yml build
	docker-compose -f docker-compose-infrastructure.yml up

stop-infra:
	docker-compose -f docker-compose-infrastructure.yml down

.PHONY: start stop start-infra stop-infra
