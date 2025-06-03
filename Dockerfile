# Development

FROM maven:3.9.4-eclipse-temurin-17
ENV WORKING_DIR /app
WORKDIR ${WORKING_DIR}
COPY pom.xml ./
COPY src ./src
RUN mvn dependency:go-offline
EXPOSE 8080
CMD ["mvn", "spring-boot:run"]