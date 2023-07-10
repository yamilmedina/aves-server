docker/build-image: local/assemble
	docker build -t aves:latest -f Dockerfile .

compose/run: docker/build-image
	docker-compose up --build

local/assemble:
	./gradlew clean buildFatJar

local/run:
	./gradlew clean runFatJar
