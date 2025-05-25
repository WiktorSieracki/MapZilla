FROM gradle:jdk21-alpine
WORKDIR /opt
COPY . .
RUN gradle wrapper
EXPOSE 8081
CMD ["./gradlew", "bootRun"]