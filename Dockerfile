ARG VERSION=LOCAL

FROM maven:3-openjdk-17-slim AS builder

ARG VERSION

WORKDIR /app

COPY ./pom.xml /app/

# Download and cache dependencies between builds
RUN mvn dependency:go-offline

COPY ./src /app/src

RUN mvn -Drevision=${VERSION} clean package

FROM openjdk:17-alpine

ARG VERSION

WORKDIR /app

COPY --from=builder /app/target/pokedex-${VERSION}.jar /app/pokedex.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "/app/pokedex.jar"]
