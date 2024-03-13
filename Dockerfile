FROM openjdk:21

WORKDIR /app

COPY target/myWebProgr-0.0.1-SNAPSHOT.jar /app/myWebProgr-0.0.1-SNAPSHOT.jar
COPY sql/init.sql /docker-entrypoint-initdb.d/init.sql

EXPOSE 8080
CMD ["java", "-jar", "myWebProgr-0.0.1-SNAPSHOT.jar"]
