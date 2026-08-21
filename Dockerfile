FROM eclipse-temurin:25-jdk

COPY build/libs/orderflow-0.0.1-SNAPSHOT.jar orderflow_app-1.0.0.jar

ENTRYPOINT ["java", "-jar", "orderflow_app-1.0.0.jar"]