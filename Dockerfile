FROM maven:3.9.0-eclipse-temurin-17
WORKDIR /photon/
COPY pom.xml /photon/
COPY src /photon/src/