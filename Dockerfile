# FROM docker.io/gradle AS build-env
#
# WORKDIR /app
# COPY . ./
# RUN ./gradlew buildFatJar

FROM eclipse-temurin:17-jre-alpine

# COPY --from=build-env /app/build/libs/com.wire.aves.server-all.jar /opt/aves/
COPY ./build/libs/com.wire.aves.server-all.jar /opt/aves/aves.jar
#COPY aves.jks                              /opt/aves/
#COPY firebase-sdk.json                     /opt/aves

WORKDIR /opt/aves

ENTRYPOINT ["java", "-jar", "aves.jar"]