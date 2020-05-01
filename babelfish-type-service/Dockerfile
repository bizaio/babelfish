FROM openjdk:11-jre-slim
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE
ADD target/${JAR_FILE} /opt/babelfish/babelfish-type-service.jar
ENTRYPOINT ["java","-jar","/opt/babelfish/babelfish-type-service.jar"]
